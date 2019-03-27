package boardgame.gameModel.tiles;

import boardgame.gameModel.Location;

import java.util.ArrayList;
import java.util.List;

public class HexagonalTile extends Tile {

    private final int numSides = 6;
    private Location location;

    public HexagonalTile(Location location) {
        super(location);

    }


    @Override
    public void setGridPosition(Location location) {
        this.location=location;
    }

    @Override
    public Location getGridPosition() {
        return location;
    }

    @Override
    public void addNeighbour() {

    }

    @Override
    public List<ITile> getNeighbours() {
        return null;
    }

    public List<Location> getNeighbourPositions (int tGridX, int tGRidY) {
        List<Location> neighbourLocations = new ArrayList<>();
        Location NW;
        Location NE;
        Location W;
        Location E;
        Location SW;
        Location SE;


        //Calculation is based on 0,4 coordinate for example. On a hex grid there is a slightly different
        //calculation depending on whether a tile on the y-axis is odd or even (or equivalent for the x-axis
        if (tGRidY % 2 == 0) {
            NW = new Location(tGridX - 1, tGRidY - 1);
            NE = new Location(tGridX, tGRidY - 1);
            W = new Location(tGridX - 1, tGRidY);
            E = new Location(tGridX + 1, tGRidY);
            SW = new Location(tGridX - 1, tGRidY + 1);
            SE = new Location(tGridX, tGRidY + 1);

        }else {
            NW = new Location(tGridX , tGRidY - 1);
            NE = new Location(tGridX+1, tGRidY - 1);
            W = new Location(tGridX - 1, tGRidY);
            E = new Location(tGridX + 1, tGRidY);
            SW = new Location(tGridX, tGRidY + 1);
            SE = new Location(tGridX+1, tGRidY + 1);


        }
        neighbourLocations.add(NW);
        neighbourLocations.add(NE);
        neighbourLocations.add(W);
        neighbourLocations.add(E);
        neighbourLocations.add(SW);
        neighbourLocations.add(SE);
        return neighbourLocations;
    }

}
