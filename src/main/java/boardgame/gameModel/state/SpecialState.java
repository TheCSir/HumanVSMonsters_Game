package boardgame.gameModel.state;

public class SpecialState extends OwnPieceSelected {

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Already in special state");
    }

    @Override
    public void onUseSpecial(GameContext gameContext) {

        gameContext.launchSpecialAbility();

        gameContext.setState(new IdleState());
    }
}
