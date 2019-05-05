package boardgame.gameModel.state;

public class SwapState extends OwnPieceSelected {


    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Already in swap state");
    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        System.out.println("Selecting first option");
        GameContext.doSwap(gameContext.getGm(), gameContext.getSwapPane(), gameContext.getOpt_one());
        gameContext.setState(new IdleState());
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        System.out.println("Selecting second option.");
        GameContext.doSwap(gameContext.getGm(), gameContext.getSwapPane(), gameContext.getOpt_two());
        gameContext.setState(new IdleState());
    }

}
