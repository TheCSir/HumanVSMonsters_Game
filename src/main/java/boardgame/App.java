package boardgame;

import boardgame.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
    Entry class to application.

 */

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {

            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/boardgame/view/mainView.fxml"));

            primaryStage.setTitle("Battle Game");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
