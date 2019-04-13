package boardgame.gameModel.pieces;

import boardgame.util.Location;

public abstract class Monster extends Piece implements IAttackingPiece {

    public Monster(int moveSpeed, Location location) { super(moveSpeed, location); }

    public abstract void basicAttack();

    public abstract void specialAbility();

    public abstract void createShield();
}
