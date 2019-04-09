package boardgame.gameModel.board;

import boardgame.gameModel.Location;
import boardgame.gameModel.LocationFactory;
import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;

import java.util.HashMap;
import java.util.Map;

public class Map1 {

    private static final String MOUNTAIN = "Mountain";
    private static final String PLAINS = "Plains";

    public Map1() {


        int defaultXSize = 15;
        int defaultYSize = 15;
        Map<Location, ITile> map1= new HashMap<>();


        for (int i=0; i<defaultXSize; i++){
            for (int j=0; j<defaultYSize; j++) {
                Location l = LocationFactory.createLocation(i, j);
                map1.put(l, new HexagonalTile(l));
            }
        }

    }

}
