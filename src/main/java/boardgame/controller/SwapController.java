package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.state.GameContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class SwapController {


    public static void handleSwapAction(Pane SwapPane, IGameManager gm, Button Opt_one, Button Opt_two, GameContext gc) {

        gc.pressSwapButton(SwapPane, Opt_one, Opt_two);

    }

    public static void handleSwapOne(Pane swapPane, Button opt_one, GameContext gc) {
        gc.pressSwapOne(swapPane, opt_one);

    }

    public static void handleSwapTwo(Pane swapPane, Button opt_two, GameContext gc) {
        gc.pressSwapTwo(swapPane, opt_two);
    }
}
