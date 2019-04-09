package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManager;
import boardgame.gameModel.IPlayer;
import boardgame.gameModel.Location;
import boardgame.gameModel.Turn;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;
import boardgame.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static boardgame.util.Constants.TILERADIUS;

public class MainController implements Initializable {

    @FXML // fx:id="turnTime"
    private Text turnTime; // Value injected by FXMLLoader

    @FXML
    private Pane boardPane;

    @FXML
    private VBox tileInfoPane;

    @FXML
    private Text currentPlayer;

    @FXML
    private Text pieceSelected;

    @FXML
    private Button moveButton;

    @FXML
    private Button attackButton;

    @FXML
    private Text pieceHealth;

    @FXML
    private Text pieceLocation;

    @FXML
    private Label turnNumber;

    @FXML
    private Label humanHealth;

    @FXML
    private Label monsterHealth;

    private enum State{
        MOVE,
        ATTACK,
        SPECIAL_ABILITY,
        DEFENSE,
        SWAP,
        NONE
    }

    private State currentState = State.NONE;
    private ObservableList<HexagonTileView> tiles = FXCollections.observableArrayList();
    private ObservableList<HexagonTileViewPiece> pieceObservableList = FXCollections.observableArrayList();

    double time = 60;
    private List<IPiece> humanPieces;
    private List<IPiece> monsterPieces;
    private List<IPiece> pieces;

    //Store a reference to the Game manager for main entry point to game.
    private GameManager gm;

    private Board2DHex board2DHex;

    private BoardGrid boardGrid;

    private HexagonTileViewPiece selectedPiece = null;

    private boolean tileSelected = false;

    private HexagonTileView targetTile = null;

    private HexagonTileViewPiece targetTilePiece = null;

    private IPlayer activePlayer = null;

    public MainController() {
        //Get a reference to the game manager. Currently sets up a game with default settings.
        gm = new GameManager();

        //Set up default board.
        gm.setUpBoard();
        board2DHex = (Board2DHex) gm.getiBoard();


        humanPieces = gm.setUpHumanPieces();
        monsterPieces =  gm.setUpMonsterPieces();
        pieces = new ArrayList<>();
        pieces.addAll(humanPieces);
        pieces.addAll(monsterPieces);
        //pieces = gm.setUpHumanPieces();

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
        assert boardPane != null : "fx:id=\"boardPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert tileInfoPane != null : "fx:id=\"tileInfoPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceSelected != null : "fx:id=\"pieceSelected\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceHealth != null : "fx:id=\"pieceHealth\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceLocation != null : "fx:id=\"pieceLocation\" was not injected: check your FXML file 'mainView.fxml'.";

        turnTime.setText("Turn Time " + time);

        //register text
        gm.getTurn().getActivePlayerProperty().addListener((observable, oldValue, newValue) ->
                currentPlayer.setText("Current Player: " + newValue.getPlayerName()));

        moveButton.setOnAction(e -> chooseMoveTargetPiece());
        attackButton.setOnAction(e -> chooseAttackTargetPiece());

        addPieces(tiles, pieces, boardPane);
        registerTileListenersForMove(tiles);

        registerPlayerListeners(gm.getPlayers());

        registerTurnListeners(gm.getTurn());

        turnNumber.setText("Turn: " +
                gm.getTurn().getTurnNumber());


        humanHealth.setText("Human Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        monsterHealth.setText("Monster Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        // Set up the background.
        try {
            FileInputStream input = new FileInputStream("src/main/resources/wood_table_background.jpeg");
            boardPane.setBackground(new Background(new BackgroundImage(new Image(input), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException e) {
            System.out.println("what");
        }
    }

    private void chooseMoveTargetPiece() {
        currentState = State.MOVE;
    }

    private void chooseAttackTargetPiece() {
        currentState = State.ATTACK;
    }

    private void chooseAbilityTargetPiece() {
        currentState = State.SPECIAL_ABILITY;
    }

    private void chooseDefenseTargetPiece() {
        currentState = State.DEFENSE;
    }

    private void chooseSwapTargetPiece() {
        currentState = State.SWAP;
    }

    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.

    private void registerTileListenersForMove(List<HexagonTileView> boardTiles) {

        for (HexagonTileView hexagonalTile : boardTiles) {

            //Set tile handlers
            hexagonalTile.setOnMouseClicked(e -> handleTileClickedForMove(hexagonalTile));
        }
    }

    //Register listeners for the board pieces.

    private void registerPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            PieceView pieceView = new PieceView();
            piece.locationPropertyProperty().addListener((observable) ->
                    pieceView.changePiecePosition(selectedPiece, targetTile));
        }
    }

    private void registerPlayerListeners(List<IPlayer> players) {

        for (IPlayer player : players) {
            PlayerView playerView = new PlayerView();
            player.healthProperty().addListener((observable) ->
                    playerView.decreaseHealthBar(player, targetTilePiece));

            if(player.getClass().getSimpleName().equals("HumanPlayer")) {
                player.healthProperty().addListener((observable) ->
                        humanHealth.setText("Human Health: " +
                                player.healthProperty().getValue())
                );
            }

            else if(player.getClass().getSimpleName().equals("MonsterPlayer")) {
                player.healthProperty().addListener((observable) ->
                        monsterHealth.setText("Monster Health: " +
                                player.healthProperty().getValue())
                );
            }
        }
    }

    private void registerTurnListeners(Turn turn){
        // increment Turn number label
        turn.turnNumberProperty().addListener(observable ->
                turnNumber.setText("Turn: " +
                        turn.getTurnNumber())
        );

        // reset currentState
        turn.turnNumberProperty().addListener(observable ->
                currentState = State.NONE
        );
    }

    private void unRegisterPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            PieceView pieceView = new PieceView();
            piece.locationPropertyProperty().removeListener((observable) ->
                    pieceView.changePiecePosition(selectedPiece, targetTile));
        }
    }

    //Add game pieces to the game board.

    private void addPieces(ObservableList<HexagonTileView> tileViewObservableList, List<IPiece> pieceList, Pane boardPane) {

        //Register listeners for the board pieces.
        registerPieceListeners(pieceList);

        pieceObservableList = boardGrid.addPieces(tileViewObservableList, pieceList, boardPane);

        for (HexagonTileViewPiece piece : pieceObservableList) {
            piece.setOnMouseClicked(event -> handlePieceClicked(piece));
        }
    }

    //Selects piece.
    private void handlePieceClicked(HexagonTileViewPiece piece) {
        this.selectedPiece = piece;
        this.tileSelected = true;
        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());

        switch (currentState){
            case MOVE:
                highLightTilesRange(piece);
                break;
            case ATTACK:
                gm.getTurn().getActivePlayerProperty().get().decreaseHealthProperty();
                // end turn
                gm.getTurn().nextTurn(gm.getPlayers());
                break;
            case SPECIAL_ABILITY:
                break;
            case DEFENSE:
                break;
            case SWAP:
                break;
        }
    }

    private void handleTargetPieceClicked(HexagonTileViewPiece tile) {
        this.targetTilePiece = tile;
        gm.getTurn().getActivePlayerProperty().get().decreaseHealthProperty();

        for (HexagonTileViewPiece piece : pieceObservableList) {
            piece.setOnMouseClicked(event -> handlePieceClicked(piece));
        }
    }

    private void highLightTilesRange(HexagonTileViewPiece tile){

        for(HexagonTileView neighbourView : tiles){
            neighbourView.setFill(Color.ANTIQUEWHITE);
        }

        HexagonalTile t = new HexagonalTile(tile.getLocation());
        tile.setModelTile(t);

        IPiece selectedPiece = tile.getiPiece();
        List<ITile> neighbours = board2DHex.getNeighbours(selectedPiece);

        for (ITile neighbour : neighbours) {

            for(HexagonTileView neighbourView : tiles){
                if(neighbour.equals(neighbourView.getModelTile()))
                    neighbourView.setFill(Color.RED);
            }
        }
    }

    //Gets input and updates model for piece position.
    private void handleTileClickedForMove(HexagonTileView tile) {
        assert tile != null;
        targetTile = tile;

        for (ITile neighbour : tile.getModelTile().getNeighbours()) {

            // System.out.println("Neighbour: " + neighbour.getLocation());
        }

        //Update model.
        if (this.selectedPiece != null && tileSelected && currentState.equals(State.MOVE)) {
            gm.getiBoard().movePiece(this.selectedPiece.getiPiece(), tile.getLocation());

            // end turn
            gm.getTurn().nextTurn(gm.getPlayers());
        }
    }
}
