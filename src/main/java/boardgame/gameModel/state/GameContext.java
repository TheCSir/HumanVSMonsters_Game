package boardgame.gameModel.state;

import boardgame.controller.SpecialButton;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.state.command.*;
import boardgame.util.Location;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static boardgame.util.HexGridUtil.offset_distance;

/**
 * The Game context class. This class is the main driver class for the game logic.
 * Actions that the user takes are processed as Commands using the Command pattern.
 * A State machine is used and is implemented using the State Pattern. This ensures
 * that a game piece is in the correct state before a Command is issued.
 */
public class GameContext {

    private final IGameManager gm;
    private final IBoardGrid IBoardGrid;
    private State state;
    private HexagonTileViewPiece ownPiece;
    private TileView tileView;
    private Pane swapPane;
    private Button opt_one;
    private Button opt_two;
    private List<TileView> highlightedTiles = new ArrayList<>();
    private TurnFacade tf;
    private SpecialVisitor sv;

    /**
     * Instantiates a new Game context.
     *
     * @param state      the state
     * @param IBoardGrid the board grid
     * @param gm         the gm
     */
    public GameContext(State state, IBoardGrid IBoardGrid, IGameManager gm) {
        this.state = state;
        this.IBoardGrid = IBoardGrid;
        this.gm = gm;
        tf = new TurnFacade(gm);
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
     * @param swapPane the swap pane
     * @param opt_one  the opt one
     * @param opt_two  the opt two
     */
    public void pressSwapButton(Pane swapPane, Button opt_one, Button opt_two) {
        this.swapPane = swapPane;
        this.opt_one = opt_one;
        this.opt_two = opt_two;
        SpecialButton sp1 = new SpecialButton();
        sp1.setButton(opt_one);
        //sp1.setiPiece();
        SpecialButton sp2 = new SpecialButton();
        sp2.setButton(opt_two);


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

    private StringProperty pieceNameProperty = new SimpleStringProperty();
    private StringProperty pieceLocation = new SimpleStringProperty();
    private IPiece selectedPiece;


    public StringProperty pieceNamePropertyProperty() {
        return pieceNameProperty;
    }

    public IPiece getSelectedPiece() {
        return selectedPiece;
    }

    /**
     * Select piece.
     *
     * @param piece the piece
     */
    public void selectPiece(HexagonTileViewPiece piece) {
        selectedPiece = piece.getiPiece();
        pieceNameProperty.setValue(selectedPiece.getPieceName().get());
        pieceLocationProperty().setValue(selectedPiece.getLocation().toString());
        System.out.println("selectedPiece = " + selectedPiece.getPieceName());
//        System.out.println("enemyPiece = " + enemyPiece.getiPiece().getPieceName());
        if (isActivePlayerPiece(piece.getiPiece())) {
            this.ownPiece = piece;
            state.onSelectOwnPiece(this);
        } else {
            state.onSelectEnemyPiece(this);
        }
    }


    /**
     * Reset tile colours for neighbouring tiles. Call to clear highlighted tiles
     * after selecting tiles
     */
    public void resetTileColours() {
        // IBoardGrid.setNeighbourTilesColor(IBoardGrid.getSelectedTilePiece(), Color.ANTIQUEWHITE);
        System.out.println("resetting tile colours");

        // Reset tiles color
        for (TileView tileView : highlightedTiles) {
            tileView.setFill(Color.ANTIQUEWHITE);
        }
    }

    // Checks if selected piece belongs to the active player
    private boolean isActivePlayerPiece(IPiece ipiece) {

        System.out.println("Active player is: " + gm.getTurn().getActivePlayer().getPlayerName());
        for (IPiece piece : tf.getActivePlayerPieces()) {
            if (piece.getClass().getSuperclass().equals(ipiece.getClass().getSuperclass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Highlight tiles that can be moved to.
     */
    public void highlightMove() {
        highlightedTiles.clear();
        IBoardGrid bg = getBoardGrid();

        int movespeed = selectedPiece.getMoveSpeed();

        Location pieceLocation = selectedPiece.getLocation();

        List<TileView> visited = visitAllTiles(movespeed, bg, pieceLocation);

        for (TileView tileView : visited) {
            int offDist = offset_distance(pieceLocation, tileView.getLocation());


            if (offDist <= movespeed) {
                tileView.setFill(Color.rgb(200, 24, 0));
                highlightedTiles.add(tileView);
            }

        }
    }

    public List<TileView> visitAllTiles(int distance, IBoardGrid bg, Location location) {
        //https://www.redblobgames.com/grids/hexagons/

        //Start of very inefficent BFS. Will do for the moment.
        //Probably refactor and move to separate class.
        TileView underTile = bg.getTile(location);
        List<TileView> visited = new ArrayList<>();
        Queue<TileView> queue = new LinkedList<>();
        queue.add(underTile);
        visited.add(underTile);
        int q = 0;
        while (!queue.isEmpty() && q < 10000) {
            //System.out.println("queue = " + queue.peek());
            TileView x = queue.poll();
            List<TileView> neighbours = x.getNeighbourViews();
            int i;
            for (i = 0; i < neighbours.size(); i++) {
                TileView tileView = neighbours.get(i);
                queue.add(neighbours.get(i));
                if (!visited.contains(tileView)) {
                    visited.add(neighbours.get(i));
                }
            }
            q++;
        }
        return visited;
    }


    public void highlightAttack() {
        highlightedTiles.clear();
        IBoardGrid bg = getBoardGrid();
        Location pieceLocation = selectedPiece.getLocation();
        TileView underTile = bg.getTile(pieceLocation);


        //Replace this with conditional loop once different terrain
        // exists.
        highlightedTiles.addAll(underTile.getNeighbourViews());
        for (TileView t : highlightedTiles) {
            t.setFill(Color.RED);
        }
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
            command.SetCommand(getGm(), tf, getTileView().getModelTile().getLocation(), selectedPiece, getBoardGrid(), highlightedTiles);
            commandProcessor.execute(command);
        }
    }

    /**
     * Swap selection first option. This will be one of the 2 classes fo a player that is not the current
     * class selected
     */
    public void swapOne() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(tf, getGm(), swapPane, opt_one);
        System.out.println("opt_one = " + opt_one.getText());
        commandProcessor.execute(command);
    }

    /**
     * Swap selection second option. This will be one of the 2 classes fo a player that is not the current
     * class selected.
     */
    public void swapTwo() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(tf, getGm(), swapPane, opt_two);
        System.out.println("opt_two = " + opt_two.getText());
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

            //resetTileColours();
        }
    }

    /**
     * Launch special ability.
     *
     */
    public void launchSpecialAbility() {
        SpecialCommand command = sv.getCommand();
        command.setCommand(gm, getOwnPiece().getiPiece(), sv, tf, selectedPiece, getSelectedPiece());
        commandProcessor.execute(command);
        List<TileView> visited = visitAllTiles(0, getBoardGrid(), selectedPiece.getLocation());
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
     * Gets gm.
     *
     * @return the gm
     */
    public IGameManager getGm() {
        return gm;
    }

    /**
     * Gets swap pane.
     *
     * @return the swap pane
     */
    public Pane getSwapPane() {
        return swapPane;
    }

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

    public void setPieceSelected(IPiece piece) {
        this.selectedPiece = piece;
    }

    public StringProperty pieceLocationProperty() {
        return pieceLocation;
    }

    //Can only enter here from OwnPieceSelected or subclasses.
    public void highlightSpecialTiles(states state, SpecialVisitor sv) {
        //maybe should assert correct class here.
        State specialState = StateFactory.getState(state);
        this.state = specialState;
        HighlightTilesVisitor hv = sv.getHv();
        List<TileView> visited = visitAllTiles(0, getBoardGrid(), selectedPiece.getLocation());
        hv.setHighlightVariables(selectedPiece, getBoardGrid(), gm, tf, visited, sv);
        specialState.accept(hv);
    }

    public void highlightTiles(State state) {
        HighlightTilesVisitor hv = new HighlightTilesVisitor();
        List<TileView> visited = visitAllTiles(0, getBoardGrid(), selectedPiece.getLocation());
        hv.setHighlightVariables(getBoardGrid(), gm, visited);
        state.accept(hv);
    }
}
