package boardgame.gameModel.pieces;

import boardgame.util.Location;

import static boardgame.gameModel.pieces.PieceConstants.*;

public class MonsterPieceFactory extends AbstractPieceFactory {

    @Override
    public IPiece getPiece(String pieceClass, Location location) {
        switch (pieceClass) {
            case MELEE:
                return new Minotaur(location);
            case RANGED:
                return new Medusa(location);
            case SUPPORT:
                return new Griffin(location);
        }
        return null;
    }

    @Override
    public IPiece getMelee(Location location) {
        return new Minotaur(location);
    }

    @Override
    public IPiece getRanged(Location location) {
        return new Medusa(location);
    }

    @Override
    public IPiece getSupport(Location location) {
        return new Griffin(location);
    }
}

