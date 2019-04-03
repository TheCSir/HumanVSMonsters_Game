package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManager;
import boardgame.gameModel.Location;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Warrior;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileView;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.PieceView;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    private Text pieceSelected;

    @FXML
    private Text pieceHealth;

    @FXML
    private Text pieceLocation;

    private ObservableList<HexagonTileView> tiles;

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
        tiles = FXCollections.observableArrayList();
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

        //Assertions to ensure that injection works
        assert currentPlayer != null : "fx:id=\"currentPlayer\" was not injected: check your FXML file 'mainView.fxml'.";
        assert turnTime != null : "fx:id=\"turnTime\" was not injected: check your FXML file 'mainView.fxml'.";
        assert endTurnButton != null : "fx:id=\"endTurnButton\" was not injected: check your FXML file 'mainView.fxml'.";
        assert boardPane != null : "fx:id=\"boardPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert tileInfoPane != null : "fx:id=\"tileInfoPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceSelected != null : "fx:id=\"pieceSelected\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceHealth != null : "fx:id=\"pieceHealth\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceLocation != null : "fx:id=\"pieceLocation\" was not injected: check your FXML file 'mainView.fxml'.";

        turnTime.setText("Turn Time " + time);
        BoardGrid bg = new BoardGrid();
        Board2DHex board2DHex = (Board2DHex) gm.getiBoard();
        board2DHex.setUpTiles(10, 10);
        Map<Location, ITile> board = board2DHex.getTiles();
        board2DHex.setUpTiles();
        List<ITile> boardTiles = new ArrayList<>(board.values());
        drawBasicGrid(boardTiles, r, boardPane);
        endTurnButton.setOnAction(e -> changeActivePlayer());
        List<IPiece> pieces = gm.setUpHumanPieces();
        addPieces(bg, pieces);

    }



    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.
    public void drawBasicGrid(List<ITile> boardTiles, double radius, Pane boardPane) {
        double xStartOffset = 40;
        double yStartOffset = 40;

        BoardGrid boardGrid = new BoardGrid();
        List<HexagonTileView> hexagonTileViews = boardGrid.calculateTileCoord(
                boardTiles, Constants.TILERADIUS, xStartOffset, yStartOffset);

        for (HexagonTileView hexagonalTile: hexagonTileViews) {
            
            //Add the tile to the JAvaFX pane.
            boardPane.getChildren().add(hexagonalTile);


            hexagonalTile.setOnMouseClicked(e -> handleTileClicked(hexagonalTile));
            tiles.add(hexagonalTile);
            tileMap.put(hexagonalTile.getLocation(), hexagonalTile);
        }

        boardGrid.drawTileGridPos(hexagonTileViews, boardPane);
    }




    //Add game pieces to the game board. //TODO move to a view class.
    private void addPieces(BoardGrid boardGrid, List<IPiece> pieceList) {
        Location tileCoords = new Location(1, 1);

        Warrior warrior = new Warrior(10, 5, tileCoords);
        warrior.locationPropertyProperty().addListener((observable, oldValue, newValue) -> {
            PieceView pieceView = new PieceView();
            if (selectedTile != null && targetTile != null)
                pieceView.changePiecePosition(selectedTile, targetTile);
        });
        warrior.getLocation().changeLocation(5, 5);
        ObservableList<HexagonTileViewPiece> pieceObservableList = FXCollections.observableArrayList();
        pieceObservableList.addListener((InvalidationListener) observable -> {
            System.out.println("Piece changed");
        });
        //TODO fix adding pieces
        for (HexagonTileView h: tiles) {
            if (h.getLocation().equals(tileCoords)) {
                double xCoord = h.getInitialX();
                double yCoord = h.getInitialY();
                HexagonTileViewPiece pieceTile = new HexagonTileViewPiece(xCoord, yCoord, r, warrior);
                try {
                    pieceTile.setImagePattern("src/main/resources/bigBird.PNG");
                }catch (FileNotFoundException e) {
                    System.out.println("Image File not found!");
                }

                boardPane.getChildren().add(pieceTile);
                pieceTile.setOnMouseClicked(e -> handlePieceClicked(pieceTile));
                pieceObservableList.add(pieceTile);

            }
        }


    }

    private HexagonTileViewPiece selectedTile = null;

    private boolean tileSelected = false;

    private HexagonTileView targetTile = null;

    private void handlePieceClicked(HexagonTileViewPiece tile){
        this.selectedTile = tile;
        this.tileSelected = true;

        //TODO make this bring up information about the piece.
        //TODO highlight surrounding tiles using neighbours.

    }

    //TODO separate tile and piece. Override this method in piece.
    private void handleTileClicked(HexagonTileView tile) {
        PieceView pieceView = new PieceView();
        System.out.println("Board position is: " + tile.getLocation());
        System.out.println(tile.getModelTile().getNeighbours().size());
        for (ITile neighbour: tile.getModelTile().getNeighbours()) {

            System.out.println("Neighbour: " + neighbour.getLocation());
        }

        if(selectedTile !=null && tileSelected){
            if (gm.getiBoard().movePiece(selectedTile.getiPiece(), tile.getLocation())){
                targetTile = tile;
                IPiece piece = selectedTile.getiPiece();
                piece.setLocationProperty(tile.getLocation());
            }
        }
    }
}
