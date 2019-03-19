package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;

public interface IBoard {
    public void insertPice(IPiece piece);
    public void setUpTiles();
    public void addTile(ITile tile);
}
