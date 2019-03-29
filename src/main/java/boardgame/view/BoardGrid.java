package boardgame.view;

import boardgame.gameModel.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardGrid {

    Map<Location, HexagonTileView> tileMap;
    ArrayList<HexagonTileView> hexTile;

    public BoardGrid() {

        tileMap = new HashMap<>();


    }

    public HexagonTileView getTile(Location mapLocation) {
        return tileMap.get(mapLocation);
    }


    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.


}


