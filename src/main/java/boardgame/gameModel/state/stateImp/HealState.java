package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;

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
