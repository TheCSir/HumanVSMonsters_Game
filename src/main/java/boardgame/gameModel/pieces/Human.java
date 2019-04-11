package boardgame.gameModel.pieces;

import boardgame.util.Location;

public abstract class Human extends Piece implements IAttackingPiece {

    public Human(int moveSpeed, Location location) { super(moveSpeed, location); }

    public abstract void basicAttack();

    public abstract void specialAbility();

}
