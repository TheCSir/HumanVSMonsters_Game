package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public abstract class Monster extends Piece implements IAttackingPiece {
    public Monster(int _health, int moveSpeed, Location location) {
        super(_health, moveSpeed, location);
    }

    public abstract void basicAttack();
}
