package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;

public abstract class Board {
    private ITile[] tiles;
    public abstract void insertPiece(IPiece piece);
    public abstract void setUpTiles();
    public abstract void addTile(ITile tile);
}
