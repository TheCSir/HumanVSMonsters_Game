package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManager;
import boardgame.gameModel.pieces.Piece;
import boardgame.util.Constants;
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

    private ArrayList<HexagonTile> tiles;

    private Map<MapLocation, HexagonTile> tileMap = new HashMap<>();

    double time = 60;

    private String[] playerArray = {Constants.PLAYER1, Constants.PLAYER2};
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
        BoardGrid bg = new BoardGrid();
        drawBasicGrid(Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS, r);
        endTurnButton.setOnAction(e -> changeActivePlayer());
        addPieces(bg, new ArrayList<>());
    }


    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.
    private void drawBasicGrid(int rows, int columns, double radius) {
        double xStartOffset = 40;
        double yStartOffset = 40;
        final double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
        final double TILE_HEIGHT = 2 * r;
        final double TILE_WIDTH = 2 * n;
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                double xCoord = x * TILE_WIDTH + (y % 2) * n + xStartOffset;
                double yCoord = y * TILE_HEIGHT * 0.75 + yStartOffset;

                HexagonTile tile = new HexagonTile(xCoord, yCoord, radius);
                tile.setGridPosition(new MapLocation(x, y));
                Text gridloc = new Text(tile.getGridPosition().getxGridValue() + ", " + tile.getGridPosition().getyGridValue());

                boardPane.getChildren().add(tile);
                boardPane.getChildren().add(gridloc);
                gridloc.setX(tile.getXPosition());
                gridloc.setY(tile.getYPosition());
                tile.setOnMouseClicked(e -> handleTileClicked(tile));
                tiles.add(tile);
                tileMap.put(tile.getGridPosition(), tile);

            }
        }

        for (HexagonTile t: tiles) {
            int tGridX = t.getGridPosition().getxGridValue();
            int tGRidY = t.getGridPosition().getyGridValue();

            List<MapLocation> neighbourLocations = getNeighbourPositions(tGridX, tGRidY);

                for (MapLocation m: neighbourLocations) {
                    if(checkMapLocation(m,Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS)){
                        t.addNeighbour(tileMap.get(m));
                    }
                }

                for (HexagonTile ne: t.getNeighbours()) {
                    System.out.println("Tile is: " + t.getGridPosition().getxGridValue() + ", "
                            + t.getGridPosition().getyGridValue()
                            + ", Neigbouring tile is " + ne.getGridPosition().getxGridValue() + ", "
                            + ne.getGridPosition().getyGridValue());
                }

            }
        }

    private boolean checkMapLocation(MapLocation mapLocation, int rows, int columns) {
        return (mapLocation.getxGridValue() >= 0
                && mapLocation.getyGridValue() >=0
                && mapLocation.getxGridValue() < columns
                && mapLocation.getyGridValue() < rows);
    }

    private List<MapLocation> getNeighbourPositions (int tGridX, int tGRidY) {
        List<MapLocation> neighbourLocations = new ArrayList<>();
        MapLocation NW;
        MapLocation NE;
        MapLocation W;
        MapLocation E;
        MapLocation SW;
        MapLocation SE;

        if (tGRidY % 2 == 0) {
            NW = new MapLocation(tGridX - 1, tGRidY - 1);
            NE = new MapLocation(tGridX, tGRidY - 1);
            W = new MapLocation(tGridX - 1, tGRidY);
            E = new MapLocation(tGridX + 1, tGRidY);
            SW = new MapLocation(tGridX - 1, tGRidY + 1);
            SE = new MapLocation(tGridX, tGRidY + 1);

        }else {
            NW = new MapLocation(tGridX , tGRidY - 1);
            NE = new MapLocation(tGridX+1, tGRidY - 1);
            W = new MapLocation(tGridX - 1, tGRidY);
            E = new MapLocation(tGridX + 1, tGRidY);
            SW = new MapLocation(tGridX, tGRidY + 1);
            SE = new MapLocation(tGridX+1, tGRidY + 1);


        }
        neighbourLocations.add(NW);
        neighbourLocations.add(NE);
        neighbourLocations.add(W);
        neighbourLocations.add(E);
        neighbourLocations.add(SW);
        neighbourLocations.add(SE);
        return neighbourLocations;
    }

    //Add game pieces to the game board. //TODO move to a view class.
    private void addPieces(BoardGrid boardGrid, List<Piece> pieceList) {
        MapLocation tileCoords = new MapLocation(1, 1);
        double xCoord = 0.0;
        double yCoord = 0.0;

        for (HexagonTile h: tiles) {
            if (h.getGridPosition().equals(tileCoords)) {
                xCoord = h.getInitialX();
                yCoord = h.getInitialY();
                HexagonTilePiece pieceTile = new HexagonTilePiece(xCoord, yCoord, r);
                pieceTile.setGridPosition(new MapLocation(1, 1));
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
        List<HexagonTile> neighbours = tileMap.get(tile.getGridPosition()).getNeighbours();
        for (HexagonTile n: neighbours) {
            n.setFill(Color.LIGHTCORAL);
        }

    }

    private void handleTileClicked(HexagonTile tile) {
        if(selectedTile !=null && tileSelected){
            List<HexagonTile> neighbours = tileMap.get(selectedTile.getGridPosition()).getNeighbours();
            for (HexagonTile n: neighbours) {
                n.setFill(Color.ANTIQUEWHITE);
            }
            changePiecePosition(selectedTile, tile);

//            selectedTile=null;
//            tileSelected=false;
        }
    }

    //TODO change to view class.
    private void changePiecePosition(HexagonTile hexagonPiece, HexagonTile desiredTilePosition) {

        if (checkValidMove(hexagonPiece, desiredTilePosition.getGridPosition())) {

            //Should probably be a view method.
            Translate translate = new Translate();
            translate.setX(desiredTilePosition.getBoundsInParent().getCenterX() - hexagonPiece.getBoundsInParent().getCenterX());
            translate.setY(desiredTilePosition.getBoundsInParent().getCenterY() - hexagonPiece.getBoundsInParent().getCenterY());
            hexagonPiece.getTransforms().addAll(translate);

            //Bring piece to front so that it doesn't get stuck behind background tile.
            hexagonPiece.toFront();
            hexagonPiece.setGridPosition(desiredTilePosition.getGridPosition());
        }
    }

    private boolean checkValidMove(HexagonTile piece, MapLocation mapLocation) {
        List<HexagonTile> neighbours = tileMap.get(piece.getGridPosition()).getNeighbours();
        for (HexagonTile tile: neighbours) {
            if (tile.getGridPosition().equals(mapLocation)){
                System.out.println("Valid move!");
                return true;
            }
        }
        System.out.println("Invalid move!");
        return false;
    }
}
