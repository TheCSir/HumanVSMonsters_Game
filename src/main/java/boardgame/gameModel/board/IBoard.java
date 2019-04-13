package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.collections.ObservableMap;

/**
 * The Board interface. Our design uses an interface to set the contract for what Board classes must provide.
 * An abstract class is provided for 2 dimensional boards. The board2dHex class extends Board2D to provide a concrete
 * implementation. New instances can only be provided by the BoardFactory to decouple Board creation from
 * the board class.
 */
public interface IBoard {

    /**
     * Sets up tiles.
     */
//Set the board up based on default values.
    void setUpTiles();

    /**
     * Add tile.
     *
     * @param location the location of the tile.
     * @param tile     the tile itself.
     */
    void addTile(Location location, ITile tile);

    /**
     * Delete tile.
     *
     * @param location the location
     * @param tile     the tile
     */
    void deleteTile(Location location, ITile tile);

    /**
     * Gets tiles using a Location as the key.
     *
     * @return the tiles
     */

    ObservableMap<Location, ITile> getTiles();

    /**
     * Move a piece on the board. Returns true if
     * piece can be moved to desired location.
     *
     * @param piece           the piece
     * @param desiredLocation the desired location
     * @return boolean of whether move is possible.
     */
    boolean movePiece(IPiece piece, Location desiredLocation);
}
