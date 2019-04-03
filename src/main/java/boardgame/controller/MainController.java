package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManager;
import boardgame.gameModel.Location;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileView;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.PieceView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static boardgame.util.Constants.TILERADIUS;

public class MainController implements Initializable {

    //TODO Move all these variables out of main controller class so they can be dynamically assigned.



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

    private ObservableList<HexagonTileView> tiles = FXCollections.observableArrayList();
    private ObservableList<HexagonTileViewPiece> pieceObservableList = FXCollections.observableArrayList();

    double time = 60;
    private List<IPiece> pieces;


    //Store a reference to the Game manager for main entry point to game.
    private GameManager gm;

    private Board2DHex board2DHex;

    private BoardGrid boardGrid;

    private HexagonTileViewPiece selectedTile = null;

    private boolean tileSelected = false;

    private HexagonTileView targetTile = null;

    public MainController () {
        //Get a reference to the game manager. Currently sets up a game with default settings.
        gm = new GameManager();

        //Set up default board.

        gm.setUpBoard();
        board2DHex = (Board2DHex) gm.getiBoard();

        pieces = gm.setUpHumanPieces();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Map<Location, ITile> board = board2DHex.getTiles();
        List<ITile> boardTiles = new ArrayList<>(board.values());

        boardGrid = new BoardGrid();
        tiles = boardGrid.drawBasicGrid(boardTiles, TILERADIUS, boardPane);


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



        //register text
        gm.playerProperty().addListener((observable, oldValue, newValue) -> currentPlayer.setText("Current Player: " + newValue));
        endTurnButton.setOnAction(e ->
                gm.changeActivePlayer());


        addPieces(tiles, pieces, boardPane);
        registerTileListeners(tiles);
    }
    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.

    private void registerTileListeners(List<HexagonTileView> boardTiles) {

        for (HexagonTileView hexagonalTile: boardTiles) {

            //Set tile handlers
            hexagonalTile.setOnMouseClicked(e -> handleTileClicked(hexagonalTile));
        }
    }
    //Register listeners for the board pieces.

    private void registerPieceListeners (List<IPiece> pieces) {

        for (IPiece piece: pieces) {
            PieceView pieceView = new PieceView();
            piece.locationPropertyProperty().addListener((observable) ->
            pieceView.changePiecePosition(selectedTile, targetTile));
        }
    }
    //Add game pieces to the game board.

    private void addPieces(ObservableList<HexagonTileView> tileViewObservableList, List<IPiece> pieceList, Pane boardPane) {

        //Register listeners for the board pieces.
        registerPieceListeners(pieceList);


        ObservableList<HexagonTileViewPiece> pieces = boardGrid.addPieces(tileViewObservableList, pieceList, boardPane);

        for (HexagonTileViewPiece piece: pieces) {
            piece.setOnMouseClicked(event -> handlePieceClicked(piece));
        }
    }

    //Selects piece.
    private void handlePieceClicked(HexagonTileViewPiece tile){
        this.selectedTile = tile;
        this.tileSelected = true;
        pieceSelected.setText("Class: " + tile.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
        + "X: " + tile.getiPiece().getLocation().getX()
        + ", "
        + "Y: " + tile.getiPiece().getLocation().getY());
        pieceHealth.setText(
                "Health: " + tile.getiPiece().healthProperty().getValue());

    }

   //Gets input and updates model for piece position.
    private void handleTileClicked(HexagonTileView tile) {
        targetTile = tile;

        //Debugging:
        System.out.println("Board position is: " + tile.getLocation());
        System.out.println(tile.getModelTile().getNeighbours().size());
        for (ITile neighbour : tile.getModelTile().getNeighbours()) {

            System.out.println("Neighbour: " + neighbour.getLocation());
        }

        //Update model.
        if (selectedTile != null && tileSelected) {
            gm.getiBoard().movePiece(selectedTile.getiPiece(), tile.getLocation());
            }
        }
}
