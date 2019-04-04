package boardgame.gameModel.board;

import boardgame.gameModel.Location;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;

public abstract class Board2d implements IBoard {

    public abstract void insertPiece(IPiece piece);
    public abstract void setUpTiles();
    public abstract void addTile(ITile tile);

    //Ensure returned value is not outside grid.
    public boolean checkMapLocation(Location location, int rows, int columns) {
        return (location.getX() >= 0
                && location.getY() >=0
                && location.getX() < columns
                && location.getY() < rows);
    }
}
