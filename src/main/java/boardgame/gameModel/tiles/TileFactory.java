package boardgame.gameModel.tiles;

import boardgame.util.Location;

/**
 * Creator for tiles. Used to reduce hard dependencies according to GRASP principles.
 */
public class TileFactory {

    /**
     * Create tile tile.
     *
     * @param TileType the tile type
     * @param location the location
     * @return the tile
     */
    public static ITile createTile(String TileType, Location location) {
        if (TileType.equals(HexagonalTile.class.getName())) {
            return new HexagonalTile(location);
        }
        return null;
    }
}
