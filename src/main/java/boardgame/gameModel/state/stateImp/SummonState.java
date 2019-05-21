package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.HighlightVisitor;

public class SummonState extends SpecialState {
    @Override
    public void accept(HighlightVisitor hv) {
        hv.visit(this);
    }
}
