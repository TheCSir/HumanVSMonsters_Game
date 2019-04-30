package boardgame.controller;

import boardgame.gameModel.IGameManager;

public class RegisterListenerFactory {

    public static RegisterListeners createRegisterListeners(IGameManager gm, StatusController tc) {
        return new RegisterListeners(gm, tc);
    }
}
