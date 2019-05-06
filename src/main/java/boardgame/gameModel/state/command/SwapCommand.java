package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.*;
import boardgame.util.LocationFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SwapCommand implements Command {
    private Button selectionButton;
    private Pane swapPane;
    private IGameManager gm;

    //Return full name of the piece
    public static String getClassFullName(String piece) {
        if (piece.equals(Warrior.class.getSimpleName())) {
            return Warrior.class.getName();
        } else if (piece.equals(Priest.class.getSimpleName())) {
            return Priest.class.getName();
        } else if (piece.equals(Archer.class.getSimpleName())) {
            return Archer.class.getName();
        } else if (piece.equals(Medusa.class.getSimpleName())) {
            return Medusa.class.getName();
        } else if (piece.equals(Griffin.class.getSimpleName())) {
            return Griffin.class.getName();
        } else if (piece.equals(Minotaur.class.getSimpleName())) {
            return Minotaur.class.getName();
        } else
            return null;
    }

    @Override
    public void execute() {

        //Get current piece and it's location
        IPiece oldPiece = gm.getTurn().getActivePlayer().getPieces().get(0);
        int x = oldPiece.getLocation().getX();
        int y = oldPiece.getLocation().getY();

        //Remove current piece
        gm.getTurn().getActivePlayer().getPieces().remove(oldPiece);

        //Get piece full name
        String Piece = getClassFullName(selectionButton.getText());

        //Create new piece and add to board
        IPiece newPiece = PieceFactory.createPiece(Piece, 5, LocationFactory.createLocation(x, y));
        gm.getTurn().getActivePlayer().getPieces().add(newPiece);

        //Handle GUI validations
        swapPane.setVisible(false);

        //End turn
        gm.getTurn().nextTurn(gm.getPlayers());

    }

    @Override
    public void undo() {
        // TODO: swap undo to be implemented.
    }

    public void SetCommand(IGameManager gm, Pane swapPane, Button selectionButton) {
        this.gm = gm;
        this.swapPane = swapPane;
        this.selectionButton = selectionButton;
    }

    @Override
    public void redo() {
        // TODO: swap redo to be implemented.
    }
}
