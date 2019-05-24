package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.AbstractPieceFactory;
import boardgame.gameModel.pieces.FactoryProducer;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.state.GameContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SwapCommand implements Command {
    private Button selectionButton;
    private GameContext gC;
    private Pane swapPane;
    private IGameManager gm;
    private IPiece oldPiece;
    private IPiece newPiece;
    private TurnFacade tf;

    @Override
    public void undo() {

        //Roll back turn.
        tf.goBackOneTurn();

        //Remove current piece
        tf.removePiece(newPiece);
        //Set piece back to previous piece.
        tf.addPiece(oldPiece);

    }


    public void SetCommand(TurnFacade tf, IGameManager gm, Pane swapPane, Button selectionButton, GameContext gC) {
        this.tf = tf;
        this.gm = gm;
        this.swapPane = swapPane;
        this.selectionButton = selectionButton;
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
        newPiece = apf.getPieceByName(selectionButton.getText(), oldPiece.getLocation());

        tf.addPiece(newPiece);

        //Handle GUI validations
        swapPane.setVisible(false);

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

