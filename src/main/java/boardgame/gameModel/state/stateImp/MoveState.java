package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;

public class MoveState extends OwnPieceSelected {

    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Already in move state");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        gameContext.setState(states.ATTACK);
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        gameContext.setState(states.SPECIAL);
    }

    @Override
    public void onDefence(GameContext gameContext) {
        gameContext.setState(states.DEFENCE);
    }

    @Override
    public void onSwap(GameContext gameContext) {
        gameContext.setState(states.SWAP);
    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        gameContext.setState(states.OWNPIECESELECTED);
    }

    @Override
    public void onSelectTile(GameContext gameContext) {

        gameContext.movePiece();

        gameContext.setState(states.IDLE);
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {
        gameContext.setState(states.ENEMYPIECESELECTED);
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
