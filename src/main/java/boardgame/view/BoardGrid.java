package boardgame.view;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import boardgame.util.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static boardgame.util.Constants.xStartOffset;
import static boardgame.util.Constants.yStartOffset;

public class BoardGrid {


    private ObservableList<HexagonTileViewPiece> pieceObservableList = FXCollections.observableArrayList();
    private ObservableMap<Location, TileView> tileViewObservableMap = FXCollections.observableHashMap();
    private ObservableList<TileView> hexagonTileViews = FXCollections.observableArrayList();


    public ObservableList<HexagonTileViewPiece> getPieceObservableList() {
        return pieceObservableList;
    }

    public void setPieceObservableList(ObservableList<HexagonTileViewPiece> pieceObservableList) {
        this.pieceObservableList = pieceObservableList;
    }

    public ObservableMap<Location, TileView> getTileViewObservableMap() {
        return tileViewObservableMap;
    }

    public void setTileViewObservableMap(ObservableMap<Location, TileView> tileViewObservableMap) {
        this.tileViewObservableMap = tileViewObservableMap;
    }

    public void addTile(TileView tileView) {
        tileViewObservableMap.put(tileView.getLocation(), tileView);
    }

    public TileView getTile(Location location) {
        return tileViewObservableMap.get(location);
    }

    public ObservableList<TileView> getHexagonTileViews() {
        return hexagonTileViews;
    }

    public void setHexagonTileViews(ObservableList<TileView> hexagonTileViews) {
        this.hexagonTileViews = hexagonTileViews;
    }

    private Pane boardPane;

    public BoardGrid(Pane boardPane) {
        this.boardPane = boardPane;
        initialiseBoardBackGround();
    }
    //Add game pieces to the game board.
    public void addPieces(List<IPiece> pieceList, Pane boardPane) {
        pieceObservableList.clear();
        for (IPiece piece : pieceList) {
            addPiece(piece, boardPane);
        }
    }


    public void addPiece(IPiece piece, Pane boardPane) {
        TileView hexView = tileViewObservableMap.get(piece.getLocation());
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

        List<TileView> hexagonTileViews = calculateTileCoord(
                boardTiles, radius, xStartOffset, yStartOffset);

        for (TileView hexagonalTile : hexagonTileViews) {

            //Add the tile to the JAvaFX pane.
            boardPane.getChildren().add(hexagonalTile);
        }

        //Add neighbouring tile views.
        for (TileView tileView : tileViewObservableMap.values()) {
            List<ITile> neighbours = tileView.getNeighbours();
            for (ITile neighbour: neighbours) {
                tileView.addNeighbourView(tileViewObservableMap.get(neighbour.getLocation()));
            }
        }
    }

    //returns a list of tiles to add to a pane.
    public List<TileView> calculateTileCoord(List<ITile> hexagonTiles, double r, double xStartOffset, double yStartOffset) {
        double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
        double TILE_HEIGHT = 2 * r;
        double TILE_WIDTH = 2 * n;
        List<TileView> hexagonTileViewList = new ArrayList<>();
        for (ITile hexagonalTile:
                hexagonTiles) {
            int xPos = hexagonalTile.getLocation().getX();
            int yPos = hexagonalTile.getLocation().getY();

            //Set starting coordinates for the tile to be drawn.
            double xCoord = xPos * TILE_WIDTH + (yPos % 2) * n + xStartOffset;
            double yCoord = yPos * TILE_HEIGHT * 0.75 + yStartOffset;

            //Create the new tile.
            TileView tile = TileViewFactory.createTileView(xCoord, yCoord, r, hexagonalTile);
            hexagonTileViewList.add(tile);


            tileViewObservableMap.put(tile.getLocation(), tile);
            hexagonTileViews.add(tile);
        }
        return hexagonTileViewList;
    }

    private void initialiseBoardBackGround() {
        // Set up the background.
        try {
            FileInputStream input = new FileInputStream("src/main/resources/wood_table_background.jpeg");
            boardPane.setBackground(new Background(new BackgroundImage(new Image(input), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException e) {
            System.out.println("what");
        }
    }

    public void setNeighbourTilesColor(HexagonTileViewPiece selectedTilePiece, Color color) {
        TileView underTile = this.getTile(selectedTilePiece.getLocation());
        List<TileView> neighbouringTiles = underTile.getNeighbourViews();
        for (TileView neighbourView : neighbouringTiles) {
            neighbourView.setFill(color);
        }
    }

    // EXPERIMENT: Highlight piece range
    public void setNeighbourTilesColor(HexagonTileViewPiece selectedTilePiece, int depth) {
        for(int i = 0; i < depth; i++){
            TileView underTile = this.getTile(selectedTilePiece.getLocation());
            List<TileView> rangeTiles = underTile.getNeighbourViews();
            for (TileView neighbourView : rangeTiles) {
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
}


