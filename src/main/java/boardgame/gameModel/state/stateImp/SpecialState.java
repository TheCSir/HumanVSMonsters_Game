package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.HighlightVisitor;

public abstract class SpecialState extends OwnPieceSelected {

    public abstract void accept(HighlightVisitor hv);
}
