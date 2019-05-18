package boardgame.gameModel.state;

public class SummonState extends SpecialState {
    @Override
    public void accept(HighlightVisitor hv) {
        hv.visit(this);
    }
}
