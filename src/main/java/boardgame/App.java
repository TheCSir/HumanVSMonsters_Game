package boardgame;

import boardgame.controller.MainController;
import boardgame.gameModel.board.Board;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.board.Location;
import boardgame.util.Util;
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

            Board2DHex board =  new Board2DHex();
            board.setUpTiles();

            Group hexGroup = new Group();

            for(HexagonalTile h : board.getHexagonalTiles()) {
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
