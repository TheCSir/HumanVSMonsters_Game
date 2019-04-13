package boardgame.view;

import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;

public class TileViewFactory {

    public static TileView createTileView(double x, double y, double radius, ITile tile) {
        //Generally avoid instance of but will make an exception for this simple constructor for convenience.
        if (tile instanceof HexagonalTile) {
            return new HexagonTileView(x, y, radius, tile);
        }
        return null;
    }
}
