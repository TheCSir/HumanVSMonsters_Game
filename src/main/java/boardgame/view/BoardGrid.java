package boardgame.view;

import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardGrid {

    private static final String MOUNTAIN = "MOUNTAIN";
    private static final String PLAINS = "PLAINS";

    Map<MapLocation, HexagonTile> tileMap;
    ArrayList<HexagonTile> hexTile;

    public BoardGrid() {

        tileMap = new HashMap<>();


    }
    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.


}


