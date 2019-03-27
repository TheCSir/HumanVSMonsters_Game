package boardgame.view;

import boardgame.gameModel.GameManager;
import boardgame.gameModel.tiles.ITile;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class HexagonTile extends Polygon implements Drawable2dTile {

    private Image image = null;

    private MapLocation gridPosition;

    private List<HexagonTile> neighbours = new ArrayList<>();

    private HexagonTile modelTile;

    //Radius - the inner radius from hexagon center to outer corner

    @Override
    public String toString() {
        return super.toString();
    }

    public HexagonTile(double x, double y, double radius) {
        super();

        drawTile(x, y, radius);
    }

    public void drawTile(double x, double y, double radius) {
        this.initalY=y;
        this.initialX=x;
        double TILE_HEIGHT = 2 * radius;

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
        setOnMouseClicked(e -> System.out.println("Clicked: " + this));

    }

    public HexagonTile(HexagonTile tile) {
        //Hold a reference to the tile from the model that this is drawing.
        this.modelTile = tile;
    }


    public void setImagePattern(String imageUrl) throws FileNotFoundException {
        //Loading image from URL
        ImagePattern imagePattern = new ImagePattern(new Image(new FileInputStream(imageUrl)));
        setFill(imagePattern);
    }

    public double getXPosition() {
        return getBoundsInParent().getCenterX();
    }

    public double getYPosition() {
        return getBoundsInParent().getCenterY();
    }


    private double initialX;
    private double initalY;
    public double getInitialX() {
        return this.initialX;
    }

    public double getInitialY() {
        return this.initalY;
    }

    public void setInitialX(double x) {
        this.initialX=x;
    }

    public void setInitalY(double y) {
        this.initalY = y;
    }

    public MapLocation getGridPosition() {
        return this.gridPosition;
    }

    public void setGridPosition(MapLocation gridPosition) {
        this.gridPosition=gridPosition;
    }

    public void addNeighbour(HexagonTile tile) {
        neighbours.add(tile);
    }

    public List<HexagonTile> getNeighbours(){
        return this.neighbours;
    }




    @Override
    public void draw(double radius) {

    }
}