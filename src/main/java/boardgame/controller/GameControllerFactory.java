package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.view.BoardGrid;

public class GameControllerFactory {

    public static GameController createGameController(IGameManager gm, BoardGrid bg, MainController mc) {
        return new GameController(gm, bg, mc);
    }
}
