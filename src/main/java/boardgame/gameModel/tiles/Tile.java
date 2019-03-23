package boardgame.gameModel.tiles;

import javafx.scene.shape.Polygon;

public abstract class Tile extends Polygon implements ITile {
    protected int xAxis;
    protected int yAxis;
    protected final int size = 20;
    protected double[] sides;
    protected boolean traversable;
    protected int movementCost;

    public boolean getTraversable() {
        return traversable;
    }

    public double[] getSides() {
        return sides;
    }

    public void returnOrientation() {
    }
}
