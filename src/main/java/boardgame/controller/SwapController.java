package boardgame.controller;

import boardgame.gameModel.state.GameContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class SwapController {


    public static void handleSwapAction(Pane SwapPane, Button Opt_one, Button Opt_two, GameContext gc) {

        gc.pressSwapButton(SwapPane, Opt_one, Opt_two);

    }

    public static void handleSwapOne(GameContext gc) {
        gc.pressSwapOne();

    }

    public static void handleSwapTwo(GameContext gc) {
        gc.pressSwapTwo();
    }
}
