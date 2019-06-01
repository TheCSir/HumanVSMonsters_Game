package boardgame.view;

import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Location;

/**
 * Represents a Piece drawn on the board. Uses composition as requires a reference to the piece from the model that
 * this view class represents. This extends the HexagonTileView (which extends Polygon). The rationale for this is
 * that a view tile and a view piece are the same except one holds a IPiece and one holds a ITile. The actual drawing
 * is the same. This reduces code duplication and reduces the chance of errors. With the way we have designed it it also
 * significantly reduces code complexity for drawing the tiles and pieces.
 */
public class HexagonTileViewPiece extends HexagonTileView {

    private IPiece iPiece;


    /**
     * Instantiates a new Hexagon tile view piece.
     *
     * @param x      tile x co-ordinate
     * @param y      tile y-co-ordinate
     * @param radius the radius of the tile
     * @param piece  the reference to the piece from the model
     */
    HexagonTileViewPiece(double x, double y, double radius, IPiece piece) {
        super();
        this.iPiece = piece;
        super.drawTile(x, y, radius);
    }

    public IPiece getiPiece() {
        return iPiece;
    }

    @Override
    public void setLocation(Location gridPosition) {
        iPiece.setLocation(gridPosition);
    }

    @Override
    public Location getLocation() {
        return iPiece.getLocation();
    }

}
