package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.view.StatusController;

public class RegisterListenerFactory {

    public static RegisterListeners createRegisterListeners(MainController mc, IGameManager gm, StatusController tc) {
        return new RegisterListeners(mc, gm, tc);
    }
}
