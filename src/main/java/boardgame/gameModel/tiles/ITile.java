package boardgame.gameModel.tiles;

import boardgame.util.Location;

import java.util.List;

public interface ITile {

    //Pass a location containing x and y (for a 2d grid) to the tile.
    void setLocation(Location location);

    //Return the location (containing grid position)
    Location getLocation();

    void addNeighbour(ITile tile);


    List<ITile> getNeighbours();

    int getPieceID();


}
