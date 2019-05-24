package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.PieceVisitor;
import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.State;
import boardgame.gameModel.state.states;

import java.util.Objects;

public class OwnPieceSelected implements State {

    private static final String TAG = "OwnPieceSelected";
    private static OwnPieceSelected ownPieceSelected;

    public OwnPieceSelected() {
        System.out.println("In own piece selected state");
    }

    public static OwnPieceSelected getInstance() {
        return Objects.requireNonNullElseGet(ownPieceSelected, OwnPieceSelected::new);
    }

    @Override
    public void onMove(GameContext gameContext) {

        System.out.println("Setting surrounding colour to Red");
        System.out.println("Setting move state");
        gameContext.setState(states.MOVE);
    }

    @Override
    public void onAttack(GameContext gameContext) {

        System.out.println("setting attack state");

        gameContext.setState(states.ATTACK);
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Set special state");
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
        System.out.println("Setting defense state");
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
        System.out.println("selecting tile");
        gameContext.setState(states.IDLE);
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {
        System.out.println("selecting enemy piece");

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
