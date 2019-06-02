package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.List;

public abstract class Board2d implements IBoard {

    private final ObservableMap<Location, ITile> boardGrid = FXCollections.observableHashMap();

    public abstract void setUpTiles();
    public abstract void addTile(ITile tile);

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
    public void movePiece(IPiece piece, Location location) {
            piece.setLocation(location);
    }

    public ObservableMap<Location, ITile> getBoardGrid() {
        return boardGrid;
    }
}
