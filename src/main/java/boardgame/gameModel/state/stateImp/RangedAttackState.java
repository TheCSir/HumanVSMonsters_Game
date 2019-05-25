package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;

public class RangedAttackState extends SpecialState {


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
