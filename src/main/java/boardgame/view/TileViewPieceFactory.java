package boardgame.view;

import boardgame.gameModel.pieces.IPiece;

public class TileViewPieceFactory {

    public static HexagonTileViewPiece createViewTilePiece(double x, double y, double radius, IPiece piece) {
        return new HexagonTileViewPiece(x, y, radius, piece);
    }
}
