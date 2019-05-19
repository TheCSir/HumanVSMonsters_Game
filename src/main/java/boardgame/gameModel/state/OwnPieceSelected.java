package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.PieceVisitor;
import boardgame.gameModel.pieces.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class OwnPieceSelected implements State {

    private static final String TAG = "OwnPieceSelected";
    private static OwnPieceSelected ownPieceSelected;

    public OwnPieceSelected() {
        System.out.println("In own piece selected state");
    }

    @Override
    public void onMove(GameContext gameContext) {

        System.out.println("Setting surrounding colour to Red");
        System.out.println("Setting move state");
        gameContext.setState(states.MOVE);
    }

    public static OwnPieceSelected getInstance() {
        return Objects.requireNonNullElseGet(ownPieceSelected, OwnPieceSelected::new);
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
        // gameContext.setState(sv.getState());

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

        Pane SwapPane = gameContext.getSwapPane();
        IGameManager gm = gameContext.getGm();
        Button Opt_one = gameContext.getOpt_one();
        Button Opt_two = gameContext.getOpt_two();

        //Switch the disabled status
        SwapPane.setVisible(!SwapPane.isVisible());

        //get current piece class
        String oldPieceName = gm.getTurn().getActivePlayer().getPieces().get(0).getClass().getName();

        // Button label store location;
        String OptOne = null;
        String OptTwo = null;


        // Check and populate Gui according to current situation
        if (oldPieceName.equals(Warrior.class.getName())) {
            OptOne = Archer.class.getSimpleName();
            OptTwo = Priest.class.getSimpleName();

        } else if (oldPieceName.equals(Priest.class.getName())) {
            OptOne = Warrior.class.getSimpleName();
            OptTwo = Archer.class.getSimpleName();

        } else if (oldPieceName.equals(Archer.class.getName())) {
            OptOne = Warrior.class.getSimpleName();
            OptTwo = Priest.class.getSimpleName();

        } else if (oldPieceName.equals(Medusa.class.getName())) {
            OptOne = Griffin.class.getSimpleName();
            OptTwo = Minotaur.class.getSimpleName();

        } else if (oldPieceName.equals(Griffin.class.getName())) {
            OptOne = Medusa.class.getSimpleName();
            OptTwo = Minotaur.class.getSimpleName();

        } else if (oldPieceName.equals(Minotaur.class.getName())) {
            OptOne = Griffin.class.getSimpleName();
            OptTwo = Medusa.class.getSimpleName();

        }

        // Set button labels
        Opt_one.setText(OptOne);
        Opt_two.setText(OptTwo);

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
