package boardgame.gameModel.pieces;

import boardgame.util.Location;

/**
 * Abstract Piece Factory to create pieces depending on class.
 */
public abstract class AbstractPieceFactory {
    public abstract IPiece getPiece(String pieceClass, Location location);

    public abstract IPiece getMelee(Location location);

    public abstract IPiece getRanged(Location location);

    public abstract IPiece getSupport(Location location);
}
