package boardgame.view;

import boardgame.gameModel.Location;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static boardgame.util.Constants.xStartOffset;
import static boardgame.util.Constants.yStartOffset;

public class BoardGrid {

    ArrayList<HexagonTileView> hexTile;
    private ObservableList<HexagonTileView> pieceObservableList = FXCollections.observableArrayList();

    public ObservableMap<Location, HexagonTileView> getTileViewObservableMap() {
        return tileViewObservableMap;
    }

    public void setTileViewObservableMap(ObservableMap<Location, HexagonTileView> tileViewObservableMap) {
        this.tileViewObservableMap = tileViewObservableMap;
    }

    public void addTile(HexagonTileView tileView) {
        tileViewObservableMap.put(tileView.getLocation(), tileView);
    }

    public HexagonTileView getTile(Location location) {
        return tileViewObservableMap.get(location);
    }

    private ObservableMap<Location, HexagonTileView> tileViewObservableMap = FXCollections.observableHashMap();

    public ObservableList<HexagonTileView> getHexagonTileViews() {
        return hexagonTileViews;
    }

    public void setHexagonTileViews(ObservableList<HexagonTileView> hexagonTileViews) {
        this.hexagonTileViews = hexagonTileViews;
    }

    private ObservableList<HexagonTileView> hexagonTileViews = FXCollections.observableArrayList();

    public BoardGrid() {

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
                        pieceTile.setImagePattern(imageURL(piece));
                        System.out.println(imageURL(piece));
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

//    public HexagonTileViewPiece addPiece(HexagonTileView , IPiece piece, Pane boardPane) {
//        for (HexagonTileView h: tileViewObservableList) {
//            if (h.getLocation().equals(piece.getLocation())) {
//                double xCoord = h.getInitialX();
//                double yCoord = h.getInitialY();
//                HexagonTileViewPiece pieceTile = new HexagonTileViewPiece(xCoord, yCoord, Constants.TILERADIUS, piece);
//                try {
//                    pieceTile.setImagePattern(Constants.BIRDPNG);
//                }catch (FileNotFoundException e) {
//                    System.out.println("Image File not found!");
//                }
//
//                boardPane.getChildren().add(pieceTile);
//                pieces.add(pieceTile);
//            }
//        }
//
//    }

    public String imageURL(IPiece iPiece) {
        String i  = "src/main/resources/"
                + iPiece.getClass().getName()
                + ".png";
        System.out.println(i);
        return i;
    }

    public void drawBasicGrid(List<ITile> boardTiles, double radius, Pane boardPane) {

        List<HexagonTileView> hexagonTileViews = calculateTileCoord(
                boardTiles, radius, xStartOffset, yStartOffset);

        for (HexagonTileView hexagonalTile: hexagonTileViews) {

            //Add the tile to the JAvaFX pane.
            boardPane.getChildren().add(hexagonalTile);
        }

        //Add neighbouring tile views.
        for (HexagonTileView tileView: tileViewObservableMap.values()) {
            List<ITile> neighbours = tileView.getNeighbours();
            for (ITile neighbour: neighbours) {
                tileView.addNeighbourView(tileViewObservableMap.get(neighbour.getLocation()));
            }
        }
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


            tileViewObservableMap.put(tile.getLocation(), tile);
            hexagonTileViews.add(tile);
        }
        return hexagonTileViewList;
    }

    public void setNeighbourTilesColor(HexagonTileViewPiece selectedTilePiece, Color color) {
        HexagonTileView underTile = this.getTile(selectedTilePiece.getLocation());
        List<HexagonTileView> neighbouringTiles = underTile.getNeighbourViews();
        for (HexagonTileView neighbourView : neighbouringTiles) {
            neighbourView.setFill(color);
        }
    }

    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.

}


