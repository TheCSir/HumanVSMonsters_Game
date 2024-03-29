package boardgame.gameModel.tiles;


import boardgame.util.Location;

import java.util.ArrayList;
import java.util.List;

import static org.valid4j.Assertive.require;

/**
 * Abstract representation of board tile. Inheriting classes will implement the type of terrain (wall, water etc..)
 */

public abstract class Tile implements ITile {
    List<Location> neighbourPositions;
    private final List<ITile> neighbours;
    private Location location;

    //Constructor is package-private. Classes to use factory to instantiate objects.
    Tile(Location location) {
        require(location.getX() >-1 && location.getY() >-1);
        this.location = location;
        neighbours = new ArrayList<>();
        neighbourPositions = new ArrayList<>();
    }

    public abstract boolean getTraversable();

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
                "neighbourPositions=" + neighbourPositions +
                ", neighbours=" + neighbours +
                ", location=" + location +
                '}';
    }
}
