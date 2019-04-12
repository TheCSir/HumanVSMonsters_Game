package boardgame.view;

import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.scene.shape.Polygon;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class TileView extends Polygon {


    public abstract double getInitalY();

    public abstract void drawTile(double x, double y, double radius);

    public abstract void setImagePattern(String imageUrl) throws FileNotFoundException;

    public abstract double getXPosition();

    public abstract double getYPosition();

    public abstract double getInitialX();

    public abstract double getInitialY();

    public abstract void setInitialX(double x);

    public abstract void setInitalY(double y);

    public abstract Location getLocation();

    public abstract void setLocation(Location gridPosition);

    public abstract void addNeighbour(ITile tile);

    public abstract List<ITile> getNeighbours();

    public abstract ITile getModelTile();

    public abstract void setModelTile(ITile modelTile);

    public abstract List<HexagonTileView> getNeighbourViews();

    public abstract void setNeighbourViews(List<HexagonTileView> neighbourViews);

    public abstract void addNeighbourView(HexagonTileView tileView);
}
