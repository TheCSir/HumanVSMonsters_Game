package boardgame.gameModel.pieces;

import boardgame.util.Location;

import static boardgame.gameModel.pieces.PieceConstants.*;

public class HumanPieceFactory extends AbstractPieceFactory {
    @Override
    public IPiece getPiece(String pieceClass, Location location) {
        switch (pieceClass) {
            case MELEE:
                return new Warrior(location);
            case RANGED:
                return new Archer(location);
            case SUPPORT:
                return new Priest(location);
        }
        return null;
    }
}
