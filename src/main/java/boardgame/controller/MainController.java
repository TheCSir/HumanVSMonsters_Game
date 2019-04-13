package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManagerFactory;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.gameModel.pieces.*;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private Button defendButton;

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

    private enum State{
        MOVE,
        ATTACK,
        SPECIAL_ABILITY,
        DEFENSE,
        SWAP,
        NONE
    }

    private State currentState = State.NONE;

    //Store a reference to the Game manager for main entry point to game.
    private IGameManager gm;

    private BoardGrid boardGrid;

    private HexagonTileViewPiece selectedTilePiece = null;

    private boolean tileSelected = false;

    private TileView targetTile = null;

    private HexagonTileViewPiece targetTilePiece = null;

    private String PieceSelectionOne;

    private String PieceSelectionTwo;

    public MainController() {
        //Get a reference to the game manager. Currently sets up a game with default settings.
        gm = GameManagerFactory.createGameManager();

        gm.defaultGameSetup();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardGrid = new BoardGrid(boardPane);
        boardGrid.drawBasicGrid(new ArrayList<>(gm.getiBoard().getTiles().values()), TILERADIUS, boardPane);
        assertJFXInjection();


        // register piece actions
        moveButton.setOnMouseClicked(e -> handleMoveClicked());
        attackButton.setOnAction(e -> chooseAttackTargetPiece());
        swapButton.setOnAction(e -> SwapController.handleSwapAction(SwapPane, gm, Opt_one, Opt_two));
        Opt_one.setOnAction(e -> SwapController.doSwap(gm,SwapPane,Opt_one));
        Opt_two.setOnAction(e -> SwapController.doSwap(gm,SwapPane,Opt_two));
        //defense code
        defendButton.setOnAction(e -> chooseDefenseTargetPiece());
        //end


        registerTileListenersForMove(boardGrid.getHexagonTileViews());
        registerPlayerListeners(gm.getPlayers());
        registerTurnListeners(gm.getTurn());

        initialiseTextFields();

        addPieces(gm.getAllPieces());
        registerPieceListListener();
        gm.testPieces();

    }

    private void initialiseTextFields() {

        currentPlayer.setText("Current Player: " + gm.getTurn().getActivePlayer().getPlayerName());
        turnNumber.setText("Turn: " +
                gm.getTurn().getTurnNumber());


        humanHealth.setText("Gandalf Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        monsterHealth.setText("Sauron Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        turnTime.setText("Turn Time " + 60);
    }

    private void assertJFXInjection() {
        //Assertions to ensure that injection works
        assert currentPlayer != null : "fx:id=\"currentPlayer\" was not injected: check your FXML file 'mainView.fxml'.";
        assert turnTime != null : "fx:id=\"turnTime\" was not injected: check your FXML file 'mainView.fxml'.";
        assert boardPane != null : "fx:id=\"boardPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert tileInfoPane != null : "fx:id=\"tileInfoPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceSelected != null : "fx:id=\"pieceSelected\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceHealth != null : "fx:id=\"pieceHealth\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceLocation != null : "fx:id=\"pieceLocation\" was not injected: check your FXML file 'mainView.fxml'.";
    }

    private void chooseAttackTargetPiece() {
        currentState = State.ATTACK;

        if(selectedTilePiece != null)
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.ANTIQUEWHITE);
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


    private void registerTileListenersForMove(List<TileView> boardTiles) {

        for (TileView hexagonalTile : boardTiles) {

            //Set tile handlers
            hexagonalTile.setOnMouseClicked(e -> handleTileClicked(hexagonalTile));
        }
    }

    //Register listeners for the board pieces.
    private void registerPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            registerPieceListener(piece);
        }
    }

    private void registerPlayerListeners(List<IPlayer> players) {

        for (IPlayer player : players) {
            PlayerView playerView = new PlayerView();
            player.healthProperty().addListener((observable) ->
                    playerView.decreaseHealthBar(player, targetTilePiece));

            if(player.getClass().getSimpleName().equals("HumanPlayer")) {
                player.healthProperty().addListener((observable) ->
                        humanHealth.setText("Gandalf Health: " +
                                player.healthProperty().getValue())
                );
            }

            else if(player.getClass().getSimpleName().equals("MonsterPlayer")) {
                player.healthProperty().addListener((observable) ->
                        monsterHealth.setText("Sauron Health: " +
                                player.healthProperty().getValue())
                );
            }
        }
    }

    private void registerPieceListListener() {
        for (IPlayer iPlayer : gm.getPlayers()) {
            ObservableList<IPiece> pieces = iPlayer.getPieces();
            pieces.addListener((ListChangeListener<IPiece>) c -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (IPiece piece : c.getAddedSubList()) {
                            addPiece(piece);
                        }
                    } else if (c.wasRemoved()) {
                        c.getRemoved();
                        for (IPiece piece : c.getRemoved()) {
                            removePiece(piece);
                        }
                    }
                }
            });
        }

    }

    private void removePiece(IPiece piece) {
        boardGrid.removePiece(piece, boardPane);
    }

    private void registerTurnListeners(Turn turn){
        // increment Turn number label
        turn.turnNumberProperty().addListener(observable ->
                turnNumber.setText("Turn: " +
                        turn.getTurnNumber())
        );

        // Change Current Player label
        gm.getTurn().getActivePlayerProperty().addListener(observable ->
                currentPlayer.setText("Current Player: " + turn.getActivePlayer().getPlayerName()));

        // reset currentState
        turn.turnNumberProperty().addListener(observable ->
                currentState = State.NONE
        );
    }

    private void unRegisterPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            piece.locationPropertyProperty().removeListener((observable) ->
                    PieceView.changePiecePosition(selectedTilePiece, targetTile));
        }
    }

    //Add game pieces to the game board.
    private void addPieces(List<IPiece> pieceList) {

        for (IPiece piece : pieceList) {
            addPiece(piece);
        }
    }

    private void addPiece(IPiece piece) {
        //Register move listener
        registerPieceListener(piece);

        boardGrid.addPiece(piece, boardPane);

        //set view piece handler.
        for (HexagonTileViewPiece hexagonTileViewPiece : boardGrid.getPieceObservableList()) {
            if (hexagonTileViewPiece.getiPiece().equals(piece)) {
                hexagonTileViewPiece.setOnMouseClicked(event -> handlePieceClicked(hexagonTileViewPiece));
            }
        }

    }

    private void registerPieceListener(IPiece piece) {
        piece.locationPropertyProperty().addListener((observable) ->
                PieceView.changePiecePosition(selectedTilePiece, targetTile));
    }

    //Selects piece.
    private void handlePieceClicked(HexagonTileViewPiece piece) {
        // Reset tiles color
        if(selectedTilePiece != null)
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.ANTIQUEWHITE);

        this.selectedTilePiece = piece;
        this.tileSelected = true;

        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());


        switch (currentState){
            case MOVE:
                if(isActivePlayerPiece())
                    boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.RED);
                break;
            case ATTACK:
                if(!isActivePlayerPiece()) {
                    // get attacked player
                    IPlayer attackedPLayer = gm.getAttackedPlayer(selectedTilePiece.getiPiece());

                    attackedPLayer.decreaseHealthProperty();

                    // end turn
                    gm.getTurn().nextTurn(gm.getPlayers());
                }
                break;
            case SPECIAL_ABILITY:
                break;
            case DEFENSE:
                if (isActivePlayerPiece()) {
                    // get defense player
                    IPlayer defensePlayer = gm.getAttackedPlayer(selectedTilePiece.getiPiece());

                    defensePlayer.createShield();
                    // end turn
                    gm.getTurn().nextTurn(gm.getPlayers());
                }
                break;
            case SWAP:
                break;
        }
    }

    //Gets input and updates model for piece position.
    private void handleTileClicked(TileView tile) {
        assert tile != null;
        targetTile = tile;

        if (selectedTilePiece != null && tileSelected && isActivePlayerPiece() && currentState.equals(State.MOVE)) {
            // Reset tiles color
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.ANTIQUEWHITE);

            //Update model.
            boolean pieceMoved = gm.getiBoard().movePiece(selectedTilePiece.getiPiece(), tile.getLocation());

            if(pieceMoved) {
                // end turn
                gm.getTurn().nextTurn(gm.getPlayers());
            }
        }
    }

    private void handleMoveClicked() {
        currentState = State.MOVE;
        if (selectedTilePiece != null && tileSelected && isActivePlayerPiece()) {
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.RED);
        }
    }

    // Checks if selected piece belongs to the active player
    private boolean isActivePlayerPiece(){
        if(selectedTilePiece == null)
            return false;

        for(IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if(piece.getClass().getSimpleName().equals(selectedTilePiece.getiPiece().getClass().getSimpleName())) {
                return true;
            }
        }

        return false;
    }


    public void setPieceSelectionTwo(String pieceSelectionTwo) {
        PieceSelectionTwo = pieceSelectionTwo;
    }

    public void setPieceSelectionOne(String pieceSelectionOne) {
        PieceSelectionOne = pieceSelectionOne;
    }


}
