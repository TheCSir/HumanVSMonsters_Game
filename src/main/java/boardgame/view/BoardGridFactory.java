package boardgame.view;

import boardgame.controller.MainController;
import javafx.scene.layout.Pane;

public class BoardGridFactory {

    public static BoardGrid createBoardGrid(Pane boardPane, MainController mc) {
        return new BoardGrid(boardPane, mc);
    }
}
