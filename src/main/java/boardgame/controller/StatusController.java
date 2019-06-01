package boardgame.controller;

import boardgame.gameModel.IGameManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;


/**
 * This class controls the right game status area.
 */
public class StatusController extends VBox {

    private final IGameManager gm;
    private final GameController gc;
    @FXML
    private VBox gameStatus;

    @FXML
    private Label turnNumber;

    @FXML
    private Label humanHealth;

    @FXML
    private Label monsterHealth;

    @FXML
    private Text currentPlayer;


    @FXML
    private Text turnTime;

    @FXML
    private Button beginUndoButton;

    @FXML
    private Button undoButton;

    @FXML
    private Button redoButton;

    @FXML
    private Button finishUndoButton;

    //Button to replay all the moves from the beginning as an animation.
    @FXML
    private Button replay;

    private static final Integer STARTTIME = 60;
    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    /**
     * Gets turn number.
     *
     * @return the turn number
     */
    public Label getTurnNumber() {
        return turnNumber;
    }


    /**
     * Gets human health.
     *
     * @return the human health
     */
    public Label getHumanHealth() {
        return humanHealth;
    }


    /**
     * Gets monster health.
     *
     * @return the monster player's health
     */
    public Label getMonsterHealth() {
        return monsterHealth;
    }

    /**
     * Gets current player text field.
     *
     * @return the text field that displays the current player.
     */
    public Text getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the turn time remaining.
     *
     * @return the text field containg remaining turn time.
     */
    public Text getTurnTime() {
        return turnTime;
    }


    /**
     * Initialise the text fields for the status area.
     */
    public void initialiseTextFields() {

        getCurrentPlayer().setText("Current Player: " + gm.getTurn().getActivePlayer().getPlayerName());
        getTurnNumber().setText("Turn: " +
                gm.getTurn().getTurnNumber());


        getHumanHealth().setText(gm.getPlayers().get(0).getPlayerName() + " Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        getMonsterHealth().setText(gm.getPlayers().get(1).getPlayerName() + " Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        getTurnTime().setText("Turn Time " + 60);
    }

    public void setTurnNumber(String text) {
        turnNumber.setText(text);
    }


    /**
     * Instantiates a new Status controller.
     *
     * @param gm the game manager
     */
    public StatusController(IGameManager gm, GameController gc) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/boardgame/view/status.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.gm = gm;
        this.gc = gc;
        initialiseTextFields();

        beginUndoButton.setOnMouseClicked(event -> beginUndo());
        finishUndoButton.setOnMouseClicked(event -> finishUndo());

        undoButton.setOnMouseClicked(event -> gm.getGameContext().undo());

        redoButton.setOnMouseClicked(event -> gm.getGameContext().redo());

        //Set listener to replay all the moves from the beginning when button is clicked.
        replay.setOnMouseClicked(event -> gm.getGameContext().replayAllMoves());

        gm.getTurn().turnNumberProperty().addListener(observable -> timeline());

        // Bind the timerLabel text property to the timeSeconds property
        turnTime.textProperty().bind(timeSeconds.asString());

        //Add a listener to the timeSeconds IntegerProperty. If
        timeSeconds.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0)
                gm.endTurn();
        });

        //Start so it works on the first turn.
        timeline();
    }

    private void timeline() {

        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 1),
                        new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }


    public void enableBeginUndo() {
        boolean isUndoUsed = gm.getActivePlayer().getIsUndoUsed();
        if (!isUndoUsed && finishUndoButton.isDisabled())
            beginUndoButton.setDisable(false);
        else {
            beginUndoButton.setDisable(true);
        }
    }

    private void beginUndo() {
        /*disable all buttons except for finishUndoButton, undoButton and redoButton
        to force user to finish taking undo/redo action
         */
        gm.getActivePlayer().setIsUndoUsed(true);
        beginUndoButton.setDisable(true);
        replay.setDisable(true);
        gc.setActionButtonsDisable(true);

        finishUndoButton.setDisable(false);
        undoButton.setDisable(false);
        redoButton.setDisable(false);
    }

    private void finishUndo() {
        finishUndoButton.setDisable(true);
        undoButton.setDisable(true);
        redoButton.setDisable(true);
        replay.setDisable(false);
        gc.setActionButtonsDisable(false);
    }
}

