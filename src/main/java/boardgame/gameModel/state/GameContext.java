package boardgame.gameModel.state;

import boardgame.controller.GameController;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.command.*;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.util.HexGridUtil;
import boardgame.util.Location;
import boardgame.util.PieceUtil;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game context class. This class is the main driver class for the game logic.
 * It is the glue between the State, Command and Visitor patterns that that implement the
 * behaviour. As a result this class has a higher amount of coupling than most other
 * classes in our game where we have tried to have as much abstraction as possible to enable flexibility.
 * What we have done with this class however is move most of the implementation details out of the class.
 * Actions that the user takes are processed as Commands using the Command pattern.
 * A State machine is used and is implemented using the State Pattern. This ensures
 * that a game piece is in the correct state before a Command is issued.
 * We try to put as little game logic as possible in this class as the responsibility of this class
 * is to maintain Game State and facilitate the State, Command and Visitor patterns to work together.
 */
public class GameContext {

    private final IGameManager gm;
    private final IBoardGrid IBoardGrid;
    private final GameController gController;
    private State state;
    private HexagonTileViewPiece ownPiece;
    private TileView tileView;
    private List<TileView> highlightedTiles = new ArrayList<>();
    private TurnFacade tf;
    private SpecialVisitor sv;

    //These StringProperties are bound in GameController
    private final StringProperty pieceNameProperty = new SimpleStringProperty();
    private final StringProperty pieceLocation = new SimpleStringProperty();
    private final StringProperty swapAlternativeOne = new SimpleStringProperty();
    private final StringProperty swapAlternativeTwo = new SimpleStringProperty();
    private final StringProperty specialAbilityDescription = new SimpleStringProperty("Special Ability");

    private IPiece selectedPiece;

    /**
     * Instantiates a new Game context.
     *  @param state      the state
     * @param IBoardGrid the board grid
     * @param gm         the gm
     * @param gameController
     */
    public GameContext(State state, IBoardGrid IBoardGrid, IGameManager gm, GameController gameController) {
        this.state = state;
        this.IBoardGrid = IBoardGrid;
        this.gm = gm;
        tf = new TurnFacade(gm);
        this.gController = gameController;
    }


    //*******************************************************
    //                                                      *
    //     This section is responsible for state   *
    //     changes called from the state machine. A user
    //     clicks a button which triggers a State Change.
    //      The stateImp are not meant to hold game logic.
    //      They merely manage the transitions and
    //      available commands.
    //                                                      *
    //*******************************************************

    /**
     * Press move.
     */
    public void pressMove() {
        state.onMove(this);
    }

    /**
     * Press attack.
     */
    public void pressAttack() {
        state.onAttack(this);
    }

    /**
     * Press special.
     */
    public void pressSpecial() {
        state.onSpecial(this);
    }

    /**
     * Press defence.
     */
    public void pressDefence() {
        state.onDefence(this);
    }

    /**
     * Press swap button. This will set the state to swap
     */
    public void pressSwapButton() {
        state.onSwap(this);
    }

    /**
     * Press swap one.
     */
    public void pressSwapOne() {
        state.onSwapOne(this);
    }

    /**
     * Press swap two.
     */
    public void pressSwapTwo() {
        state.onSwapTwo(this);
    }


    /**
     * Click tile.
     *
     * @param tile the tile
     */
    public void clickTile(TileView tile) {
        this.tileView = tile;
        state.onSelectTile(this);
    }

    //*******************************************************************************

    //*******************************************************
    //*********  COMMAND SECTION ****************************
    //
    //  This section contains commands that can be executed.
    //  All commands need to go through the command processor
    // so that history can be recorded.
    //
    //  To create a new command create a class for the command
    // implementing Command interface. Then set the variables.
    // Don't forget to call execute on the command.
    //******************************************************/
    private CommandProcessor commandProcessor = new CommandProcessor();

    /**
     * Undo the selected action through the command processor.
     */
    public void undo() {
        //Reset all tiles to avoid weird errors.
        HighlightTilesVisitor.resetTileColours(getBoardGrid());
        commandProcessor.undo();
    }

    /**
     * Redo the selected action through the command processor.
     */
    public void redo() {
        commandProcessor.redo();
    }

    /**
     * Move a piece. Validates valid move before command is passed to Command Processor.
     */
    public void movePiece() {
        MoveCommand command = new MoveCommand();

        List<Location> locations = new ArrayList<>();
        for (TileView t : highlightedTiles) {
            locations.add(t.getModelTile().getLocation());
        }
        if (locations.contains(getTileView().getModelTile().getLocation())) {
            command.SetCommand(tf, getTileView().getModelTile().getLocation(), selectedPiece, getBoardGrid(), highlightedTiles);
            commandProcessor.execute(command);
        }
    }

    /**
     * Swap selection first option. This will be one of the 2 classes fo a player that is not the current
     * class selected
     */
    public void swapOne() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(tf, gm, swapAlternativeOne.get(), this);
        commandProcessor.execute(command);
    }

    /**
     * Swap selection second option. This will be one of the 2 classes fo a player that is not the current
     * class selected.
     */
    public void swapTwo() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(tf, gm, swapAlternativeTwo.get(), this);
        commandProcessor.execute(command);
    }

    /**
     * Attack a piece. Called as a transition from the attack state. Passes enemy piece details to attack Command
     * and then resets the enemy piece variable and resets any highlighted tiles to their original value.
     */
    public void attackPiece() {

        //Validate that the enemy piece is within attack range.
        if (highlightedTiles.contains(getBoardGrid().getTile(selectedPiece.getLocation()))) {

            AttackCommand command = new AttackCommand();
            command.setCommand(tf, selectedPiece);
            commandProcessor.execute(command);
        }
    }

    /**
     * Launch special ability.
     *
     */
    public void launchSpecialAbility() {
        SpecialCommand command = sv.getCommand();
        command.setCommand(getOwnPiece().getiPiece(), sv, tf, selectedPiece, getTileView());
        commandProcessor.execute(command);
        List<TileView> visited = HexGridUtil.visitAllTiles(0, getBoardGrid(), selectedPiece.getLocation());
        for (TileView t : visited) {
            t.setFill(Color.ANTIQUEWHITE);
        }
    }

    /**
     * Create shield.
     */
    public void createShield() {
        DefenceCommand command = new DefenceCommand();
        command.SetCommand(tf, selectedPiece);
        commandProcessor.execute(command);
    }

    public void replayAllMoves() {
        commandProcessor.replayMoves();
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(states state) {
        this.state = StateFactory.getState(state);
        highlightTiles(this.state);
    }

    //Can only enter here from OwnPieceSelected or subclasses.
    public void highlightSpecialTiles(states state, SpecialVisitor sv) {
        //maybe should assert correct class here.
        State specialState = StateFactory.getState(state);
        this.state = specialState;
        HighlightTilesVisitor hv = sv.getHv();
        List<TileView> visited = HexGridUtil.visitAllTiles(0, getBoardGrid(), selectedPiece.getLocation());
        hv.setHighlightVariables(selectedPiece, getBoardGrid(), gm, tf, visited, sv);
        specialState.accept(hv);
        highlightedTiles = hv.getTargetTiles();
    }

    public void highlightTiles(State state) {
        HighlightTilesVisitor hv = new HighlightTilesVisitor();
        List<TileView> visited = HexGridUtil.visitAllTiles(0, getBoardGrid(), selectedPiece.getLocation());
        hv.setHighlightVariables(getBoardGrid(), gm, visited, selectedPiece);
        state.accept(hv);
        highlightedTiles = hv.getTargetTiles();
    }

    /**
     * Select piece.
     *
     * @param piece the piece
     */
    public void selectPiece(HexagonTileViewPiece piece) {
        gm.toggleMinionSelectionOff();
        gm.specialAbilityOffCD();
        selectedPiece = piece.getiPiece();

        if(selectedPiece.getClass().getSimpleName().equals(PieceConstants.MINION)){
            gm.toggleMinionSelectionOn("Minion Health: " + selectedPiece.getHealth());
        }

        pieceNameProperty.setValue(selectedPiece.getPieceName().get());
        pieceLocationProperty().setValue(selectedPiece.getLocation().toString());

        if (tf.isActivePlayerPiece(piece.getiPiece())) {
            this.ownPiece = piece;
            // Validate GUI special Ability
            if(!gm.getActivePlayer().getIsAbilityUsed()){
                    gm.specialAbilityOnCD();
            }

            specialAbilityDescription.setValue(piece.getiPiece().getSpecialAbilityDescription());
            state.onSelectOwnPiece(this);

        } else {
            state.onSelectEnemyPiece(this);
        }
    }

    public void setUpSwap() {

        //Switch the disabled status for the Swap Pane in the GUI.
        setSwapPaneVisible(true);

        //Request the names of the other pieces that the piece may be swapped to.
        String[] altClasses = PieceUtil.getSwapAlternatives(getSelectedPiece().getPieceClass(), gm.getActivePlayer());

        //Set the names of the swap classes. These StringProperty values are bound in GameController so the buttons
        // don't need to be manually set.
        swapAlternativeOne.setValue(altClasses[0]);
        swapAlternativeTwo.setValue(altClasses[1]);
    }

    //Getters and setters.
    public void setSwapPaneVisible(boolean b) {
        gController.setSwapPaneVisible(b);
    }

    public void setPieceSelected(IPiece piece) {
        this.selectedPiece = piece;
    }

    public StringProperty pieceLocationProperty() {
        return pieceLocation;
    }

    public StringProperty swapAlternativeTwoProperty() {
        return swapAlternativeTwo;
    }

    public StringProperty swapAlternativeOneProperty() {
        return swapAlternativeOne;
    }

    public HexagonTileViewPiece getOwnPiece() {
        return ownPiece;
    }

    public TileView getTileView() {
        return tileView;
    }

    public StringProperty specialAbilityDescriptionProperty() {
        return specialAbilityDescription;
    }

    public State getState() {
        return state;
    }

    public IBoardGrid getBoardGrid() {
        return IBoardGrid;
    }

    public StringProperty pieceNamePropertyProperty() {
        return pieceNameProperty;
    }

    public IPiece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSpecialVisitor(SpecialVisitor sv) {
        this.sv = sv;
    }

    public List<TileView> getHighlightedTiles() {
        return highlightedTiles;
    }
}
