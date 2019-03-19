package gameModel.board;

import gameModel.pieces.IPiece;
import gameModel.tiles.ITile;

public abstract class Board {
    private ITile[] tiles;
    public abstract void insertPiece(IPiece piece);
    public abstract void setUpTiles();
    public abstract void addTile(ITile tile);
}
