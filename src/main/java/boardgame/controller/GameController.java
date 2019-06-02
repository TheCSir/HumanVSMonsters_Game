package boardgame.controller;

import boardgame.gameModel.GameManagerFactory;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.states;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.TileView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Game Controller. This is a JavaFX Controller.
 */
public class GameController implements Initializable {


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

    @FXML
    private Button specialAbilityButton;

    @FXML
    private Text pieceLocation;

    @FXML
    private Text pieceHealth;

    @FXML
    private Button swapButton;

    @FXML
    private Pane SwapPane;

    @FXML
    private Button Opt_one;

    @FXML
    private Button Opt_two;



    private GameContext gameContext;

    private final String humanPlayerName;
    private final String monsterPlayerName;
    private final int numOfPieces;
    private final int gridRowsNum;
    private final int gridColumnsNum;

    /**
     * The GameController holds handler methods for input actions. It also registers the listeners for the model pieces.
     * As our application follows an observer pattern these listeners will update the view when triggered.
     * The handle methods call the model through the gameManager interface when responding to user input as per the MVC
     * pattern.
     * The GameController class is the main part of application that currently requires major refactoring as
     * it has a bit too much coupling. Whilst it is reasonably cohesive it is highly coupled.
     * Ideally we would also remove a bit of game logic that is stuck here.
     */
    public GameController(String humanPlayerName, String monsterPlayerName,
                          int numberOfPieces, int gridRows, int gridColumns) {

        this.humanPlayerName = humanPlayerName;
        this.monsterPlayerName = monsterPlayerName;
        this.numOfPieces = numberOfPieces;
        this.gridRowsNum = gridRows;
        this.gridColumnsNum = gridColumns;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/boardgame/view/gameView.fxml"));
        fxmlLoader.setController(this);

        try {
            Parent root = fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Battle Game");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Store a reference to the Game manager for main entry point to game.
        IGameManager gm = GameManagerFactory.createGameManager(boardPane, this);
        // gm.defaultGameSetup();

        gm.customGameSetup(this.humanPlayerName, this.monsterPlayerName,
                this.numOfPieces, this.gridRowsNum, this.gridColumnsNum);
        gm.setUpGame();

        StatusController statusController = new StatusController(gm, this);

        gameContext = gm.getGameContext();

        //Avoids null pointer when setting up game.
        gameContext.setPieceSelected(gm.getActivePlayer().getPieces().get(0));
        //Bind text property for selected piece.
        pieceSelected.textProperty().bind(gameContext.pieceNamePropertyProperty());
        pieceLocation.textProperty().bind(gameContext.pieceLocationProperty());

        specialAbilityButton.textProperty().bind(gameContext.specialAbilityDescriptionProperty());
        RegisterListeners registerListeners = RegisterListenerFactory.createRegisterListeners(gm, statusController);

        initialiseHandlers();

        registerListeners.registerPlayerListeners(gm.getPlayers());
        registerListeners.registerTurnListeners();

        boardPane.getChildren().add(statusController);
        statusController.setLayoutX(800);

        Opt_one.textProperty().bind(gameContext.swapAlternativeOneProperty());
        Opt_two.textProperty().bind(gameContext.swapAlternativeTwoProperty());

        //Set state for turn 1.
        gameContext.setState(states.IDLE);

    }

    private void chooseAttackTargetPiece() {
        //Update Model.
        gameContext.pressAttack();
    }


    //Selects piece.
    public void handlePieceClicked(HexagonTileViewPiece piece) {

        //Update model.
        gameContext.selectPiece(piece);

    }

    public void toggleMinionSelectionOn(String healthText) {

        defendButton.setDisable(true);
        specialAbilityButton.setDisable(true);
        swapButton.setDisable(true);
        pieceHealth.setText(healthText);
    }

    public void specialAbilityOnCD() {
        specialAbilityButton.setDisable(false);

    }

    public void specialAbilityOffCD() {
        specialAbilityButton.setDisable(true);
    }

    public void toggleMinionSelectionOff() {
        defendButton.setDisable(false);
        specialAbilityButton.setDisable(false);
        swapButton.setDisable(false);
        pieceHealth.setText("");
    }

    private void handleMoveClicked() {
        gameContext.pressMove();
    }

    private void handleSwapAction(GameContext gc) {
        gc.pressSwapButton();
    }

    private void handleSwapOne(GameContext gc) {
        gc.pressSwapOne();
    }

    private void handleSwapTwo(GameContext gc) {
        gc.pressSwapTwo();
    }

    private void chooseDefenseTargetPiece() {
        gameContext.pressDefence();
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
        swapButton.setOnAction(e -> handleSwapAction(gameContext));
        Opt_one.setOnAction(e -> handleSwapOne(gameContext));
        Opt_two.setOnAction(e -> handleSwapTwo(gameContext));
        //defense code
        defendButton.setOnAction(e -> chooseDefenseTargetPiece());
        specialAbilityButton.setOnMouseClicked(e -> handleSpecialClicked());
    }

    private void handleSpecialClicked() {
        gameContext.pressSpecial();
    }

    public void setSwapPaneVisible(boolean b) {
        SwapPane.setVisible(b);
    }

    public void setActionButtonsDisable(boolean isDisable){
        moveButton.setDisable(isDisable);
        attackButton.setDisable(isDisable);
        specialAbilityButton.setDisable(isDisable);
        defendButton.setDisable(isDisable);
        swapButton.setDisable(isDisable);
    }
}
