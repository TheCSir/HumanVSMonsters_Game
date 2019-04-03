package boardgame.view;

import boardgame.gameModel.Location;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static boardgame.util.Constants.xStartOffset;
import static boardgame.util.Constants.yStartOffset;

public class BoardGrid {

    Map<Location, HexagonTileView> tileMap;
    ArrayList<HexagonTileView> hexTile;
    private ObservableList<HexagonTileView> pieceObservableList = FXCollections.observableArrayList();

    public BoardGrid() {

        tileMap = new HashMap<>();

    }

    public HexagonTileView getTile(Location mapLocation) {
        return tileMap.get(mapLocation);
    }
    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.

    //Add game pieces to the game board.
    public ObservableList<HexagonTileViewPiece> addPieces(ObservableList<HexagonTileView> tileViewObservableList, List<IPiece> pieceList, Pane boardPane) {
        ObservableList<HexagonTileViewPiece> pieces = FXCollections.observableArrayList();

        for (HexagonTileView h: tileViewObservableList) {
            for (IPiece piece:pieceList) {

                if (h.getLocation().equals(piece.getLocation())) {
                    double xCoord = h.getInitialX();
                    double yCoord = h.getInitialY();
                    HexagonTileViewPiece pieceTile = new HexagonTileViewPiece(xCoord, yCoord, Constants.TILERADIUS, piece);
                    try {
                        pieceTile.setImagePattern(Constants.BIRDPNG);
                    }catch (FileNotFoundException e) {
                        System.out.println("Image File not found!");
                    }

                    boardPane.getChildren().add(pieceTile);
                    pieces.add(pieceTile);
                }
            }
        }

        return pieces;
    }

    public ObservableList<HexagonTileView> drawBasicGrid(List<ITile> boardTiles, double radius, Pane boardPane) {

        List<HexagonTileView> hexagonTileViews = calculateTileCoord(
                boardTiles, radius, xStartOffset, yStartOffset);

        for (HexagonTileView hexagonalTile: hexagonTileViews) {

            //Add the tile to the JAvaFX pane.
            boardPane.getChildren().add(hexagonalTile);

        }

        drawTileGridPos(hexagonTileViews, boardPane);
        return FXCollections.observableArrayList(hexagonTileViews);
    }

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

    //Draw the tile coordinate to help with debugging.
    public void drawTileGridPos(List<HexagonTileView> hexagonTileViewList, Pane boardPane) {

        for (HexagonTileView hexagonalTile: hexagonTileViewList) {


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


