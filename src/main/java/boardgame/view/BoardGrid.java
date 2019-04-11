package boardgame.view;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import boardgame.util.Location;
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


    private ObservableList<HexagonTileViewPiece> pieceObservableList = FXCollections.observableArrayList();

    public ObservableList<HexagonTileViewPiece> getPieceObservableList() {
        return pieceObservableList;
    }

    public void setPieceObservableList(ObservableList<HexagonTileViewPiece> pieceObservableList) {
        this.pieceObservableList = pieceObservableList;
    }

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
    public void addPieces(List<IPiece> pieceList, Pane boardPane) {
        pieceObservableList.clear();
        for (IPiece piece : pieceList) {
            addPiece(piece, boardPane);
        }
    }


    public void addPiece(IPiece piece, Pane boardPane) {
        HexagonTileView hexView = tileViewObservableMap.get(piece.getLocation());
        double xCoord = hexView.getInitialX();
        double yCoord = hexView.getInitialY();
        HexagonTileViewPiece pieceTile = new HexagonTileViewPiece(xCoord, yCoord, Constants.TILERADIUS, piece);
        try {
            pieceTile.setImagePattern(imageURL(piece));
        } catch (FileNotFoundException e) {
            System.out.println("Image File not found!");
        }

        boardPane.getChildren().add(pieceTile);
        pieceObservableList.add(pieceTile);
    }


    private String imageURL(IPiece iPiece) {
        return "src/main/resources/"
                + iPiece.getClass().getName()
                + ".png";
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

    // EXPERIMENT: Highlight piece range
    public void setNeighbourTilesColor(HexagonTileViewPiece selectedTilePiece, int depth) {
        for(int i = 0; i < depth; i++){
            HexagonTileView underTile = this.getTile(selectedTilePiece.getLocation());
            List<HexagonTileView> rangeTiles = underTile.getNeighbourViews();
            for (HexagonTileView neighbourView : rangeTiles) {
                neighbourView.setFill(Color.BLUE);
            }
        }
    }

    public void removePiece(IPiece piece, Pane boardPane) {
        for (HexagonTileViewPiece viewPiece : pieceObservableList) {
            if (viewPiece.getiPiece().equals(piece)) {
                boardPane.getChildren().remove(viewPiece);
            }
        }

    }

    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.

}


