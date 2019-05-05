package boardgame.gameModel.state.command;


//This class is using the Command pattern. This implements the Command interface.
// See http://www.informit.com/articles/article.aspx?p=2471643&seqNum=5 for info about
// implementation outline.

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.util.Location;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.PieceView;
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
        // highlightedTiles.clear();
    }

    @Override
    public void undo() {

        //turn.setActivePlayer(turn..getActivePlayer();
        //Set the piece back to it's starting position. This will also redraw the piece.
        //Why is listener not being called?

        //Roll back turn.
        int previousTurn = turn.getTurnNumber() - 1;
        turn.setTurnNumberProperty(previousTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = turn.getTurnNumber() % gm.getPlayers().size();
        turn.setActivePlayer(gm.getPlayers().get(nextPlayerIndex));

        TileView target2 = boardGrid.getTileView(startingLocation);

        selectedPiece.setLocation(startingLocation);

        //This should be called by observer not directly! Not sure why not working :(
        PieceView.changePiecePosition(selectedPiece, target2);
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
        System.out.println("Piece moved");

        //Listener not being called properly. Temp fix.
        PieceView.changePiecePosition(selectedPiece, target2);
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