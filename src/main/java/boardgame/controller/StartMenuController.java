package boardgame.controller;

import boardgame.gameModel.IGameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    private TextField numberOfPlayersText;
    @FXML
    private TextField gridWidthText;
    @FXML
    private TextField gridHeightText;
    @FXML
    private Button setSettingsButton;


    public StartMenuController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSettingsButton.setOnAction(e -> setSettings());
    }

    private void setSettings(){}
}
