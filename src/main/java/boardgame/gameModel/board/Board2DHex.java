package boardgame.gameModel.board;

import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;
import boardgame.gameModel.tiles.TileFactory;
import boardgame.util.Constants;
import boardgame.util.HexGridUtil;
import boardgame.util.Location;
import boardgame.util.LocationFactory;
import javafx.collections.ObservableMap;

import java.util.List;

public class Board2DHex extends Board2d {



    //Variable size
    public void setUpTiles(int rows, int columns) {

        for (int x=0; x<rows; x++) {
            for (int y=0; y<columns; y++) {
                Location location = LocationFactory.createLocation(x, y);
                ITile hexagonalTile = TileFactory.createTile(HexagonalTile.class.getName(), location);
                // hexagonalTiles.add(hexagonalTile);
                boardGrid.put(location, hexagonalTile);
            }
        }

        //For each tile add the neighbouring tiles.
        addNeighbours();
    }

    //Default size
    @Override
    public void setUpTiles() {

        for (int x=0; x<Constants.DEFAULTBOARDROWS; x++) {
               for (int y=0; y<Constants.DEFAULTBOARDCOLUMNS; y++) {
                   Location location = LocationFactory.createLocation(x, y);
                   ITile hexagonalTile = TileFactory.createTile(HexagonalTile.class.getName(), location);
                  // hexagonalTiles.add(hexagonalTile);
                   boardGrid.put(location, hexagonalTile);
               }
        }

        //For each tile add the neighbouring tiles.
        addNeighbours();
    }

    @Override
    public void addTile(ITile tile) {
        boardGrid.put(tile.getLocation(), tile);
    }

    @Override
    public void addTile(Location location, ITile tile) {
        boardGrid.put(location, tile);
    }

    @Override
    public void deleteTile(Location location, ITile tile) {
        boardGrid.put(location, tile);
    }

    @Override
    public ObservableMap<Location, ITile> getTiles() {
        return this.boardGrid;
    }

    @Override
    public boolean checkMapLocation(Location location, int rows, int columns) {
        return super.checkMapLocation(location, rows, columns);
    }


    public ObservableMap<Location, ITile> getBoardGrid() {
        return boardGrid;
    }

    public void setBoardGrid(ObservableMap<Location, ITile> boardGrid) {
        this.boardGrid = boardGrid;
    }

    //For each tile store their neighbouring tiles.
    private void addNeighbours() {
        for (ITile t: boardGrid.values()) {
            List<Location> neighbourLocations = HexGridUtil.getNeighbourPositions(t.getLocation());

            for (Location location: neighbourLocations) {
                if(checkMapLocation(location, Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS)){
                    t.addNeighbour(boardGrid.get(location));
                }
            }
        }
    }

}
