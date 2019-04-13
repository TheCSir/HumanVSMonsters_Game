package boardgame.gameModel.tiles;

import boardgame.util.HexGridUtil;
import boardgame.util.Location;


/**
 * Represents a basic tile (eg clear ground). New tiles may not be traversable, may cost more to move over etc...
 */
public class HexagonalTile extends Tile {


    private boolean traversable;
    private int movementCost;

    HexagonalTile(Location location) {
        super(location);
        neighbourPositions = HexGridUtil.getNeighbourPositions(location);
    }

    @Override
    public boolean getTraversable() {
        return false;
    }

    @Override
    public int getPieceID() {
        return 0;
    }


    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public int getMovementCost() {
        return movementCost;
    }


    public void setMovementCost(int movementCost) {
        this.movementCost = movementCost;
    }
}
