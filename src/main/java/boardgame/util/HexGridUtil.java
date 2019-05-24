package boardgame.util;

import boardgame.view.IBoardGrid;
import boardgame.view.TileView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.abs;

public class HexGridUtil {

    //Calculates what the neighbour positions will be on a hexagonal grid.
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

    //*******************************************************************************
    //
    //  Utility methods for calculating distance between grid points in a hex grid.
    //
    //********************************************************************************

    public static int offset_distance(Location a, Location b) {
        Hex locationA = new Hex(a.getX(), a.getY());
        Hex locationB = new Hex(b.getX(), b.getY());
        Cube ac = oddr_to_cube(locationA);
        Cube bc = oddr_to_cube(locationB);
        return cube_distance(ac, bc);
    }

    private static int cube_distance(Cube a, Cube b) {
        return (abs(a.getX() - b.getX()) + abs(a.getY() - b.getY()) + abs(a.getZ() - b.getZ())) / 2;
    }

    private static Location cube_to_oddr(Cube cube) {
        int col = cube.getX() + (cube.getZ() - (cube.getZ() & 1)) / 2;
        int row = cube.getZ();
        return new Location(col, row);
    }

    private static Cube oddr_to_cube(Hex hex) {
        int x = hex.getCol() - (hex.getRow() - (hex.getRow() & 1)) / 2;
        int z = hex.getRow();
        int y = -x - z;
        return new Cube(x, y, z);
    }

    public static List<TileView> visitAllTiles(int distance, IBoardGrid bg, Location location) {
        //https://www.redblobgames.com/grids/hexagons/

        //Start of very inefficent BFS. Will do for the moment.
        //Probably refactor and move to separate class.
        TileView underTile = bg.getTile(location);
        List<TileView> visited = new ArrayList<>();
        Queue<TileView> queue = new LinkedList<>();
        queue.add(underTile);
        visited.add(underTile);
        int q = 0;
        while (!queue.isEmpty() && q < 100000) {
            //System.out.println("queue = " + queue.peek());
            TileView x = queue.poll();
            List<TileView> neighbours = x.getNeighbourViews();
            int i;
            for (i = 0; i < neighbours.size(); i++) {
                TileView tileView = neighbours.get(i);
                queue.add(neighbours.get(i));
                if (!visited.contains(tileView)) {
                    visited.add(neighbours.get(i));
                }
            }
            q++;
        }
        return visited;
    }
}
