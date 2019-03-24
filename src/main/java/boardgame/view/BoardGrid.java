package boardgame.view;

import boardgame.util.Constants;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardGrid {

    Map<MapLocation, HexagonTile> tileMap;
    ArrayList<HexagonTile> hexTile;

    public BoardGrid() {

        tileMap = new HashMap<>();


    }

    public HexagonTile getTile(MapLocation mapLocation) {
        return tileMap.get(mapLocation);
    }


    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.


}


