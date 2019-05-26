package boardgame.gameModel.board;

import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;
import boardgame.gameModel.tiles.TileFactory;
import boardgame.util.Constants;
import boardgame.util.HexGridUtil;
import boardgame.util.Location;
import boardgame.util.LocationFactory;

import java.util.List;

public class Board2DHex extends Board2d {


    public Board2DHex(int rows, int columns) {
        super();
        setUpTiles(rows, columns);
    }

    //Variable size
    public void setUpTiles(int rows, int columns) {

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                Location location = LocationFactory.createLocation(x, y);
                ITile hexagonalTile = TileFactory.createTile(HexagonalTile.class.getName(), location);
                addTile(location, hexagonalTile);
            }
        }

        //For each tile add the neighbouring tiles.
        addNeighbours(rows, columns);
    }

    //Default size
    @Override
    public void setUpTiles() {

        for (int x = 0; x < Constants.DEFAULTBOARDROWS; x++) {
            for (int y = 0; y < Constants.DEFAULTBOARDCOLUMNS; y++) {
                Location location = LocationFactory.createLocation(x, y);
                ITile hexagonalTile = TileFactory.createTile(HexagonalTile.class.getName(), location);
                addTile(location, hexagonalTile);
            }
        }

        //For each tile add the neighbouring tiles.
        addNeighbours(Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS);
    }

    @Override
    public void addTile(ITile tile) {
        getTiles().put(tile.getLocation(), tile);
    }

    @Override
    public void addTile(Location location, ITile tile) {
        getTiles().put(location, tile);
    }

    @Override
    public void deleteTile(Location location, ITile tile) {
        getTiles().remove(location, tile);
    }

    //For each tile store their neighbouring tiles.
    private void addNeighbours(int rows, int columns) {
        for (ITile t : getTiles().values()) {
            List<Location> neighbourLocations = HexGridUtil.getNeighbourPositions(t.getLocation());

            for (Location location : neighbourLocations) {
                if (checkMapLocation(location, rows, columns)) {
                    t.addNeighbour(getTiles().get(location));
                }
            }
        }
    }
}
