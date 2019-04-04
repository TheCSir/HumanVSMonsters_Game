package boardgame.gameModel.board;

import boardgame.gameModel.Location;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;

import java.util.Map;

public interface IBoard {

    //Add a piece to the board
    void insertPiece(IPiece piece);

    //Set the board up based on default values.
    void setUpTiles();

    //add an additional tile to the board.
    void addTile(Location location, ITile tile);

    void deleteTile(Location location, ITile tile);

    //return a map using Location as a key.
    Map<Location, ITile> getTiles();

    //Return a map of pieces using pieceID as a key.
    Map<Integer, IPiece> getPieces();

    //Get an individual piece.
    IPiece getPiece(int pieceID);

    void movePiece(IPiece piece, Location desiredLocation);
}
