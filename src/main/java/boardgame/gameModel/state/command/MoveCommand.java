package boardgame.gameModel.state.command;


//This class is using the Command pattern. This implements the Command interface.
// See http://www.informit.com/articles/article.aspx?p=2471643&seqNum=5 for info about
// implementation outline.

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.util.Location;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand implements Command {

    private HexagonTileViewPiece selectedPiece;
    private Location startingLocation;
    private Location destination;
    private IGameManager gm;
    private Turn turn;
    private IBoardGrid boardGrid;
    private List<TileView> highlightedTiles;
    List<Location> locations = new ArrayList<>();

    @Override
    public void execute() {

        locations.clear();
        for (TileView highlightedTile : highlightedTiles) {
            locations.add(highlightedTile.getLocation());
            System.out.println("highlightedTile.getLocation() = " + highlightedTile.getLocation());
        }

        if (locations.contains(destination)) {
            gm.getiBoard().movePiece(selectedPiece.getiPiece(), destination);

            //Should this be replaced by a location check instead of having move return a boolean?
            // end turn
            gm.getTurn().nextTurn(gm.getPlayers());
            System.out.println("Piece moved");
        }
    }

    @Override
    public void undo() {


        //Roll back turn.
        int previousTurn = turn.getTurnNumber() - 1;
        turn.setTurnNumberProperty(previousTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = turn.getTurnNumber() % gm.getPlayers().size();
        turn.setActivePlayer(gm.getPlayers().get(nextPlayerIndex));

        selectedPiece.setLocation(startingLocation);

        //TODO reset text field.
    }

    @Override
    public void redo() {

        TileView target2 = boardGrid.getTileView(destination);
        gm.getGameContext().clickTile(target2);

        gm.getiBoard().movePiece(selectedPiece.getiPiece(), destination);

        //Should this be replaced by a location check instead of having move return a boolean?
        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());

    }

    public void SetCommand(IGameManager gm, Location destination, HexagonTileViewPiece selectedPiece, IBoardGrid boardGrid, List<TileView> highlightedTiles) {
        this.gm = gm;
        turn = gm.getTurn();
        this.selectedPiece = selectedPiece;
        this.destination = destination;
        startingLocation = new Location(selectedPiece.getLocation().getX(), selectedPiece.getLocation().getY());
        this.boardGrid = boardGrid;
        this.highlightedTiles = highlightedTiles;
    }
}