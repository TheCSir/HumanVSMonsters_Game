package boardgame.controller;

import boardgame.gameModel.IGameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label piecseNumValidation;
    @FXML
    private Label gridValidation;


    public StartMenuController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSettingsButton.setOnAction(e -> setSettings());
    }

    private void setSettings() {
        boolean valid = true;
        piecseNumValidation.setText("");
        gridValidation.setText("");

        int numOfPieces = 0, gridRowsNum = 0, gridColumnsNum = 0;
        try {
            numOfPieces = Integer.parseInt(numberOfPiecesText.getText());

            if(numOfPieces < 1 || numOfPieces > 3) {
                piecseNumValidation.setText("Number of pieces must be from 1-3.");
                valid = false;
            }

        } catch (Exception ex) {
            piecseNumValidation.setText("Number of pieces must be from 1-3.");
            valid = false;
        }

        try {
            gridRowsNum = Integer.parseInt(gridRowsText.getText());
            gridColumnsNum = Integer.parseInt(gridColumnsText.getText());

            if(gridRowsNum < 1 || gridColumnsNum < 1){
                gridValidation.setText("Grid Rows and Columns must be positive numbers.");
                valid = false;
            }

        } catch (Exception ex) {
            gridValidation.setText("Grid Rows and Columns must be positive numbers.");
            valid = false;
        }

        if(valid) {
            GameController gc = new GameController(humanPlayerNameText.getText(),
                    monsterPlayerNameText.getText(), numOfPieces, gridRowsNum, gridColumnsNum);
        }
    }
}
