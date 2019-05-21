package boardgame.gameModel.state;

import boardgame.gameModel.state.stateImp.*;

public interface HighlightVisitor {

    void visit(RangedAttackState r);

    void visit(SpecialAttackState s);

    void visit(SummonState s);

    void visit(HealState h);

    void visit(EnemyPieceSel enemyPieceSel);

    void visit(IdleState idleState);

    void visit(MoveState moveState);

    void visit(AttackState attackState);

    void visit(DefenceState defenceState);
}
