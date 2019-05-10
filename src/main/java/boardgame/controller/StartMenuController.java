package boardgame.controller;

import boardgame.gameModel.IGameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
    //private final IGameManager gm;
    @FXML
    private TextField humanPlayerNameText;
    @FXML
    private TextField monsterPlayerNameText;
    @FXML
    private TextField numberOfPiecesText;
    @FXML
    private TextField gridRowsText;
    @FXML
    private TextField gridColumnsText;
    @FXML
    private Button setSettingsButton;


    public StartMenuController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSettingsButton.setOnAction(e -> setSettings());
    }

    private void setSettings() {
        int numOfPlayers, gridRowsNum, gridColumnsNum = 0;
        try {
            numOfPlayers = Integer.parseInt(numberOfPiecesText.getText());
            gridRowsNum = Integer.parseInt(gridRowsText.getText());
            gridColumnsNum = Integer.parseInt(gridColumnsText.getText());

        } catch (Exception ex) {
            throw ex;
        }

        GameController gc = new GameController(humanPlayerNameText.getText(),
                monsterPlayerNameText.getText(), numOfPlayers, gridRowsNum, gridColumnsNum);
    }
}
