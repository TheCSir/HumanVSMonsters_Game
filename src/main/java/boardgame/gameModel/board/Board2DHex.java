package boardgame.gameModel.board;

import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board2DHex extends Board {
    private List<HexagonalTile> hexagonalTiles;
    @Override
    public void setUpTiles() {
        Util util = new Util();
        String data = null;
        try {
            data = util.readFile("C:\\Users\\Mohamad\\Desktop\\Other stuff\\Uni\\Sem 1 2019\\OOSD\\A1\\OOSD-Assignment\\src\\main\\java\\boardgame\\gameModel\\maps\\map1.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Location> locData = util.convertJsonToObjectArray(data);

        hexagonalTiles = new ArrayList<HexagonalTile>();

        for(Location l : locData){
            hexagonalTiles.add(new HexagonalTile(l.getXAxis(), l.getYAxis()));
        }

    }

    public List<HexagonalTile> getHexagonalTiles(){
        return this.hexagonalTiles;
    }
}
