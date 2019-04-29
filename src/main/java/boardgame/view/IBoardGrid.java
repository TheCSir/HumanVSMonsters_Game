package boardgame.view;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

public interface IBoardGrid {
    TileView getTargetTile();

    void setTargetTile(TileView targetTile);

    HexagonTileViewPiece getSelectedTilePiece();

    TileView getTile(Location location);

    void addPiece(IPiece piece);

    String imageURL(IPiece iPiece);

    void drawBasicGrid(List<ITile> boardTiles, double radius, Pane boardPane);

    //returns a list of tiles to add to a pane.
    List<TileView> calculateTileCoord(List<ITile> hexagonTiles, double r, double xStartOffset, double yStartOffset);

    void initialiseBoardBackGround();

    void setNeighbourTilesColor(HexagonTileViewPiece selectedTilePiece, Color color);

    void removePiece(IPiece piece);

    Pane getBoardPane();
}
