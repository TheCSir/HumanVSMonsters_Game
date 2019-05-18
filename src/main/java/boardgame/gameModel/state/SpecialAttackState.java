package boardgame.gameModel.state;

public class SpecialAttackState extends SpecialState {

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.launchSpecialAbility();
        gameContext.setState(states.IDLE);
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
