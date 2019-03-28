package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManager;
import boardgame.gameModel.Location;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Warrior;
import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileView;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.PieceView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    //TODO Move all these variables out of main controller class so they can be dynamically assigned.
    private final static double r = Constants.TILERADIUS; // the inner radius from hexagon center to outer corner


    @FXML // fx:id="turnTime"
    private Text turnTime; // Value injected by FXMLLoader

    @FXML
    private Pane boardPane;

    @FXML
    private VBox tileInfoPane;


    @FXML
    private Text currentPlayer;

    @FXML
    private Button endTurnButton;

    private ArrayList<HexagonTileView> tiles;

    private Map<Location, HexagonTileView> tileMap = new HashMap<>();

    double time = 60;

    private String[] playerArray = {Constants.PLAYER1, Constants.PLAYER2};
    private String activePlayer;

    //Store a reference to the Game manager for main entry point to game.
    private GameManager gm;

    //TODO move event.

    public MainController () {

        //Get a reference to the game manager. Currently sets up a game with default settings.
        gm = new GameManager();

        //Set up default board.

        gm.setUpBoard();

        activePlayer = playerArray[0];
        tiles = new ArrayList<>();
    }

    //Temporary solution for testing of changing player. Not intended for submission. change to model.
    private void changeActivePlayer() {
        if (activePlayer.equals(playerArray[0])){
            activePlayer = playerArray[1];
            currentPlayer.setText(activePlayer);
        }else {
            activePlayer = playerArray[0];
            currentPlayer.setText(activePlayer);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turnTime.setText("Turn Time " + time);
        BoardGrid bg = new BoardGrid();
        drawBasicGrid(Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS, r);
        endTurnButton.setOnAction(e -> changeActivePlayer());
        List<IPiece> pieces = gm.setUpHumanPieces();
        addPieces(bg, pieces);
    }


    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.
    private void drawBasicGrid(int rows, int columns, double radius) {
        double xStartOffset = 40;
        double yStartOffset = 40;
        final double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
        final double TILE_HEIGHT = 2 * r;
        final double TILE_WIDTH = 2 * n;
        Board2DHex board2DHex = new Board2DHex();
        board2DHex.setUpTiles(10, 10);
        Map<Location, ITile> board = board2DHex.getTiles();
        board2DHex.setUpTiles();
        List<HexagonalTile> boardTiles = board2DHex.getHexagonalTiles();

        for (HexagonalTile hexagonalTile: boardTiles) {
            int xPos = hexagonalTile.getLocation().getX();
            int yPos = hexagonalTile.getLocation().getY();

            //Set starting coordinates for the tile to be drawn.
            double xCoord = xPos * TILE_WIDTH + (yPos % 2) * n + xStartOffset;
            double yCoord = yPos * TILE_HEIGHT * 0.75 + yStartOffset;

            //Create the new tile.
            HexagonTileView tile = new HexagonTileView(xCoord, yCoord, radius, hexagonalTile);

            //Draw the tile coordinate to help with debugging.
            Text gridloc = new Text(xPos + ", " + yPos);
            gridloc.setX(tile.getXPosition());
            gridloc.setY(tile.getYPosition());


            //Add the tile to the JAvaFX pane.
            boardPane.getChildren().add(tile);
            boardPane.getChildren().add(gridloc);

            tile.setOnMouseClicked(e -> handleTileClicked(tile));
            tiles.add(tile);
            tileMap.put(tile.getLocation(), tile);

        }
    }


    //Add game pieces to the game board. //TODO move to a view class.
    private void addPieces(BoardGrid boardGrid, List<IPiece> pieceList) {
        Location tileCoords = new Location(1, 1);
        double xCoord = 0.0;
        double yCoord = 0.0;
        Warrior warrior = new Warrior(10, 5);
        warrior.setLocation(tileCoords);
//        for (IPiece piece: pieceList) {
//
//        }
        //tileMap.get(tileCoords);

        //TODO fix adding pieces
        for (HexagonTileView h: tiles) {
            if (h.getLocation().equals(tileCoords)) {
                xCoord = h.getInitialX();
                yCoord = h.getInitialY();
                HexagonTileViewPiece pieceTile = new HexagonTileViewPiece(xCoord, yCoord, r, warrior);
                try {
                    pieceTile.setImagePattern("src/main/resources/bigBird.PNG");
                }catch (FileNotFoundException e) {
                    System.out.println("Image File not found!");
                }

                boardPane.getChildren().add(pieceTile);
                pieceTile.setOnMouseClicked(e -> handlePieceClicked(pieceTile));
            }
        }
    }

    private HexagonTileViewPiece selectedTile = null;

    private boolean tileSelected = false;

    private void handlePieceClicked(HexagonTileViewPiece tile){
        this.selectedTile = tile;
        this.tileSelected = true;


        //TODO fix this
//        List<HexagonTileView> neighbours = tileMap.get(tile.getLocation()).getNeighbours();
//        for (HexagonTileView n: neighbours) {
//            n.setFill(Color.LIGHTCORAL);
//        }

    }

    //TODO separate tile and piece. Override this method in piece.
    private void handleTileClicked(HexagonTileView tile) {
        PieceView pieceView = new PieceView();
        System.out.println("Howdy!");
        System.out.println("Board position is: " + tile.getLocation());
        System.out.println(tile.getModelTile().getNeighbours().size());
        for (ITile neighbour: tile.getModelTile().getNeighbours()) {

            System.out.println("Neighbour: " + neighbour.getLocation());
        }
        if(selectedTile !=null && tileSelected){

            //TODO fix this
//            List<HexagonTileView> neighbours = tileMap.get(selectedTile.getLocation()).getNeighbours();
//            for (HexagonTileView n: neighbours) {
//                n.setFill(Color.ANTIQUEWHITE);
//            }
            pieceView.changePiecePosition(selectedTile, tile);

//            selectedTile=null;
//            tileSelected=false;
        }
    }


   // }

}
