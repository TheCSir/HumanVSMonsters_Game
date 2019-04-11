package boardgame.gameModel.tiles;

import boardgame.util.HexGridUtil;
import boardgame.util.Location;

public class HexagonalTile extends Tile {

    public HexagonalTile(Location location) {
        super(location);
        neighbourPositions = HexGridUtil.getNeighbourPositions(location);
    }

    @Override
    public int getPieceID() {
        return 0;
    }
}
