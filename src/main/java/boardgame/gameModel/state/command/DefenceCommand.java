package boardgame.gameModel.state.command;

import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;

public class DefenceCommand implements Command {

    private IPiece ownPiece;
    private TurnFacade tf;


    @Override
    public void execute() {
        ownPiece.createShield(tf.getTurnNumber());
        System.out.println("Defending");
        // end turn
        tf.nextTurn();
    }

    @Override
    public void undo() {
        //Roll back turn.
        tf.goBackOneTurn();

        ownPiece.setIsShielded(false);
    }

    @Override
    public void redo() {
        execute();
    }

    public void SetCommand(TurnFacade tf, IPiece ownPiece) {
        this.tf = tf;
        this.ownPiece = ownPiece;
    }
}
