package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;

public class MoveState extends OwnPieceSelected {

    public MoveState() {
        System.out.println("In move state");
    }

    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Already in move state");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Moving to attack state");
        gameContext.setState(states.ATTACK);
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("changing to special state");
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
        System.out.println("The piece should try to move now. If it can't move stay in this state or go to idle");

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
