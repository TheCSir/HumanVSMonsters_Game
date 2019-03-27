package boardgame.gameModel.tiles;

import boardgame.gameModel.Location;

import java.util.List;

public interface ITile {

    //Pass a location containing x and y (for a 2d grid) to the tile.
    public void setGridPosition(Location location);

    //Return the location (containing grid position)
    public Location getGridPosition();

    public void addNeighbour();


    public List<ITile> getNeighbours();




}
