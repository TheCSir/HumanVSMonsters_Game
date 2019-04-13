package boardgame.gameModel.tiles;

import boardgame.util.Location;

import java.util.List;

/**
 * Interface for board game tiles for the model.
 */
public interface ITile {

    /**
     * Gets location.
     *
     * @return the location
     */
//Return the location (containing grid position)
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
     * @param tile the tile
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
