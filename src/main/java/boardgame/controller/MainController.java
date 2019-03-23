package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManager;
import boardgame.gameModel.pieces.Piece;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTile;
import boardgame.view.HexagonTilePiece;
import boardgame.view.MapLocation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //TODO Move all these variables out of main controller class so they can be dynamically assigned.
    private final static double r = 40; // the inner radius from hexagon center to outer corner
    private final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
    private final static double TILE_HEIGHT = 2 * r;
    private final static double TILE_WIDTH = 2 * n;
    private static final String PLAYER1 = "Human Player";
    private static final String PLAYER2 = "Monster Player";

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

    private ArrayList<HexagonTile> tiles;

    double time = 60;

    private String[] playerArray = {PLAYER1, PLAYER2};
    private String activePlayer;

    //TODO move event.

    public MainController () {

        //Get a reference to the game manager
        GameManager gm = new GameManager();
        //gm.setUpDefaultGame();
        activePlayer = playerArray[0];
        tiles = new ArrayList<>();
    }


    //Temporary solution for testing of changing player. Not intended for submission.
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
        BoardGrid bg = drawBasicGrid(10, 10, r);
        endTurnButton.setOnAction(e -> changeActivePlayer());
        addPieces(bg, new ArrayList<>());
    }


    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.
    private BoardGrid drawBasicGrid(int rows, int columns, double radius) {
        BoardGrid boardGrid = new BoardGrid();
        double xStartOffset = 40;
        double yStartOffset = 40;

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                double xCoord = x * TILE_WIDTH + (y % 2) * n + xStartOffset;
                double yCoord = y * TILE_HEIGHT * 0.75 + yStartOffset;

                HexagonTile tile = new HexagonTile(xCoord, yCoord, radius);
                tile.setGridPosition(new MapLocation(x, y));
                boardPane.getChildren().add(tile);
                tile.setOnMouseClicked(e -> handleTileClicked(tile));
                tiles.add(tile);

            }
        }return boardGrid; //TODO actually return grid.
    }

    //Add game pieces to the game board.
    private void addPieces(BoardGrid boardGrid, List<Piece> pieceList) {
        MapLocation tileCoords = new MapLocation(1, 1);
        double xCoord = 0.0;
        double yCoord = 0.0;

        for (HexagonTile h: tiles) {
            if (h.getGridPosition().equals(tileCoords)) {
                xCoord = h.getInitialX();
                yCoord = h.getInitialY();
                HexagonTilePiece pieceTile = new HexagonTilePiece(xCoord, yCoord, r);
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

    private HexagonTile selectedTile = null;

    private boolean tileSelected = false;

    private void handlePieceClicked(HexagonTile tile){
        this.selectedTile = tile;
        this.tileSelected = true;
    }

    private void handleTileClicked(HexagonTile tile) {
        if(selectedTile !=null && tileSelected){
            changePiecePosition(selectedTile, tile);
            selectedTile=null;
            tileSelected=false;
        }
    }

    //TODO change to view class.
    private void changePiecePosition(HexagonTile hexagonTile, HexagonTile desiredTilePosition) {
        //Should probably be a view method.
        Translate translate = new Translate();
        translate.setX(desiredTilePosition.getBoundsInParent().getCenterX()- hexagonTile.getBoundsInParent().getCenterX());
        translate.setY(desiredTilePosition.getBoundsInParent().getCenterY() - hexagonTile.getBoundsInParent().getCenterY());
        hexagonTile.getTransforms().addAll(translate);

        //Bring piece to front so that it doesn't get stuck behind background tile.
        hexagonTile.toFront();
    }

}
