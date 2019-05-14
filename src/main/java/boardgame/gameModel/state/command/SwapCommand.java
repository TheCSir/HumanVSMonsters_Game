package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SwapCommand implements Command {
    private Button selectionButton;
    private Pane swapPane;
    private IGameManager gm;
    private IPiece oldPiece;
    private IPiece newPiece;
    private TurnFacade tf;

    public static String getClassFullName(String piece) {
        if (piece.equals(Warrior.class.getSimpleName())) {
            return PieceConstants.MELEE;
        } else if (piece.equals(Priest.class.getSimpleName())) {
            return PieceConstants.SUPPORT;
        } else if (piece.equals(Archer.class.getSimpleName())) {
            return PieceConstants.RANGED;
        } else if (piece.equals(Medusa.class.getSimpleName())) {
            return PieceConstants.RANGED;
        } else if (piece.equals(Griffin.class.getSimpleName())) {
            return PieceConstants.SUPPORT;
        } else if (piece.equals(Minotaur.class.getSimpleName())) {
            return PieceConstants.MELEE;
        } else
            return null;
    }

    @Override
    public void undo() {

        //Roll back turn.
        tf.goBackOneTurn();

        //Remove current piece
        tf.removePiece(newPiece);
        //Set piece back to previous piece.
        tf.addPiece(oldPiece);

    }

    public void SetCommand(TurnFacade tf, IGameManager gm, Pane swapPane, Button selectionButton) {
        this.tf = tf;
        this.gm = gm;
        this.swapPane = swapPane;
        this.selectionButton = selectionButton;
    }

    //Return full name of the piece
    @Override
    public void execute() {

        //Get current piece and it's location
        oldPiece = gm.getTurn().getActivePlayer().getPieces().get(0);

        //Remove current piece
        tf.removePiece(oldPiece);

        //Get piece full name
        String piece = getClassFullName(selectionButton.getText());

        //Create new piece and add to board
        AbstractPieceFactory apf = FactoryProducer.getFactory(gm.getTurn().getActivePlayer().playerType());
        //newPiece = apf.getPiece(piece, oldPiece.getLocation());
        newPiece = new Minion(oldPiece.getLocation(),"Snake");

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
