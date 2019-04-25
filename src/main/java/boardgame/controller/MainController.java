package boardgame.controller;

import boardgame.gameModel.GameManagerFactory;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.IdleState;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.TileView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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

    @FXML
    private Text pieceSelected;

    @FXML
    private Button moveButton;

    @FXML
    private Button attackButton;

    @FXML
    private Button defendButton;

    private GameContext gameContext;

    /**
     * This is the main entry point after the App class is started. The MainController holds handler methods
     * for input actions. It also registers the listeners for the model pieces. As our application follows
     * an observer pattern these listeners will update the view when triggered.
     * The handle methods call the model through the gameManager interface when responding to user input as per the MVC
     * pattern.
     * The MainController class is the main part of application that currently requires major refactoring as
     * it has a bit too much coupling. Whilst it is reasonably cohesive it is highly coupled.
     * Ideally we would also remove a bit of game logic that is stuck here.
     */
    public MainController() {
        //Get a reference to the game manager. Currently sets up a game with default settings.

    }

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


    public Text getPieceSelected() {
        return pieceSelected;
    }

    public Text getPieceLocation() {
        return pieceLocation;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BoardGrid boardGrid = new BoardGrid(boardPane, this);
        //Store a reference to the Game manager for main entry point to game.
        IGameManager gm = GameManagerFactory.createGameManager(boardGrid);
        gm.defaultGameSetup();

        StatusController statusController = new StatusController(gm);


        IdleState idleState = new IdleState();
        gameContext = new GameContext(idleState, boardGrid, gm, this);

        boardGrid.drawBasicGrid(new ArrayList<>(gm.getiBoard().getTiles().values()), TILERADIUS, boardPane);
        gm.setUpGame();
        RegisterListeners registerListeners = RegisterListenerFactory.createRegisterListeners(gm, statusController);

        initialiseHandlers();

        registerListeners.registerPlayerListeners(gm.getPlayers());
        registerListeners.registerTurnListeners(gm.getTurn());

        boardPane.getChildren().add(statusController);
        statusController.setLayoutX(800);

    }

    private void chooseAttackTargetPiece() {

        gameContext.pressAttack();
    }


    //Selects piece.
    public void handlePieceClicked(HexagonTileViewPiece piece) {

        gameContext.selectPiece(piece);

    }

    private void chooseDefenseTargetPiece() {
        System.out.println("Clicked defense");
        gameContext.pressDefence(gameContext);
    }

    //Gets input and updates model for piece position.
    public void handleTileClicked(TileView tile) {
        assert tile != null;
        gameContext.clickTile(tile);

    }

    private void initialiseHandlers() {
        // register piece actions
        moveButton.setOnMouseClicked(e -> handleMoveClicked());
        attackButton.setOnAction(e -> chooseAttackTargetPiece());
        swapButton.setOnAction(e -> SwapController.handleSwapAction(SwapPane, Opt_one, Opt_two, gameContext));
        Opt_one.setOnAction(e -> SwapController.handleSwapOne(gameContext));
        Opt_two.setOnAction(e -> SwapController.handleSwapTwo(gameContext));
        //defense code
        defendButton.setOnAction(e -> chooseDefenseTargetPiece());
    }

    private void handleMoveClicked() {
        gameContext.pressMove();
    }

}
