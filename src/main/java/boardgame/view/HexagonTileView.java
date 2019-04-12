package boardgame.view;

import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class HexagonTileView extends TileView {

    @Override
    public double getInitalY() {
        return initalY;
    }

    private ITile modelTile;

    HexagonTileView(double x, double y, double radius, ITile hexagonalTile) {
        super();
        this.modelTile = hexagonalTile;
        drawTile(x, y, radius);
    }

    HexagonTileView() {

    }

    //Radius - the inner radius from hexagon center to outer corner

    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public void drawTile(double x, double y, double radius) {
        this.initalY = y;
        this.initialX = x;

        double n = Math.sqrt(radius * radius * 0.75); // the inner radius from hexagon center to middle of the axis
        double TILE_WIDTH = 2 * n;
        // creates the polygon using the corner coordinates

        //Should I use these coordinate to establish wheree to place piece?
        getPoints().addAll(
                x, y,
                x, y + radius,
                x + n, y + radius * 1.5,
                x + TILE_WIDTH, y + radius,
                x + TILE_WIDTH, y,
                x + n, y - radius * 0.5
        );

        // set up the visuals and a click listener for the tile
        setFill(Color.ANTIQUEWHITE);
        setStrokeWidth(1);
        setStroke(Color.BLACK);
    }


    @Override
    public void setImagePattern(String imageUrl) throws FileNotFoundException {
        //Loading image from URL
        ImagePattern imagePattern = new ImagePattern(new Image(new FileInputStream(imageUrl)));
        setFill(imagePattern);
    }


    @Override
    public double getXPosition() {
        return getBoundsInParent().getCenterX();
    }


    @Override
    public double getYPosition() {
        return getBoundsInParent().getCenterY();
    }


    private double initialX;
    private double initalY;


    @Override
    public double getInitialX() {
        return this.initialX;
    }


    @Override
    public double getInitialY() {
        return this.initalY;
    }


    @Override
    public void setInitialX(double x) {
        this.initialX = x;
    }


    @Override
    public void setInitalY(double y) {
        this.initalY = y;
    }


    @Override
    public Location getLocation() {
        return modelTile.getLocation();
    }


    @Override
    public void setLocation(Location gridPosition) {
        modelTile.setLocation(gridPosition);
    }


    @Override
    public void addNeighbour(ITile tile) {
        modelTile.addNeighbour(tile);
    }


    @Override
    public List<ITile> getNeighbours() {
        return modelTile.getNeighbours();
    }


    @Override
    public ITile getModelTile() {
        return modelTile;
    }


    @Override
    public void setModelTile(ITile modelTile) {
        this.modelTile = modelTile;
    }

    private List<HexagonTileView> neighbourViews = FXCollections.observableArrayList();


    @Override
    public List<HexagonTileView> getNeighbourViews() {
        return neighbourViews;
    }


    @Override
    public void setNeighbourViews(List<HexagonTileView> neighbourViews) {
        this.neighbourViews = neighbourViews;
    }


    @Override
    public void addNeighbourView(HexagonTileView tileView) {
        this.neighbourViews.add(tileView);
    }
}