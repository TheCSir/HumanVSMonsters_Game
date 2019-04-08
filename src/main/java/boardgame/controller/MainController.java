package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.*;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import boardgame.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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
    private Button endTurnButton;

    @FXML
    private Text pieceSelected;

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

    @FXML
    private Button swapButton;

    @FXML
    private Pane SwapPane;

    @FXML
    private Button Opt_one;

    @FXML
    private Button Opt_two;

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

    private HexagonTileViewPiece targetTilePiece = null;

    private IPlayer activePlayer = null;

    // To store swap piece selection values
    private int ButtonOneVal,ButtonTwoVal;


    public MainController() {
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

        attackButton.setOnAction(e -> chooseAttackTarget());

        //swap button action
        swapButton.setOnAction(e -> HandleSwapPane());

        //Swap options clicked
        Opt_one.setOnAction(e -> HandleSwap(1));
        Opt_two.setOnAction(e -> HandleSwap(2));


        addPieces(tiles, pieces, boardPane);
        registerTileListeners(tiles);

        registerPlayerListeners(gm.getPlayers());

        registerTurnListeners(gm.getTurn());

        turnNumber.setText("Turn: " +
                gm.getTurn().getTurnNumber());


        humanHealth.setText("Human Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        monsterHealth.setText("Monster Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        // create a input stream
        try {
            FileInputStream input = new FileInputStream("src/main/resources/proxy.duckduckgo.com.jpeg");
            boardPane.setBackground(new Background(new BackgroundImage(new Image(input), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException e) {
            System.out.println("what");
        }
    }

    private void chooseAttackTarget() {

        // register listener to choose targeted piece to attack
        //registerPlayerListeners();

        for (HexagonTileViewPiece piece : pieceObservableList) {
            if (!piece.getiPiece().equals(selectedTile))
                piece.setOnMouseClicked(event -> handleTargetPieceClicked(piece));
        }
    }

    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.

    private void registerTileListeners(List<HexagonTileView> boardTiles) {

        for (HexagonTileView hexagonalTile : boardTiles) {

            //Set tile handlers
            hexagonalTile.setOnMouseClicked(e -> handleTileClicked(hexagonalTile));
        }
    }
    //Register listeners for the board pieces.

    private void registerPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            PieceView pieceView = new PieceView();
            piece.locationPropertyProperty().addListener((observable) ->
                    pieceView.changePiecePosition(selectedTile, targetTile));
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
        turn.turnNumberProperty().addListener(observable ->
                turnNumber.setText("Turn: " +
                        turn.getTurnNumber())
        );
    }

    private void unRegisterPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            PieceView pieceView = new PieceView();
            piece.locationPropertyProperty().removeListener((observable) ->
                    pieceView.changePiecePosition(selectedTile, targetTile));
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
    private void handlePieceClicked(HexagonTileViewPiece tile) {
        this.selectedTile = tile;
        this.tileSelected = true;
        pieceSelected.setText("Class: " + tile.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + tile.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + tile.getiPiece().getLocation().getY());
    }

    private void handleTargetPieceClicked(HexagonTileViewPiece tile) {
        this.targetTilePiece = tile;
        gm.getTurn().getActivePlayerProperty().get().decreaseHealthProperty();

        for (HexagonTileViewPiece piece : pieceObservableList) {
            piece.setOnMouseClicked(event -> handlePieceClicked(piece));
        }

        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    //Gets input and updates model for piece position.
    private void handleTileClicked(HexagonTileView tile) {
        assert tile != null;
        targetTile = tile;

        //Debugging:
        // System.out.println("Board position is: " + tile.getLocation());
        // System.out.println(tile.getModelTile().getNeighbours().size());
        for (ITile neighbour : tile.getModelTile().getNeighbours()) {

            // System.out.println("Neighbour: " + neighbour.getLocation());
        }

        //Update model.
        if (selectedTile != null && tileSelected) {
            gm.getiBoard().movePiece(selectedTile.getiPiece(), tile.getLocation());
        }

        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    //handle swap clicked
    public void HandleSwapPane () {

        String OptOne,OptTwo;
        int ActivePiece = gm.getTurn().getActivePlayer().getActivePiece();

        //Switch the disabled status
        SwapPane.setVisible(!SwapPane.isVisible());

        switch (ActivePiece) {
            case 0:
                ButtonOneVal=ActivePiece+1;
                ButtonTwoVal=ActivePiece+2;
                break;

            case 1:
                ButtonOneVal=ActivePiece-1;
                ButtonTwoVal=ActivePiece+1;
                break;

            case 2:
                ButtonOneVal=ActivePiece-2;
                ButtonTwoVal=ActivePiece-1;
                break;

        }
        //set button texts according to pieces
        Opt_one.setText(gm.getTurn().getActivePlayer().getPieces().get(ButtonOneVal).getClass().getSimpleName());
        Opt_two.setText(gm.getTurn().getActivePlayer().getPieces().get(ButtonTwoVal).getClass().getSimpleName());
    }

    public void HandleSwap(int button) {


        //To Do: check if active player is human or monster
        int activePiece = gm.getTurn().getActivePlayer().getActivePiece();
        Location currentLocation = gm.getHumanPieces().get(activePiece).getLocation();
        Location outSideTheBoard = new Location(-1,-1);
        int UserChoice;

        // Select which piece to swap according to user input
        if(button==1){ UserChoice = ButtonOneVal;}
        else{ UserChoice = ButtonTwoVal; }

        gm.adjustHumanLocation(activePiece,outSideTheBoard);
        gm.adjustHumanLocation(UserChoice,currentLocation);
        gm.getTurn().getActivePlayer().setActivePiece(UserChoice);

        //debug
        System.out.println("New Locaton of "+gm.getHumanPieces().get(activePiece).getClass().getSimpleName() +
                " is "+ gm.getHumanPieces().get(activePiece).getLocation());

        System.out.println("New Locaton of "+gm.getHumanPieces().get(UserChoice).getClass().getSimpleName() +
                " is "+ gm.getHumanPieces().get(UserChoice).getLocation());

        //set the pane invisible
        SwapPane.setVisible(false);

    }
}
