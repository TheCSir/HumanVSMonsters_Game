package boardgame.gameModel.tiles;

import boardgame.util.Location;

import java.util.List;

/**
 * Interface for board game tiles for the model. Provides the contract for different tiles. Interface means
 * classes using tiles do not need to rely on concrete implementations which might change.
 */
public interface ITile {

    /**
     * Gets location as a grid position. The tile holds it's own location. This makes it easy for any method that
     * has access to a tile to find out it's grid position.
     *
     * @return the location of this tile.
     */
    Location getLocation();

    /**
     * Sets the tile location.
     *
     * @param location Pass a location containing x and y (for a 2d grid) to the tile.
     */
    void setLocation(Location location);

    /**
     * Add neighbour.
     *
     * @param tile the Itile
     */
    void addNeighbour(ITile tile);


    /**
     * Gets neighbours of tile on the grid.
     *
     * @return list of neighbouring tiles.
     */
    List<ITile> getNeighbours();

    /**
     * Gets piece id.
     *
     * @return the piece id
     */
    int getPieceID();


}
