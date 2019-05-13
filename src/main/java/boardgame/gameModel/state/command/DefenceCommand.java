package boardgame.gameModel.state.command;

import boardgame.gameModel.TurnFacade;
import boardgame.view.HexagonTileViewPiece;

public class DefenceCommand implements Command {

    private HexagonTileViewPiece ownPiece;
    private TurnFacade tf;


    @Override
    public void execute() {
        ownPiece.getiPiece().createShield(tf.getTurnNumber());
        System.out.println("Defending");
        // end turn
        tf.nextTurn();
    }

    @Override
    public void undo() {
        //Roll back turn.
        tf.goBackOneTurn();

        ownPiece.getiPiece().setIsShielded(false);
    }

    @Override
    public void redo() {
        execute();
    }

    public void SetCommand(TurnFacade tf, HexagonTileViewPiece ownPiece) {
        this.tf = tf;
        this.ownPiece = ownPiece;
    }
}
