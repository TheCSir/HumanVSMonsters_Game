package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;

public class DefenceState extends OwnPieceSelected {


    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Already in defence state");
    }


    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        gameContext.createShield();
        gameContext.setState(states.IDLE);
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
