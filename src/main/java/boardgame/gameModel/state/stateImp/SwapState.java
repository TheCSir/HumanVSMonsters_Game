package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.states;

public class SwapState extends OwnPieceSelected {


    @Override
    public void onSwap(GameContext gameContext) {
        gameContext.setState(states.SWAP);
    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        gameContext.swapOne();
        gameContext.setState(states.IDLE);
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        gameContext.swapTwo();
        gameContext.setState(states.IDLE);
    }
}
