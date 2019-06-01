package boardgame.gameModel.command;

import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.TileView;

public class HealCommand extends SpecialCommand {

    private double h;
    private TurnFacade tf;
    @Override
    public void execute() {
        IPlayer activePlayer = tf.getActivePlayer();
        if (!activePlayer.getIsAbilityUsed()) {

            activePlayer.increaseHealthProperty(h);

            //set ability used counter
            activePlayer.setIsAbilityUsed(tf.getTurnNumber());

            tf.nextTurn();
        } else {
            System.out.println("Special ability already used!!");
        }
    }

    @Override
    public void undo() {
        tf.goBackOneTurn();
        IPlayer activePlayer = tf.getActivePlayer();
        activePlayer.healthProperty().setValue(activePlayer.healthProperty().get() - h);
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public void setCommand(IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
    }

    public void setHealValue(double h) {
        this.h = h;
    }

}
