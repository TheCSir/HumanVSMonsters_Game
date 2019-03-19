package gameModel.board;

import gameModel.pieces.IPiece;
import gameModel.tiles.ITile;

public interface IBoard {
    public void insertPice(IPiece piece);
    public void setUpTiles();
    public void addTile(ITile tile);
}
