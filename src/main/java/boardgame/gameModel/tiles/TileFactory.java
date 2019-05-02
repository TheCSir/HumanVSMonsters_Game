package boardgame.gameModel.tiles;

import boardgame.util.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Creator for tiles. Used to reduce hard dependencies according to GRASP principles.
 */
public class TileFactory {

    private static Map<String, ITile> tilesCache = new HashMap<>();

    /**
     * Create tile tile.
     *
     * @param tileType the tile type
     * @param location the location
     * @return the tile
     */
    public static ITile createTile(String tileType, Location location) {
        return new HexagonalTile(location);
    }

}
