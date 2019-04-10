package boardgame.util;

import java.util.ArrayList;
import java.util.List;

public class HexGridUtil {

    //Calculates what the neighbour positions will be on a hexagonal grid.
    //TODO May belong in a different class.
    public static List<Location> getNeighbourPositions(Location location) {
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
            NW = LocationFactory.createLocation(tGridX - 1, tGRidY - 1);
            NE = LocationFactory.createLocation(tGridX, tGRidY - 1);
            W = LocationFactory.createLocation(tGridX - 1, tGRidY);
            E = LocationFactory.createLocation(tGridX + 1, tGRidY);
            SW = LocationFactory.createLocation(tGridX - 1, tGRidY + 1);
            SE = LocationFactory.createLocation(tGridX, tGRidY + 1);

        }else {
            NW = LocationFactory.createLocation(tGridX , tGRidY - 1);
            NE = LocationFactory.createLocation(tGridX+1, tGRidY - 1);
            W = LocationFactory.createLocation(tGridX - 1, tGRidY);
            E = LocationFactory.createLocation(tGridX + 1, tGRidY);
            SW = LocationFactory.createLocation(tGridX, tGRidY + 1);
            SE = LocationFactory.createLocation(tGridX+1, tGRidY + 1);
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
