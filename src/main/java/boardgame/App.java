package boardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane = new AnchorPane();
        Scene content = new Scene(pane, 800 ,600);
        primaryStage.setScene(content);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
