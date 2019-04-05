package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public abstract class Monster extends Piece implements IAttackingPiece {

    public Monster(int moveSpeed, Location location) { super(moveSpeed, location); }

    public abstract void basicAttack();
}
