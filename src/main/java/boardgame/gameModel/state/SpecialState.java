package boardgame.gameModel.state;

public abstract class SpecialState extends OwnPieceSelected {

    public abstract void accept(HighlightVisitor hv);
}
