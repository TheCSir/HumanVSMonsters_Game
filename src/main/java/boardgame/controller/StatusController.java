package boardgame.controller;

import boardgame.gameModel.IGameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;


/**
 * This class controls the right game status area.
 */
public class StatusController extends VBox {

    private final IGameManager gm;
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
    private Button undoButton;


    @FXML
    private Button redoButton;

    /**
     * Instantiates a new Status controller.
     *
     * @param gm the game manager
     */
    public StatusController(IGameManager gm) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/boardgame/view/status.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.gm = gm;
        initialiseTextFields();

        undoButton.setOnMouseClicked(event -> gm.getGameContext().undo());

        redoButton.setOnMouseClicked(event -> gm.getGameContext().redo());

    }

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


        getHumanHealth().setText("Gandalf Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        getMonsterHealth().setText("Sauron Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        getTurnTime().setText("Turn Time " + 60);
    }

    public void setTurnNumber(String text) {
        turnNumber.setText(text);
    }

}

