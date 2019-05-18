package boardgame.gameModel.state;

public class SpecialAttackState extends OwnPieceSelected {

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.launchSpecialAbility();
        gameContext.setState(states.IDLE);
    }
}
