package boardgame.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
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

            if(numOfPieces < 2 || numOfPieces > 3) {
                piecseNumValidation.setText("Number of pieces must be from 2-3.");
                valid = false;
            }

        } catch (Exception ex) {
            piecseNumValidation.setText("Number of pieces must be from 2-3.");
            valid = false;
        }

        try {
            gridRowsNum = Integer.parseInt(gridRowsText.getText());
            gridColumnsNum = Integer.parseInt(gridColumnsText.getText());

            boolean validateSize = validateSize(gridRowsNum, gridColumnsNum);
            if(!validateSize) {
                gridValidation.setText("Grid Rows and Columns must be within 10 - 15");
                valid = false;
            }

        } catch (Exception ex) {
            gridValidation.setText("Grid Rows and Columns must be within 10 - 15");
            valid = false;
        }

        if(valid) {
            GameController gc = new GameController(humanPlayerNameText.getText(),
                    monsterPlayerNameText.getText(), numOfPieces, gridRowsNum, gridColumnsNum);

            // hide main menu
            Stage stage = (Stage) humanPlayerNameText.getScene().getWindow();
            stage.hide();
        }
    }

    private boolean validateSize(int row, int column){
        int minSize = 10, maxSize = 15;
        if(row <= maxSize && row >= minSize && column <= maxSize && column >= minSize){
            return true;
        }
        return false;
    }
}
