package boardgame.gameModel.state;

public class SwapState extends OwnPieceSelected {


    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Already in swap state");
    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        System.out.println("Selecting first option");
        gameContext.swapOne();
        gameContext.setState(states.IDLE);
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        System.out.println("Selecting second option.");
        gameContext.swapTwo();
        gameContext.setState(states.IDLE);
    }
}
