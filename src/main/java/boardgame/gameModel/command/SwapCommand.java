package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.AbstractPieceFactory;
import boardgame.gameModel.pieces.FactoryProducer;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.state.GameContext;

public class SwapCommand implements Command {
    private GameContext gC;
    private IGameManager gm;
    private IPiece oldPiece;
    private IPiece newPiece;
    private TurnFacade tf;
    private String swapAlternativeClass;

    @Override
    public void undo() {

        //Roll back turn.
        tf.goBackOneTurn();

        //Remove current piece
        tf.removePiece(newPiece);
        //Set piece back to previous piece.
        tf.addPiece(oldPiece);

    }


    public void SetCommand(TurnFacade tf, IGameManager gm, String swapAlternativeClass, GameContext gC) {
        this.tf = tf;
        this.gm = gm;
        this.swapAlternativeClass = swapAlternativeClass;
        this.gC = gC;
    }

    //Return full name of the piece
    @Override
    public void execute() {

        //Get current piece and it's location
        oldPiece = gC.getSelectedPiece();

        //Remove current piece
        tf.removePiece(oldPiece);

        //Create new piece and add to board
        AbstractPieceFactory apf = FactoryProducer.getFactory(gm.getTurn().getActivePlayer().playerType());
        assert apf != null;
        newPiece = apf.getPieceByName(swapAlternativeClass, oldPiece.getLocation());

        tf.addPiece(newPiece);

        //Handle GUI validations
        gC.setSwapPaneVisible(false);

        //End turn
        tf.nextTurn();
    }

    @Override
    public void redo() {

        //Remove current piece
        tf.removePiece(oldPiece);
        //Set piece back to previous piece.
        tf.addPiece(newPiece);

        //End turn
        tf.nextTurn();
    }
}

