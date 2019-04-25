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


/**
 * The Board grid class. This view class is responsible for drawing the tiles and pieces on the grid.
 * We are considering splitting up some of the responsibility here for assignment 2. It is the information
 * expert for the tiles and piece on the grid for their view representations. It is the main entry point
 * for the view. We feel that the class is fairly cohesive. However, the coupling could be reduced by
 * perhaps separating out the Piece Views from the Tile Views.
 */
public class BoardGrid {


    private ObservableList<HexagonTileViewPiece> pieceObservableList = FXCollections.observableArrayList();
    private ObservableMap<Location, TileView> tileViewObservableMap = FXCollections.observableHashMap();
    private ObservableList<TileView> hexagonTileViews = FXCollections.observableArrayList();

    private TileView targetTile;
    private HexagonTileViewPiece selectedTilePiece = null;

    public TileView getTargetTile() {
        return targetTile;
    }

    public void setTargetTile(TileView targetTile) {
        this.targetTile = targetTile;
    }

    public HexagonTileViewPiece getSelectedTilePiece() {
        return selectedTilePiece;
    }

    public void setSelectedTilePiece(HexagonTileViewPiece selectedTilePiece) {
        this.selectedTilePiece = selectedTilePiece;
    }


    /**
     * Instantiates a new Board grid.
     *
     * @param boardPane the board pane
     */
    public BoardGrid(Pane boardPane) {
        this.boardPane = boardPane;
        initialiseBoardBackGround();
    }

    /**
     * Gets piece observable list.
     *
     * @return the piece observable list
     */
    public ObservableList<HexagonTileViewPiece> getPieceObservableList() {
        return pieceObservableList;
    }

    /**
     * Gets tile.
     *
     * @param location the location
     * @return the tile
     */
    public TileView getTile(Location location) {
        return tileViewObservableMap.get(location);
    }

    /**
     * Gets hexagon tile views.
     *
     * @return the hexagon tile views
     */
    public ObservableList<TileView> getHexagonTileViews() {
        return hexagonTileViews;
    }

    private Pane boardPane;

    /**
     * Add piece.
     *
     * @param piece the piece
     */
    public void addPiece(IPiece piece) {
        TileView hexView = tileViewObservableMap.get(piece.getLocation());
        double xCoord = hexView.getInitialX();
        double yCoord = hexView.getInitialY();
        HexagonTileViewPiece pieceTile = TileViewPieceFactory.createViewTilePiece(xCoord, yCoord, Constants.TILERADIUS, piece);
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

    /**
     * Draw basic grid.
     *
     * @param boardTiles the board tiles
     * @param radius     the radius
     * @param boardPane  the board pane
     */
    public void drawBasicGrid(List<ITile> boardTiles, double radius, Pane boardPane) {

        List<TileView> hexagonTileViews = calculateTileCoord(
                boardTiles, radius, Constants.xStartOffset, Constants.yStartOffset);

        for (TileView hexagonalTile : hexagonTileViews) {

            //Add the tile to the JAvaFX pane.
            boardPane.getChildren().add(hexagonalTile);
        }

        //Add neighbouring tile views.
        for (TileView tileView : tileViewObservableMap.values()) {
            List<ITile> neighbours = tileView.getNeighbours();
            for (ITile neighbour : neighbours) {
                tileView.addNeighbourView(tileViewObservableMap.get(neighbour.getLocation()));
            }
        }
    }

    /**
     * Calculate tile coord list.
     *
     * @param hexagonTiles the hexagon tiles
     * @param r            the r
     * @param xStartOffset the x start offset
     * @param yStartOffset the y start offset
     * @return the list
     */
//returns a list of tiles to add to a pane.
    private List<TileView> calculateTileCoord(List<ITile> hexagonTiles, double r, double xStartOffset, double yStartOffset) {
        double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
        double TILE_HEIGHT = 2 * r;
        double TILE_WIDTH = 2 * n;
        List<TileView> hexagonTileViewList = new ArrayList<>();
        for (ITile hexagonalTile :
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
            FileInputStream input = new FileInputStream("src/main/resources/wood_table_with_dice.jpeg");
            boardPane.setBackground(new Background(new BackgroundImage(new Image(input), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException e) {
            System.out.println("what");
        }
    }

    /**
     * Sets neighbour tiles color.
     *
     * @param selectedTilePiece the selected tile piece
     * @param color             the color
     */
    public void setNeighbourTilesColor(HexagonTileViewPiece selectedTilePiece, Color color) {
        TileView underTile = this.getTile(selectedTilePiece.getLocation());
        List<TileView> neighbouringTiles = underTile.getNeighbourViews();
        for (TileView neighbourView : neighbouringTiles) {
            neighbourView.setFill(color);
        }
    }

    /**
     * Remove piece.
     *
     * @param piece the piece
     */
    public void removePiece(IPiece piece) {
        for (HexagonTileViewPiece viewPiece : pieceObservableList) {
            if (viewPiece.getiPiece().equals(piece)) {
                boardPane.getChildren().remove(viewPiece);
            }
        }
    }

    public Pane getBoardPane() {
        return boardPane;
    }
}


