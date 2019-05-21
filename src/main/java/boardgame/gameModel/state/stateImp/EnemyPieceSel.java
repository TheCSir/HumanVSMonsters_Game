package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.State;
import boardgame.gameModel.state.states;

//TODO decide whether this actually needs to be a state.
public class EnemyPieceSel implements State {
    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        System.out.println("Changing state to own piece");

        gameContext.setState(states.OWNPIECESELECTED);
    }

    @Override
    public void onSelectTile(GameContext gameContext) {

        //Update View.
        //gameContext.updateTileInfo();

    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        System.out.println("error");
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        System.out.println("error");
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
