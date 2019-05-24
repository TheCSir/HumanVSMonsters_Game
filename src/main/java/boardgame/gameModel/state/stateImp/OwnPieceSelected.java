package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.PieceVisitor;
import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.State;
import boardgame.gameModel.state.states;


public class OwnPieceSelected implements State {

    public OwnPieceSelected() {
        //Nothing happens if selecting own state.
    }


    @Override
    public void onMove(GameContext gameContext) {

        gameContext.setState(states.MOVE);
    }

    @Override
    public void onAttack(GameContext gameContext) {
        gameContext.setState(states.ATTACK);
    }

    @Override
    public void onSpecial(GameContext gameContext) {


        PieceVisitor sv = new PieceVisitor();
        gameContext.getSelectedPiece().accept(sv);
        gameContext.highlightSpecialTiles(sv.getState(), sv);


        //Set special visitor to gamecontext. Gives access to correct command to be called when special ability triggered.
        gameContext.setSpecialVisitor(sv);
    }

    @Override
    public void onDefence(GameContext gameContext) {
        gameContext.setState(states.DEFENCE);
    }

    @Override
    public void onSwap(GameContext gameContext) {

        gameContext.setUpSwap();

        gameContext.setState(states.SWAP);
    }


    @Override
    public void onSelectOwnPiece(GameContext gameContext) {

    }

    @Override
    public void onSelectTile(GameContext gameContext) {
        gameContext.setState(states.IDLE);
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.setState(states.ENEMYPIECESELECTED);
    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        //Not available from this state
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        //Not available from this state
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }

}
