package boardgame.gameModel.state.command;


//This class is using the Command pattern. This implements the Command interface.
// See http://www.informit.com/articles/article.aspx?p=2471643&seqNum=5 for info about
// implementation outline.

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.util.Location;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.PieceView;

public class MoveCommand implements Command {

    private HexagonTileViewPiece selectedPiece;
    private Location startingLocation;
    private Location destination;
    private IGameManager gm;
    private Turn turn;
    private double x;
    private double y;


    @Override
    public void execute() {
        boolean pieceMoved = gm.getiBoard().movePiece(selectedPiece.getiPiece(), destination);

        //Should this be replaced by a location check instead of having move return a boolean?
        if (pieceMoved) {
            // end turn
            gm.getTurn().nextTurn(gm.getPlayers());
            System.out.println("Piece moved");
        }
    }

    @Override
    public void undo() {
        System.out.println("changing locatio to");
        //turn.setActivePlayer(turn..getActivePlayer();
        //Set the piece back to it's starting position. This will also redraw the piece.
        //Why is listener not being called?

        selectedPiece.getiPiece().setLocation(startingLocation);
        PieceView.changePieceFromAbsoluteValue(selectedPiece, x, y);
        //Roll back turn.
        int previousTurn = turn.getTurnNumber() - 1;
        turn.setTurnNumberProperty(previousTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = turn.getTurnNumber() % gm.getPlayers().size();
        turn.setActivePlayer(gm.getPlayers().get(nextPlayerIndex));
        System.out.println("Location of selected piece is now: " + startingLocation.toString());
        System.out.println(selectedPiece.getLocation());
        System.out.println("Selected piece is: " + selectedPiece.toString());

        //TODO reset text field.
    }

    @Override
    public void redo() {
        execute();
    }

    public void SetCommand(IGameManager gm, Location destination, HexagonTileViewPiece selectedPiece) {
        this.gm = gm;
        turn = gm.getTurn();
        this.selectedPiece = selectedPiece;
        this.destination = destination;
        startingLocation = selectedPiece.getLocation();
        x = selectedPiece.getBoundsInParent().getCenterX();
        y = selectedPiece.getBoundsInParent().getCenterY();
    }
}