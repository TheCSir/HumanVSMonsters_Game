package boardgame;

import boardgame.controller.MainController;
import boardgame.gameModel.tiles.HexagonalTile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

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

            ArrayList<HexagonalTile> hexList = new ArrayList<>();
            hexList.add(new HexagonalTile(3, 4));
            hexList.add(new HexagonalTile(3, 3));
            hexList.add(new HexagonalTile(4, 3));
            hexList.add(new HexagonalTile(4, 4));
            hexList.add(new HexagonalTile(4, 5));

            Group hexGroup = new Group();

            for(HexagonalTile h : hexList) {
                h.setFill(Color.RED);
                h.setStroke(Color.BLACK);
                h.setStrokeWidth(2);

                hexGroup.getChildren().add(h);
            }

            Scene hexScene = new Scene(hexGroup, 400, 400);
            primaryStage.setScene(hexScene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(args); }
}
