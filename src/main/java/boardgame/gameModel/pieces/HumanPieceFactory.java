package boardgame.gameModel.pieces;

import boardgame.util.Location;

import static boardgame.gameModel.pieces.PieceConstants.*;

public class HumanPieceFactory extends AbstractPieceFactory {
    @Override
    public IPiece getPiece(String pieceClass, Location location, int moveSpeed, int attackPower) {
        switch (pieceClass) {
            case MELEE:
                return new Warrior(moveSpeed, location);
            case RANGED:
                return new Archer(moveSpeed, location);
            case SUPPORT:
                return new Priest(moveSpeed, location);
        }
        return null;
    }
}
