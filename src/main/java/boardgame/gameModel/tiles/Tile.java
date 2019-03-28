package boardgame.gameModel.tiles;

import boardgame.gameModel.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile implements ITile {
    List<Location> neighbourPositions;
    private List<ITile> neighbours;
    private Location location;
    protected final int size = 20;
    private double[] sides;
    private boolean traversable;
    protected int movementCost;

    public Tile(Location location) {
        this.location = location;
        neighbours = new ArrayList<>();
        neighbourPositions = new ArrayList<>();
    }

    public boolean getTraversable() {
        return traversable;
    }

    public double[] getSides() {
        return sides;
    }

    @Override
    public void setGridPosition(Location location) {
        this.location=location;
    }

    @Override
    public Location getGridPosition() {
        return this.location;
    }

    @Override
    public void addNeighbour(ITile tile) {
        neighbours.add(tile);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public List<ITile> getNeighbours() {
        return neighbours;
    }

}
