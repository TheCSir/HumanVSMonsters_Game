package boardgame.gameModel.tiles;

import boardgame.gameModel.Location;

import java.util.ArrayList;
import java.util.List;

public class HexagonalTile extends Tile {

    private final int numSides = 6;

    public HexagonalTile(Location location) {
        super(location);
        neighbourPositions = getNeighbourPositions(location);
    }


    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    //Calculates what the neighbour positions will be on a hexagonal grid.
    //TODO May belong in a different class.
    public List<Location> getNeighbourPositions(Location location) {
        int tGridX = location.getX();
        int tGRidY = location.getY();
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

    @Override
    public int getPieceID() {
        return 0;
    }
}
