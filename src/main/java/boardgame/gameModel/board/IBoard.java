package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.collections.ObservableMap;

/**
 * The interface Board.
 */
public interface IBoard {

    /**
     * Insert piece.
     *
     * @param piece the piece
     */
//Add a piece to the board
    void insertPiece(IPiece piece);

    /**
     * Sets up tiles.
     */
//Set the board up based on default values.
    void setUpTiles();

    /**
     * Add tile.
     *
     * @param location the location
     * @param tile     the tile
     */
//add an additional tile to the board.
    void addTile(Location location, ITile tile);

    /**
     * Delete tile.
     *
     * @param location the location
     * @param tile     the tile
     */
    void deleteTile(Location location, ITile tile);

    /**
     * Gets tiles.
     *
     * @return the tiles
     */
//return a map using Location as a key.
    ObservableMap<Location, ITile> getTiles();

    /**
     * Gets pieces.
     *
     * @return the pieces
     */
//Return a map of pieces using pieceID as a key.
    ObservableMap<Integer, IPiece> getPieces();

    /**
     * Gets piece.
     *
     * @param pieceID the piece id
     * @return the piece
     */
//Get an individual piece.
    IPiece getPiece(int pieceID);

    /**
     * Move piece boolean.
     *
     * @param piece           the piece
     * @param desiredLocation the desired location
     * @return the boolean
     */
    boolean movePiece(IPiece piece, Location desiredLocation);
}
