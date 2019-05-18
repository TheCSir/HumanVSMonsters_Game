package boardgame.gameModel.state.command;


//This class is using the Command pattern. This implements the Command interface.
// See http://www.informit.com/articles/article.aspx?p=2471643&seqNum=5 for info about
// implementation outline.

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Location;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand implements Command {

    private IPiece selectedPiece;
    private Location startingLocation;
    private Location destination;
    private IGameManager gm;
    private IBoardGrid boardGrid;
    private List<TileView> highlightedTiles;
    private List<Location> locations = new ArrayList<>();
    private TurnFacade tf;

    @Override
    public void execute() {

        locations.clear();
        for (TileView highlightedTile : highlightedTiles) {
            locations.add(highlightedTile.getLocation());
            System.out.println("highlightedTile.getLocation() = " + highlightedTile.getLocation());
        }

        //Check for valid location before move.
        if (locations.contains(destination)) {
            gm.getiBoard().movePiece(selectedPiece, destination);

            //End turn
            tf.nextTurn();
            System.out.println("Piece moved");
        }
    }

    @Override
    public void undo() {

        //Roll back turn.
        tf.goBackOneTurn();

        //Change piece's location. Listener will redraw the piece.
        selectedPiece.setLocation(startingLocation);

        //TODO reset text field.
    }

    @Override
    public void redo() {

        TileView target2 = boardGrid.getTileView(destination);
        gm.getGameContext().clickTile(target2);

        gm.getiBoard().movePiece(selectedPiece, destination);

        // end turn
        tf.nextTurn();

    }

    public void SetCommand(IGameManager gm, TurnFacade tf, Location destination, IPiece selectedPiece, IBoardGrid boardGrid, List<TileView> highlightedTiles) {
        this.tf = tf;
        this.gm = gm;
        this.selectedPiece = selectedPiece;
        this.destination = destination;
        startingLocation = new Location(selectedPiece.getLocation().getX(), selectedPiece.getLocation().getY());
        this.boardGrid = boardGrid;
        this.highlightedTiles = highlightedTiles;
    }
}