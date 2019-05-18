package boardgame.gameModel.state;

import boardgame.gameModel.state.stateImp.HealState;
import boardgame.gameModel.state.stateImp.RangedAttackState;
import boardgame.gameModel.state.stateImp.SpecialAttackState;
import boardgame.gameModel.state.stateImp.SummonState;

public interface HighlightVisitor {

    void visit(RangedAttackState r);

    void visit(SpecialAttackState s);

    void visit(SummonState s);

    void visit(HealState h);

}
