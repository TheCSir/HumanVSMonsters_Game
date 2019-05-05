package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceFactory;
import boardgame.gameModel.state.GameContext;
import boardgame.util.LocationFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SwapCommand implements Command {
    private Button selectionButton;
    private Pane swapPane;
    private IGameManager gm;

    @Override
    public void execute() {

        //Get current piece and it's location
        IPiece oldPiece = gm.getTurn().getActivePlayer().getPieces().get(0);
        int x = oldPiece.getLocation().getX();
        int y = oldPiece.getLocation().getY();

        //Remove current piece
        gm.getTurn().getActivePlayer().getPieces().remove(oldPiece);

        //Get piece full name
        String Piece = GameContext.getClassFullName(selectionButton.getText());

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

    }

    @Override
    public void redo() {

    }

    public void SetCommand(IGameManager gm, Pane swapPane, Button selectionButton) {
        this.gm = gm;
        this.swapPane = swapPane;
        this.selectionButton = selectionButton;
    }
}
