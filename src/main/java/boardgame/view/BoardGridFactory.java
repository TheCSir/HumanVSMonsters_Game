package boardgame.view;

import boardgame.controller.GameController;
import javafx.scene.layout.Pane;

public class BoardGridFactory {

    public static BoardGrid createBoardGrid(Pane boardPane, GameController mc) {
        return new BoardGrid(boardPane, mc);
    }
}
