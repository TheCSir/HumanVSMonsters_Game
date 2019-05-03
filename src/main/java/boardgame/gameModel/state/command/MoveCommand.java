package boardgame.gameModel.state.command;


//This class is using the Command pattern. This implements the Command interface.
// See http://www.informit.com/articles/article.aspx?p=2471643&seqNum=5 for info about
// implementation outline.

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Location;

public class MoveCommand implements Command {

    private IPiece selectedPiece;
    private Location location;
    private IGameManager gm;

    @Override
    public void execute() {
        boolean pieceMoved = gm.getiBoard().movePiece(selectedPiece, location);

        //Should this be replaced by a location check instead of having move return a boolean?
        if (pieceMoved) {
            // end turn
            gm.getTurn().nextTurn(gm.getPlayers());
            System.out.println("Piece moved");
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public void SetCommand(IGameManager gm, Location location, IPiece selectedPiece) {
        this.gm = gm;
        this.selectedPiece = selectedPiece;
        this.location = location;
    }
}
