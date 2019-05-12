package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.gameModel.pieces.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SwapCommand implements Command {
    private Button selectionButton;
    private Pane swapPane;
    private IGameManager gm;
    private IPiece oldPiece;
    private IPiece newPiece;
    private Turn turn;

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
        int previousTurn = turn.getTurnNumber() - 1;
        turn.setTurnNumberProperty(previousTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = turn.getTurnNumber() % gm.getPlayers().size();
        turn.setActivePlayer(gm.getPlayers().get(nextPlayerIndex));

        //Remove current piece
        gm.getTurn().getActivePlayer().getPieces().remove(newPiece);
        //Set piece back to previous piece.
        gm.getTurn().getActivePlayer().getPieces().add(oldPiece);

    }

    public void SetCommand(IGameManager gm, Pane swapPane, Button selectionButton) {
        this.gm = gm;
        this.swapPane = swapPane;
        this.selectionButton = selectionButton;
        turn = gm.getTurn();
    }

    //Return full name of the piece
    @Override
    public void execute() {

        //Get current piece and it's location
        oldPiece = gm.getTurn().getActivePlayer().getPieces().get(0);

        //Remove current piece
        gm.getTurn().getActivePlayer().getPieces().remove(oldPiece);

        //Get piece full name
        String piece = getClassFullName(selectionButton.getText());

        //Create new piece and add to board
        AbstractPieceFactory apf = FactoryProducer.getFactory(gm.getTurn().getActivePlayer().playerType());
        newPiece = apf.getPiece(piece, oldPiece.getLocation());

        gm.getTurn().getActivePlayer().getPieces().add(newPiece);

        //Handle GUI validations
        swapPane.setVisible(false);

        //End turn
        gm.getTurn().nextTurn(gm.getPlayers());

    }

    @Override
    public void redo() {

        //Remove current piece
        gm.getTurn().getActivePlayer().getPieces().remove(oldPiece);
        //Set piece back to previous piece.
        gm.getTurn().getActivePlayer().getPieces().add(newPiece);

        //End turn
        gm.getTurn().nextTurn(gm.getPlayers());

    }
}
