package boardgame.gameModel.state;

import boardgame.controller.GameController;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.command.*;
import boardgame.gameModel.pieces.AbstractPieceFactory;
import boardgame.gameModel.pieces.FactoryProducer;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.HexGridUtil;
import boardgame.util.Location;
import boardgame.util.PieceUtil;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game context class. This class is the main driver class for the game logic.
 * Actions that the user takes are processed as Commands using the Command pattern.
 * A State machine is used and is implemented using the State Pattern. This ensures
 * that a game piece is in the correct state before a Command is issued.
 */
public class GameContext {

    private final IGameManager gm;
    private final IBoardGrid IBoardGrid;
    private final GameController gController;
    private State state;
    private HexagonTileViewPiece ownPiece;
    private TileView tileView;
    private Button opt_one;
    private Button opt_two;
    private List<TileView> highlightedTiles = new ArrayList<>();
    private TurnFacade tf;
    private SpecialVisitor sv;
    private StringProperty pieceNameProperty = new SimpleStringProperty();
    private StringProperty pieceLocation = new SimpleStringProperty();
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
     * Press swap button.
     *
     * @param opt_one  the opt one
     * @param opt_two  the opt two
     */
    public void pressSwapButton(Button opt_one, Button opt_two) {
        this.opt_one = opt_one;
        this.opt_two = opt_two;

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



    public StringProperty pieceNamePropertyProperty() {
        return pieceNameProperty;
    }

    public IPiece getSelectedPiece() {
        return selectedPiece;
    }

    private StringProperty specialAbilityDescription = new SimpleStringProperty("Special Ability");


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
            command.SetCommand(gm, tf, getTileView().getModelTile().getLocation(), selectedPiece, getBoardGrid(), highlightedTiles);
            commandProcessor.execute(command);
        }
    }

    /**
     * Swap selection first option. This will be one of the 2 classes fo a player that is not the current
     * class selected
     */
    public void swapOne() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(tf, gm, opt_one, this);
        commandProcessor.execute(command);
    }

    /**
     * Swap selection second option. This will be one of the 2 classes fo a player that is not the current
     * class selected.
     */
    public void swapTwo() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(tf, gm, opt_two, this);
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
            command.setCommand(tf, gm, selectedPiece);
            commandProcessor.execute(command);
        }
    }

    /**
     * Launch special ability.
     *
     */
    public void launchSpecialAbility() {
        SpecialCommand command = sv.getCommand();
        command.setCommand(gm, getOwnPiece().getiPiece(), sv, tf, selectedPiece, getSelectedPiece(), getTileView());
        commandProcessor.execute(command);
        List<TileView> visited = HexGridUtil.visitAllTiles(0, getBoardGrid(), selectedPiece.getLocation());
        for (TileView t : visited) {
            t.setFill(Color.ANTIQUEWHITE);
        }
    }

    public void setSpecialVisitor(SpecialVisitor sv) {
        this.sv = sv;
    }

    /**
     * Create shield.
     */
    public void createShield() {
        DefenceCommand command = new DefenceCommand();
        command.SetCommand(tf, selectedPiece);
        commandProcessor.execute(command);
    }


    /**
     * Gets own piece.
     *
     * @return the own piece
     */
    public HexagonTileViewPiece getOwnPiece() {
        return ownPiece;
    }

    /**
     * Gets tile view.
     *
     * @return the tile view
     */
    public TileView getTileView() {
        return tileView;
    }


    //Getters and setters.

    /**
     * Gets opt one.
     *
     * @return the opt one
     */
    public Button getOpt_one() {
        return opt_one;
    }

    /**
     * Gets opt two.
     *
     * @return the opt two
     */
    public Button getOpt_two() {
        return opt_two;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Gets board grid.
     *
     * @return the board grid
     */
    public IBoardGrid getBoardGrid() {

        return IBoardGrid;
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

    public List<TileView> getHighlightedTiles() {
        return highlightedTiles;
    }

    /**
     * Select piece.
     *
     * @param piece the piece
     */
    public void selectPiece(HexagonTileViewPiece piece) {
        gm.toggleMinionSelectionOff();
        selectedPiece = piece.getiPiece();

        if(selectedPiece.getClass().getSimpleName().equals(PieceConstants.MINION)){
            gm.toggleMinionSelectionOn("Minion Health: " + selectedPiece.getHealth());
        }


        pieceNameProperty.setValue(selectedPiece.getPieceName().get());
        pieceLocationProperty().setValue(selectedPiece.getLocation().toString());

        if (tf.isActivePlayerPiece(piece.getiPiece())) {
            this.ownPiece = piece;
            specialAbilityDescription.setValue(piece.getiPiece().getSpecialAbilityDescription());
            state.onSelectOwnPiece(this);

        } else {
            state.onSelectEnemyPiece(this);
        }

    }

    public StringProperty specialAbilityDescriptionProperty() {
        return specialAbilityDescription;
    }

    public void setUpSwap() {
        Button opt_one = getOpt_one();
        Button opt_two = getOpt_two();

        //Switch the disabled status
        setSwapPaneVisible(true);

        String currentPieceClass = getSelectedPiece().getPieceClass();
        List<String> altClasses = PieceUtil.alternativeClasses(currentPieceClass);

        IPlayer currentPlayer = gm.getActivePlayer();
        AbstractPieceFactory a = FactoryProducer.getFactory(currentPlayer.playerType());
        assert a != null;
        IPiece alternative1 = a.getPiece(altClasses.get(0), new Location(0, 0));
        IPiece alternative2 = a.getPiece(altClasses.get(1), new Location(0, 0));

        opt_one.setText(alternative1.getPieceName().getValue());
        opt_two.setText(alternative2.getPieceName().getValue());
    }

    public void setSwapPaneVisible(boolean b) {
        gController.setSwapPaneVisible(b);
    }


    public void setPieceSelected(IPiece piece) {
        this.selectedPiece = piece;
    }

    public StringProperty pieceLocationProperty() {
        return pieceLocation;
    }
}
