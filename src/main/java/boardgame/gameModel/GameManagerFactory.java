package boardgame.gameModel;

import boardgame.controller.MainController;
import javafx.scene.layout.Pane;

/**
 * Game Manager Factory. Decouple Game Manager creation from main class. Easy to extend to multiple
 * different game manager implementations (eg different game  types.)
 */
public class GameManagerFactory {


    /**
     * Create game manager.
     *
     * @return the game manager
     */
    public static GameManager createGameManager(Pane boardPane, MainController mainController) {
        return new GameManager(boardPane, mainController);
    }
}
