package boardgame.view;

import boardgame.controller.GameController;
import javafx.scene.layout.Pane;

public class BoardGridFactory {

    public static BoardGrid createBoardGrid(Pane boardPane, GameController gc) {
        return new BoardGrid(boardPane, gc);
    }
}
