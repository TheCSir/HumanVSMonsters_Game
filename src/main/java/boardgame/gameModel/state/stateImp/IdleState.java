package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.State;
import boardgame.gameModel.state.states;

public class IdleState implements State {

    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }


    @Override
    public void onSelectOwnPiece(GameContext gameContext) {

        //Set new State. Need to reenter this state to avoid
        gameContext.setState(states.OWNPIECESELECTED);
    }

    @Override
    public void onSelectTile(GameContext gameContext) {
        System.out.println("Print tile info?");
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.setState(states.ENEMYPIECESELECTED);
    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        System.out.println("Shouldn't be possible to get here");
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        System.out.println("How did you get here?");
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
