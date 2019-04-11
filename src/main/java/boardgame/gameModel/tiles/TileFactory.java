package boardgame.gameModel.tiles;

import boardgame.util.Location;

public class TileFactory {

    public static ITile createTile(String TileType, Location location) {
        if (TileType.equals(HexagonalTile.class.getName())) {
            return new HexagonalTile(location);
        }
        return null;
    }
}
