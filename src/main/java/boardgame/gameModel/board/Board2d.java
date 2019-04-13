package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.List;

public abstract class Board2d implements IBoard {

    private ObservableMap<Location, ITile> boardGrid = FXCollections.observableHashMap();

    public abstract void setUpTiles();
    public abstract void addTile(ITile tile);


    private boolean checkValidMove(IPiece piece, Location location) {

        List<ITile> neighbours = getNeighbours(piece);
        for (ITile tile: neighbours) {
            if (tile.getLocation().equals(location)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ObservableMap<Location, ITile> getTiles() {
        return this.getBoardGrid();
    }

    //Ensure returned value is not outside grid.
    public boolean checkMapLocation(Location location, int rows, int columns) {
        return (location.getX() >= 0
                && location.getY() >=0
                && location.getX() < columns
                && location.getY() < rows);
    }

    @Override
    public boolean movePiece(IPiece piece, Location location) {

        boolean pieceMoved = false;
        //First check that moving to a neighbouring position. If so change location.
        if (checkValidMove(piece, location)){
            piece.setLocation(location);
            pieceMoved = true;
        }

        return pieceMoved;
    }

    private List<ITile> getNeighbours(IPiece piece) {
        return boardGrid.get(piece.getLocation()).getNeighbours();
    }

    public ObservableMap<Location, ITile> getBoardGrid() {
        return boardGrid;
    }
}
