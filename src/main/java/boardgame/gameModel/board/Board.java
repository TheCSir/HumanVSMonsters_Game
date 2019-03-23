package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;

public abstract class Board implements IBoard {
    private ITile[] tiles;
    public void insertPiece(IPiece piece){}
    public void setUpTiles(){}
    public void addTile(ITile tile){}
}
