package boardgame.view;

/*

 */

import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Location;

public class HexagonTileViewPiece extends HexagonTileView {

    private IPiece iPiece;

    HexagonTileViewPiece(double x, double y, double radius, IPiece piece) {
        super();
        this.iPiece = piece;
        super.drawTile(x, y, radius);
    }

    public IPiece getiPiece() {
        return iPiece;
    }

    public void setiPiece(IPiece iPiece) {
        this.iPiece = iPiece;
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
