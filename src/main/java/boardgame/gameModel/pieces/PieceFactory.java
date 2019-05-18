package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class PieceFactory {

    public static Piece createPiece(String piece, int moveSpeed, Location location) {
        if (piece.equals(Warrior.class.getName())) {
            return new Warrior(moveSpeed, location, "attack");
        }else if (piece.equals(Priest.class.getName())) {
            return new Priest(moveSpeed, location, "heal");
        }else if (piece.equals(Archer.class.getName())) {
            return new Archer(moveSpeed, location, "attack");
        }else if (piece.equals(Medusa.class.getName())) {
            return new Medusa(moveSpeed, location, "summon");
        }else if (piece.equals(Griffin.class.getName())) {
            return new Griffin(moveSpeed, location, "summon");
        }else if (piece.equals(Minotaur.class.getName())) {
            return new Minotaur(moveSpeed, location, "summon");
        }
        return null;
    }
}
