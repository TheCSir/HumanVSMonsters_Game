package boardgame.gameModel.tiles;

import boardgame.gameModel.Location;

public abstract class Tile implements ITile {
    private Location location;
    protected final int size = 20;
    private double[] sides;
    private boolean traversable;
    protected int movementCost;

    public Tile(Location location) {
        this.location = location;
    }

    public boolean getTraversable() {
        return traversable;
    }

    public double[] getSides() {
        return sides;
    }
}
