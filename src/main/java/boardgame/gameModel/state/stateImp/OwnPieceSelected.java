package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.PieceVisitor;
import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.State;
import boardgame.gameModel.state.states;


public class OwnPieceSelected implements State {

    public OwnPieceSelected() {
        System.out.println("In own piece selected state");
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
        //TODO set up new buttons to click.
        //for the moment let's make it a transition.
        PieceVisitor sv = new PieceVisitor();
        gameContext.getSelectedPiece().accept(sv);
        gameContext.highlightSpecialTiles(sv.getState(), sv);
        //Set the state to the state it should be depending on the PieceVisitor. This state is determined polymorphically.
        // as the visitor pattern is used.
        //gameContext.setState(sv.getState());

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
        System.out.println("No change");
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

    }

    @Override
    public void onSwapTwo(GameContext gameContext) {

    }

    @Override
    public void accept(HighlightVisitor v) {

    }

}
