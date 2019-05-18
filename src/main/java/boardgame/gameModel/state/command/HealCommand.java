package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;

public class HealCommand extends SpecialCommand {

    private int h;
    private TurnFacade tf;
    private IGameManager gm;

    @Override
    public void execute() {
        System.out.println("It's healing time!");
        gm.getActivePlayer().increaseHealthProperty(h);
        tf.nextTurn();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf) {
        this.tf = tf;
        this.gm = gm;
    }


    public void setHealValue(int h) {
        this.h = h;
    }

}
