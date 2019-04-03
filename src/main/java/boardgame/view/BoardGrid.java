package boardgame.view;

import boardgame.gameModel.Location;
import boardgame.gameModel.tiles.ITile;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//    public List<HexagonTileView> drawBasicGrid(List<ITile> boardTiles, double radius, Pane boardPane) {
//        double xStartOffset = 40;
//        double yStartOffset = 40;
//
//        List<HexagonTileView> hexagonTileViews = calculateTileCoord(
//                boardTiles, Constants.TILERADIUS, xStartOffset, yStartOffset);
//
//        for (HexagonTileView hexagonalTile: hexagonTileViews) {
//
//            //Draw the tile coordinate to help with debugging.
//            Text gridloc = new Text(hexagonalTile.getModelTile().getLocation().getX() + ", "
//                    + hexagonalTile.getModelTile().getLocation().getY());
//            gridloc.setX(hexagonalTile.getXPosition());
//            gridloc.setY(hexagonalTile.getYPosition());
//
//
//            //Add the tile to the JAvaFX pane.
//            boardPane.getChildren().add(hexagonalTile);
//            boardPane.getChildren().add(gridloc);
//
//            hexagonalTile.setOnMouseClicked(e -> handleTileClicked(hexagonalTile));
//            tiles.add(hexagonalTile);
//            tileMap.put(hexagonalTile.getLocation(), hexagonalTile);
//        }
//    }
//

    //returns a list of tiles to add to a pane.
    public List<HexagonTileView> calculateTileCoord(List<ITile> hexagonTiles, double r, double xStartOffset, double yStartOffset){
        double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
        double TILE_HEIGHT = 2 * r;
        double TILE_WIDTH = 2 * n;
        List<HexagonTileView> hexagonTileViewList = new ArrayList<>();
        for (ITile hexagonalTile:
                hexagonTiles) {
            int xPos = hexagonalTile.getLocation().getX();
            int yPos = hexagonalTile.getLocation().getY();

            //Set starting coordinates for the tile to be drawn.
            double xCoord = xPos * TILE_WIDTH + (yPos % 2) * n + xStartOffset;
            double yCoord = yPos * TILE_HEIGHT * 0.75 + yStartOffset;

            //Create the new tile.
            HexagonTileView tile = new HexagonTileView(xCoord, yCoord, r, hexagonalTile);
            hexagonTileViewList.add(tile);
        }
        return hexagonTileViewList;
    }

    public void drawTileGridPos(List<HexagonTileView> hexagonTileViewList, Pane boardPane) {

        for (HexagonTileView hexagonalTile: hexagonTileViewList) {

            //Draw the tile coordinate to help with debugging.
            Text gridloc = new Text(hexagonalTile.getModelTile().getLocation().getX() + ", "
                    + hexagonalTile.getModelTile().getLocation().getY());
            gridloc.setX(hexagonalTile.getXPosition());
            gridloc.setY(hexagonalTile.getYPosition());
            boardPane.getChildren().add(gridloc);
        }
    }

    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.


}


