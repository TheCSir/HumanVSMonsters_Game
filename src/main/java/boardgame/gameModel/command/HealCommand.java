package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.TileView;

public class HealCommand extends SpecialCommand {

    private double h;
    private TurnFacade tf;
    private IGameManager gm;

    @Override
    public void execute() {

        if(!gm.getActivePlayer().getIsAbilityUsed()){
            System.out.println("It's healing time!");
            gm.getActivePlayer().increaseHealthProperty(h);

            //set ability used counter
            gm.getActivePlayer().setIsAbilityUsed(gm.getTurn().getTurnNumber());

            tf.nextTurn();
        }
        else {
            System.out.println("Special ability already used!!");
        }
    }

    @Override
    public void undo() {
        tf.goBackOneTurn();
        gm.getActivePlayer().healthProperty().setValue(gm.getActivePlayer().healthProperty().get() - h);
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        this.gm = gm;
    }

    public void setHealValue(double h) {
        this.h = h;
    }

}
