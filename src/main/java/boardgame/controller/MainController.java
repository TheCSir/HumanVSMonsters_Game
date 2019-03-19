package boardgame.controller;

/*
 Controller class for main screen.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML // fx:id="turnTime"
    private Text turnTime; // Value injected by FXMLLoader


    double time = 60;

    public MainController () {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turnTime.setText("Turn Time " + time);
        // TODO (don't really need to do anything here).

    }
}
