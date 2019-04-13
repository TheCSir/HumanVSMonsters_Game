package boardgame.view;

import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.scene.shape.Polygon;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class represents a drawn tile. The only class that extends it currently is HexagonTileView.
 */
public abstract class TileView extends Polygon {


    /**
     * Gets inital y in window (not grid).
     *
     * @return the inital y co-ordinate in the Pane.
     */
    public abstract double getInitalY();

    /**
     * Sets inital y.
     *
     * @param y the y
     */
    public abstract void setInitalY(double y);

    /**
     * Draw this tile in a Pane.
     *
     * @param x      the x co-ordinate in the Pane
     * @param y      the y co-ordinate in the Pane
     * @param radius the radius of the tile to draw (different shapes will still all have a radius).
     */
    public abstract void drawTile(double x, double y, double radius);

    /**
     * Sets the tile image. Pass this the path to an image.
     *
     * @param imageUrl the image url
     * @throws FileNotFoundException throws an exception if the file doesn't exist.
     */
    public abstract void setImagePattern(String imageUrl) throws FileNotFoundException;

    /**
     * Gets x position.
     *
     * @return the x position
     */
    public abstract double getXPosition();

    /**
     * Gets y position.
     *
     * @return the y position
     */
    public abstract double getYPosition();

    /**
     * Gets initial x.
     *
     * @return the initial x
     */
    public abstract double getInitialX();

    /**
     * Sets initial x.
     *
     * @param x the x
     */
    public abstract void setInitialX(double x);

    /**
     * Gets initial y.
     *
     * @return the initial y
     */
    public abstract double getInitialY();

    /**
     * Gets location.
     *
     * @return the location
     */
    public abstract Location getLocation();

    /**
     * Sets location.
     *
     * @param gridPosition the grid position
     */
    public abstract void setLocation(Location gridPosition);

    /**
     * Add neighbour.
     *
     * @param tile the tile
     */
    public abstract void addNeighbour(ITile tile);

    /**
     * Gets neighbours.
     *
     * @return the neighbours of this tile on the grid.
     */
    public abstract List<ITile> getNeighbours();

    /**
     * Gets model tile.
     *
     * @return the model tile
     */
    public abstract ITile getModelTile();

    /**
     * Sets model tile.
     *
     * @param modelTile the model tile
     */
    public abstract void setModelTile(ITile modelTile);

    /**
     * Gets the neighbouring tiles.
     *
     * @return the neighbour views
     */
    public abstract List<TileView> getNeighbourViews();

    /**
     * Sets the neighbouring view tiles.
     *
     * @param neighbourViews the neighbour view tiles.
     */
    public abstract void setNeighbourViews(List<TileView> neighbourViews);

    /**
     * Add a neighbouring view tile.
     *
     * @param tileView the tile view
     */
    public abstract void addNeighbourView(TileView tileView);
}
