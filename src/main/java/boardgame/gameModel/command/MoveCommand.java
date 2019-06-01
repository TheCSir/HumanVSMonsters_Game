package boardgame.gameModel.command;

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
    private IBoardGrid boardGrid;
    private List<TileView> highlightedTiles;
    private List<Location> locations = new ArrayList<>();
    private TurnFacade tf;

    @Override
    public void execute() {

        locations.clear();
        for (TileView highlightedTile : highlightedTiles) {
            locations.add(highlightedTile.getLocation());
        }

        //Check for valid location before move.
        if (locations.contains(destination)) {
            tf.movePiece(selectedPiece, destination);

            //End turn
            tf.nextTurn();
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
        tf.clickTile(target2);


        tf.movePiece(selectedPiece, destination);

        // end turn
        tf.nextTurn();

    }

    public void SetCommand(TurnFacade tf, Location destination, IPiece selectedPiece, IBoardGrid boardGrid, List<TileView> highlightedTiles) {
        this.tf = tf;
        this.selectedPiece = selectedPiece;
        this.destination = destination;
        startingLocation = new Location(selectedPiece.getLocation().getX(), selectedPiece.getLocation().getY());
        this.boardGrid = boardGrid;
        this.highlightedTiles = highlightedTiles;
    }
}