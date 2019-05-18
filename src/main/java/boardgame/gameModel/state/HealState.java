package boardgame.gameModel.state;

public class HealState extends OwnPieceSelected {

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {

        gameContext.launchSpecialAbility();
        gameContext.setState(states.IDLE);
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }


}