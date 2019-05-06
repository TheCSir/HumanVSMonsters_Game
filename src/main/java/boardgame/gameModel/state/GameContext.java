package boardgame.gameModel.state;

import boardgame.controller.MainController;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.state.command.*;
import boardgame.util.Location;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.abs;

/**
 * The Game context class. This class is the main driver class for the game logic.
 * Actions that the user takes are processed as Commands using the Command pattern.
 * A State machine is used and is implemented using the State Pattern. This ensures
 * that a game piece is in the correct state before a Command is issued.
 */
public class GameContext {

    private final IGameManager gm;
    private final MainController mc;
    private final IBoardGrid IBoardGrid;
    private State state;
    private HexagonTileViewPiece ownPiece;
    private HexagonTileViewPiece enemyPiece;
    private TileView tileView;
    private Pane swapPane;
    private Button opt_one;
    private Button opt_two;
    private List<TileView> highlightedTiles = new ArrayList<>();

    /**
     * Instantiates a new Game context.
     *
     * @param state      the state
     * @param IBoardGrid the board grid
     * @param gm         the gm
     * @param mc         the mc
     */
    public GameContext(State state, IBoardGrid IBoardGrid, IGameManager gm, MainController mc) {
        this.state = state;
        this.IBoardGrid = IBoardGrid;
        this.gm = gm;
        this.mc = mc;
    }


    //*******************************************************
    //                                                      *
    //     This section is responsible for state   *
    //     changes called from the state machine. A user
    //     clicks a button which triggers a State Change.
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
     *
     * @param gameContext the game context
     */
    public void pressDefence(GameContext gameContext) {
        //TODO Add implementation for defence.
        System.out.println("To be implemented");
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
        IBoardGrid.setTargetTile(tile);
        state.onSelectTile(this);
    }

    /**
     * Select piece.
     *
     * @param piece the piece
     */
    public void selectPiece(HexagonTileViewPiece piece) {
        if (isActivePlayerPiece(piece.getiPiece())) {
            this.ownPiece = piece;
            state.onSelectOwnPiece(this);
        } else {
            this.enemyPiece = piece;
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
        for (IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if (piece.getClass().getSuperclass().equals(ipiece.getClass().getSuperclass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update piece details.
     */
    public void updatePieceDetails() {
        HexagonTileViewPiece piece = getOwnPiece();

        //Update View.
        Text pieceLocation = getMc().getPieceLocation();
        Text pieceSelected = getMc().getPieceSelected();
        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());
    }

    /**
     * Update enemy piece details.
     */
    void updateEnemyPieceDetails() {
        IPiece piece = getEnemyPiece().getiPiece();
        Text pieceLocation = getMc().getPieceLocation();
        Text pieceSelected = getMc().getPieceSelected();
        pieceSelected.setText("Class: " + piece.getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getLocation().getX()
                + ", "
                + "Y: " + piece.getLocation().getY());
    }

    /**
     * Highlight tiles that can be moved to.
     */
    void highlightMove() {
        highlightedTiles.clear();
        IBoardGrid bg = getBoardGrid();

        int movespeed = ownPiece.getiPiece().getMoveSpeed();

        Location pieceLocation = ownPiece.getLocation();

        //https://www.redblobgames.com/grids/hexagons/

        //Start of very inefficent BFS. Will do for the moment.
        //Probably refactor and move to separate class.
        TileView underTile = bg.getTile(pieceLocation);
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
        System.out.println("visited = " + visited.size());
        for (TileView tileView : visited) {
            int offDist = offset_distance(pieceLocation, tileView.getLocation());

            //Debugging
            System.out.println("*******************************");
            System.out.println("offDist = " + offDist);
            System.out.println("tileView = " + tileView.getLocation());
            System.out.println("pieceLocation = " + pieceLocation);
            System.out.println("***********************************");
            System.out.println("movespeed = " + movespeed);


            if (offDist <= movespeed) {
                tileView.setFill(Color.RED);
                //Debugging
//                Text text = new Text(tileView.getLocation().getX() + ", " + tileView.getLocation().getY());
//                getBoardGrid().getBoardPane().getChildren().add(text);
//                text.translateXProperty().setValue(tileView.getXPosition());
//                text.translateYProperty().setValue(tileView.getYPosition());
                highlightedTiles.add(tileView);
            }

        }


    }

    //*******************************************************************************
    //
    //  Utility methods for calculating distance between grid points in a hex grid.
    //
    //********************************************************************************

    private int offset_distance(Location a, Location b) {
        Hex locationA = new Hex(a.getX(), a.getY());
        Hex locationB = new Hex(b.getX(), b.getY());
        Cube ac = oddr_to_cube(locationA);
        Cube bc = oddr_to_cube(locationB);
        return cube_distance(ac, bc);
    }

    private int cube_distance(Cube a, Cube b) {
        return (abs(a.x - b.x) + abs(a.y - b.y) + abs(a.z - b.z)) / 2;
    }

    private Location cube_to_oddr(Cube cube) {
        int col = cube.x + (cube.z - (cube.z & 1)) / 2;
        int row = cube.z;
        return new Location(col, row);
    }

    private Cube oddr_to_cube(Hex hex) {
        int x = hex.col - (hex.row - (hex.row & 1)) / 2;
        int z = hex.row;
        int y = -x - z;
        return new Cube(x, y, z);
    }

    //*******************************************************************************

    /**
     * Move piece.
     */
    public void movePiece() {
        MoveCommand command = new MoveCommand();
        List<Location> locations = new ArrayList<>();
        for (TileView t : highlightedTiles) {
            locations.add(t.getModelTile().getLocation());
        }
        if (locations.contains(getTileView().getModelTile().getLocation())) {
            command.SetCommand(getGm(), getTileView().getModelTile().getLocation(), getOwnPiece(), getBoardGrid(), highlightedTiles);
            commandProcessor.execute(command);
        }
    }

    /**
     * Gets board grid.
     *
     * @return the board grid
     */
    public IBoardGrid getBoardGrid() {

        return IBoardGrid;
    }



    /**
     * Update tile info.
     */
    public void updateTileInfo() {
    }

    /**
     * Sets up swap.
     */
    public void setUpSwap() {
    }

    /**
     * Create shield.
     */
    public void createShield() {
        DefenceCommand command = new DefenceCommand();
        command.SetCommand(getGm(), getOwnPiece());
        commandProcessor.execute(command);
    }

    /**
     * Gets enemy piece.
     *
     * @return the enemy piece
     */
    public HexagonTileViewPiece getEnemyPiece() {
        return enemyPiece;
    }

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
     * Swap one.
     */
    public void swapOne() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(getGm(), swapPane, opt_one);
        System.out.println("opt_one = " + opt_one.getText());
        commandProcessor.execute(command);
    }

    /**
     * Swap two.
     */
    public void swapTwo() {
        SwapCommand command = new SwapCommand();
        command.SetCommand(getGm(), swapPane, opt_two);
        System.out.println("opt_two = " + opt_two.getText());
        commandProcessor.execute(command);
    }

    /**
     * Attack piece.
     */
    public void attackPiece() {
        AttackCommand command = new AttackCommand();
        command.setCommand(gm, getEnemyPiece());
        commandProcessor.execute(command);
    }

    /**
     * Launch special ability.
     */
    public void launchSpecialAbility() {
        SpecialCommand command = new SpecialCommand();
        command.setCommand(getGm(), getOwnPiece().getiPiece());
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
     * Gets mc.
     *
     * @return the mc
     */
    public MainController getMc() {
        return mc;
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
     * Sets state.
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * The type Cube.
     */
    class Cube {
        /**
         * The X.
         */
        int x;
        /**
         * The Y.
         */
        int y;
        /**
         * The Z.
         */
        int z;

        /**
         * Instantiates a new Cube.
         *
         * @param x the x
         * @param y the y
         * @param z the z
         */
        Cube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    /**
     * The type Hex.
     */
    class Hex {
        /**
         * The Col.
         */
        int col; //x
        /**
         * The Row.
         */
        int row; //y

        /**
         * Instantiates a new Hex.
         *
         * @param col the col
         * @param row the row
         */
        Hex(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }


}
