package boardgame.gameModel.tiles;


import boardgame.util.Location;

import java.util.ArrayList;
import java.util.List;

import static org.valid4j.Assertive.require;

public abstract class Tile implements ITile {
    List<Location> neighbourPositions;
    private List<ITile> neighbours;
    private Location location;
    protected final int size = 20;
    private boolean traversable;
    private int movementCost;

    Tile(Location location) {
        require(location.getX() >-1 && location.getY() >-1);
        this.location = location;
        neighbours = new ArrayList<>();
        neighbourPositions = new ArrayList<>();
    }

    public boolean getTraversable() {
        return traversable;
    }



    @Override
    public void setLocation(Location location) {
        this.location=location;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void addNeighbour(ITile tile) {
        neighbours.add(tile);
    }


    @Override
    public List<ITile> getNeighbours() {
        return neighbours;
    }

    @Override
    public String toString() {
        return "Tile{" +
                ", location=" + location +
                ", movementCost=" + movementCost +
                '}';
    }
}
