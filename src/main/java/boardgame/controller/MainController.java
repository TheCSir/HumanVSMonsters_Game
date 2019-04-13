package boardgame.controller;

import boardgame.gameModel.GameManagerFactory;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.StatusController;
import boardgame.view.TileView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static boardgame.util.Constants.TILERADIUS;

/**
 * The Main Controller. This is a JavaFX Controller.
 */
public class MainController implements Initializable {


    @FXML
    private Pane boardPane;

    private RegisterListeners registerListeners;

    @FXML
    private Text pieceSelected;

    @FXML
    private Button moveButton;

    @FXML
    private Button attackButton;

    @FXML
    private Button defendButton;


    @FXML
    private Text pieceLocation;

    @FXML
    private Button swapButton;

    @FXML
    private Pane SwapPane;

    @FXML
    private Button Opt_one;

    @FXML
    private Button Opt_two;
    private GameController gameController;

    private State currentState = State.NONE;

    //Store a reference to the Game manager for main entry point to game.
    private IGameManager gm;

    private BoardGrid boardGrid;


    /**
     * This is the main entry point after the App class is started. The MainController holds handler methods
     * for input actions. It also registers the listeners for the model pieces. As our application follows
     * an observer pattern these listeners will update the view when triggered.
     * The handle methods call the model through the gameManager interface when responding to user input as per the MVC
     * pattern.
     * The MainController class is the main part of application that currently requires major refactoring as
     * it has a bit too much coupling. Whilst it is reasonably cohesive it is highly coupled.
     * Ideally we would also remove a bit of game logic that is stuck here.
     *
     */
    public MainController() {
        //Get a reference to the game manager. Currently sets up a game with default settings.
        gm = GameManagerFactory.createGameManager();
        gm.defaultGameSetup();
    }


    public RegisterListeners getRegisterListeners() {
        return registerListeners;
    }


    public BoardGrid getBoardGrid() {
        return boardGrid;
    }

    public GameController getGameController() {
        return gameController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StatusController statusController = new StatusController(gm);

        boardGrid = new BoardGrid(boardPane);
        boardGrid.drawBasicGrid(new ArrayList<>(gm.getiBoard().getTiles().values()), TILERADIUS, boardPane);
        registerListeners = RegisterListenerFactory.createRegisterListeners(this, gm, statusController);

        initialiseHandlers();

        registerListeners.registerTileListenersForMove(boardGrid.getHexagonTileViews());
        registerListeners.registerPlayerListeners(gm.getPlayers());
        registerListeners.registerTurnListeners(gm.getTurn());

        gameController = GameControllerFactory.createGameController(gm, boardGrid, this);
        gameController.setUpGame();

        boardPane.getChildren().add(statusController);
        statusController.setLayoutX(800);

        registerListeners.registerPieceListListener();
    }

    private void chooseAttackTargetPiece() {
        currentState = State.ATTACK;

        if (boardGrid.getSelectedTilePiece() != null)
            boardGrid.setNeighbourTilesColor(boardGrid.getSelectedTilePiece(), Color.ANTIQUEWHITE);
    }

    //Selects piece.
    public void handlePieceClicked(HexagonTileViewPiece piece) {
        // Reset tiles color
        if (boardGrid.getSelectedTilePiece() != null)
            boardGrid.setNeighbourTilesColor(boardGrid.getSelectedTilePiece(), Color.ANTIQUEWHITE);

        boardGrid.setSelectedTilePiece(piece);
        boardGrid.setTileSelected(true);

        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());


        switch (currentState){
            case MOVE:
                if(isActivePlayerPiece())
                    boardGrid.setNeighbourTilesColor(boardGrid.getSelectedTilePiece(), Color.RED);
                break;
            case ATTACK:
                if(!isActivePlayerPiece()) {

                    // get attacked player
                    gm.getAttackedPlayer(boardGrid.getSelectedTilePiece().getiPiece()).decreaseHealthProperty(boardGrid.getSelectedTilePiece().getiPiece());

                    // end turn
                    gm.getTurn().nextTurn(gm.getPlayers());
                }
                break;
            case SPECIAL_ABILITY:
                break;
            case DEFENSE:
                if (isActivePlayerPiece()) {
                    // Get active player and create shield
                    boardGrid.getSelectedTilePiece().getiPiece().createShield(gm.getTurn().getTurnNumber());

                    // end turn
                    gm.getTurn().nextTurn(gm.getPlayers());
                }
                break;
            case SWAP:
                break;
        }
    }

    private void chooseDefenseTargetPiece() {
        currentState = State.DEFENSE;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    //Gets input and updates model for piece position.
    public void handleTileClicked(TileView tile) {
        assert tile != null;
        boardGrid.setTargetTile(tile);

        if (boardGrid.getSelectedTilePiece() != null && boardGrid.isTileSelected() && isActivePlayerPiece() && currentState.equals(State.MOVE)) {
            // Reset tiles color
            boardGrid.setNeighbourTilesColor(boardGrid.getSelectedTilePiece(), Color.ANTIQUEWHITE);

            //Update model.
            boolean pieceMoved = gm.getiBoard().movePiece(boardGrid.getSelectedTilePiece().getiPiece(), tile.getLocation());

            if (pieceMoved) {
                // end turn
                gm.getTurn().nextTurn(gm.getPlayers());
            }
        }
    }
    private void initialiseHandlers() {
        // register piece actions
        moveButton.setOnMouseClicked(e -> handleMoveClicked());
        attackButton.setOnAction(e -> chooseAttackTargetPiece());
        swapButton.setOnAction(e -> SwapController.handleSwapAction(SwapPane, gm, Opt_one, Opt_two));
        Opt_one.setOnAction(e -> SwapController.doSwap(gm, SwapPane, Opt_one));
        Opt_two.setOnAction(e -> SwapController.doSwap(gm, SwapPane, Opt_two));
        //defense code
        defendButton.setOnAction(e -> chooseDefenseTargetPiece());
    }

    private void handleMoveClicked() {
        currentState = State.MOVE;
        if (boardGrid.getSelectedTilePiece() != null && boardGrid.isTileSelected() && isActivePlayerPiece()) {
            boardGrid.setNeighbourTilesColor(boardGrid.getSelectedTilePiece(), Color.RED);
        }
    }

    // Checks if selected piece belongs to the active player
    private boolean isActivePlayerPiece() {
        if (boardGrid.getSelectedTilePiece() == null)
            return false;

        for (IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if (piece.getClass().getSimpleName().equals(boardGrid.getSelectedTilePiece().getiPiece().getClass().getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    public enum State {
        /**
         * Move state.
         */
        MOVE,
        /**
         * Attack state.
         */
        ATTACK,
        /**
         * Special ability state.
         */
        SPECIAL_ABILITY,
        /**
         * Defense state.
         */
        DEFENSE,
        /**
         * Swap state.
         */
        SWAP,
        /**
         * None state.
         */
        NONE
    }
}
