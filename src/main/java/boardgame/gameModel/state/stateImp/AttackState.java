package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;

public class AttackState extends OwnPieceSelected {


    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Already in attack state");
    }


    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.attackPiece();
        gameContext.setState(states.IDLE);
        System.out.println(gameContext.getState().getClass().getSimpleName());
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
