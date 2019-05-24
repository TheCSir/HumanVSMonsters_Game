package boardgame.view;

import boardgame.controller.GameController;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import boardgame.util.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
public class BoardGrid implements IBoardGrid {

    private final GameController gc;

    public ObservableList<HexagonTileViewPiece> getPieceObservableList() {
        return pieceObservableList;
    }

    private final ObservableList<HexagonTileViewPiece> pieceObservableList = FXCollections.observableArrayList();
    private final ObservableMap<Location, TileView> tileViewObservableMap = FXCollections.observableHashMap();

    private double radius;

    @Override
    public ObservableMap<Location, TileView> getTileViewObservableMap() {
        return tileViewObservableMap;
    }

    @Override
    public TileView getTileView(Location location) {
        return tileViewObservableMap.get(location);
    }

    private final ObservableList<TileView> hexagonTileViews = FXCollections.observableArrayList();

    /**
     * Instantiates a new Board grid.
     *
     * @param boardPane the board pane
     * @param gameController
     */
    BoardGrid(Pane boardPane, GameController gameController) {
        this.boardPane = boardPane;
        initialiseBoardBackGround();
        this.gc = gameController;
    }

    /**
     * Gets tile.
     *
     * @param location the location
     * @return the tile
     */
    @Override
    public TileView getTile(Location location) {
        return tileViewObservableMap.get(location);
    }

    private final Pane boardPane;

    /**
     * Add piece.
     *
     * @param piece the piece
     * @return
     */
    @Override
    public HexagonTileViewPiece addPiece(IPiece piece) {
        TileView hexView = tileViewObservableMap.get(piece.getLocation());
        double xCoord = hexView.getInitialX();
        double yCoord = hexView.getInitialY();
        HexagonTileViewPiece pieceTile = TileViewPieceFactory.createViewTilePiece(xCoord, yCoord, this.radius, piece);
        try {
            pieceTile.setImagePattern(imageURL(piece));
        } catch (FileNotFoundException e) {
            System.out.println("Image File not found!");
        }

        piece.locationPropertyProperty().addListener((observable) ->
                PieceView.changePiecePosition(pieceTile, getTile(pieceTile.getLocation())));

        pieceTile.setOnDragDetected(event -> {
            //TODO set state to move
            /* drag was detected, start a drag-and-drop gesture*/
            /* allow any transfer mode */
            Dragboard db = pieceTile.startDragAndDrop(TransferMode.ANY);

            /* Put a string on a dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString(pieceTile.getClass().getSimpleName());
            db.setContent(content);

            event.consume();
        });

        boardPane.getChildren().add(pieceTile);
        pieceObservableList.add(pieceTile);
        return pieceTile;
    }


    @Override
    public String imageURL(IPiece iPiece) {

        String PieceName = iPiece.getClass().getName();

        if (PieceName == "boardgame.gameModel.pieces.Minion"){

            PieceName = "boardgame.gameModel.pieces." + iPiece.getPieceName().getValue();
        }

        System.out.println(PieceName);
        return "src/main/resources/"
                + PieceName
                + ".png";
    }

    /**
     * Draw basic grid.
     *
     * @param boardTiles the board tiles
     * @param boardRows the board rows
     * @param boardColumns the board columns
     * @param boardPane  the board pane
     */
    @Override
    public void drawBasicGrid(List<ITile> boardTiles, int boardRows, int boardColumns, Pane boardPane) {

        this.radius = calculateTileRadius(boardRows, boardColumns);

        List<TileView> hexagonTileViews = calculateTileCoord(
                boardTiles, this.radius, Constants.xStartOffset, Constants.yStartOffset);

        for (TileView hexagonalTile : hexagonTileViews) {

            //Add the tile to the JAvaFX pane.
            boardPane.getChildren().add(hexagonalTile);
        }

        try {
            //Add neighbouring tile views.
            for (TileView tileView : tileViewObservableMap.values()) {
                List<ITile> neighbours = tileView.getNeighbours();
                for (ITile neighbour : neighbours) {
                    if(neighbour != null)
                        tileView.addNeighbourView(tileViewObservableMap.get(neighbour.getLocation()));
                }
            }
        }
        catch(Exception ex){
            throw ex;
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
    @Override
    public List<TileView> calculateTileCoord(List<ITile> hexagonTiles, double r, double xStartOffset, double yStartOffset) {
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
            //Set tile handlers
            tile.setOnMouseClicked(e -> gc.handleTileClicked(tile));
            hexagonTileViewList.add(tile);


            tileViewObservableMap.put(tile.getLocation(), tile);
            hexagonTileViews.add(tile);
        }
        return hexagonTileViewList;
    }

    @Override
    public void initialiseBoardBackGround() {
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
    @Override
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
    @Override
    public void removePiece(IPiece piece) {
        for (HexagonTileViewPiece viewPiece : pieceObservableList) {
            if (viewPiece.getiPiece().equals(piece)) {
                boardPane.getChildren().remove(viewPiece);
            }
        }
    }

    @Override
    public Pane getBoardPane() {
        return boardPane;
    }

    @Override
    public double calculateTileRadius(int boardRows, int boardColumns) {
        int m;
        // Grab biggest number between rows and columns
        if (boardRows> boardColumns)
            m = boardRows;
        else
            m = boardColumns;

        // Use TILERADIUS and DEFAULTBOARDROWS as reference points for calculating radius to fit the screen
        return (Constants.TILERADIUS * Constants.DEFAULTBOARDROWS) / m;
    }
}


