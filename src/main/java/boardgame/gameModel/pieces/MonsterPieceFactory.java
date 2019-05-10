package boardgame.gameModel.pieces;

import boardgame.util.Location;

import static boardgame.gameModel.pieces.PieceConstants.*;

public class MonsterPieceFactory extends AbstractPieceFactory {

    @Override
    public IPiece getPiece(String pieceClass, Location location, int moveSpeed, int attackPower) {
        switch (pieceClass) {
            case MELEE:
                return new Minotaur(moveSpeed, location);
            case RANGED:
                return new Medusa(moveSpeed, location);
            case SUPPORT:
                return new Griffin(moveSpeed, location);
        }
        return null;
    }
}

