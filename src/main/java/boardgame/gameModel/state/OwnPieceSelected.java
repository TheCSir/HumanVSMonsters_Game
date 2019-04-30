package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class OwnPieceSelected implements State {

    public OwnPieceSelected() {
        System.out.println("In own piece selected state");
    }

    @Override
    public void onMove(GameContext gameContext) {
        //TODO highlight surrounding tiles.
        gameContext.highlightMove();
        System.out.println("Setting surrounding colour to Red");
        System.out.println("Setting move state");
        gameContext.setState(new MoveState());
    }

    @Override
    public void onAttack(GameContext gameContext) {
        //TODO highlight surrounding tiles.
        System.out.println("setting attack state");
        gameContext.setState(new AttackState());
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Set special state");
        //TODO set up new buttons to click.

        gameContext.setState(new SpecialState());
    }

    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Setting defense state");
        gameContext.setState(new DefenceState());
    }

    @Override
    public void onSwap(GameContext gameContext) {

        gameContext.setUpSwap();
        System.out.println("setting swap state.");
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

        gameContext.setState(new SwapState());
    }


    @Override
    public void notSelected(GameContext gameContext) {
        System.out.println("Setting idle state");
    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        System.out.println("No change");
    }

    @Override
    public void onSelectTile(GameContext gameContext) {
        System.out.println("selecting tile");
        gameContext.setState(new IdleState());
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {
        System.out.println("selecting enemy piece");

        gameContext.updateEnemyPieceDetails();

        gameContext.setState(new EnemyPieceSel());
    }

    @Override
    public void attackPiece(GameContext gameContext) {

    }

    @Override
    public void onSwapOne(GameContext gameContext) {

    }

    @Override
    public void onSwapTwo(GameContext gameContext) {

    }
}
