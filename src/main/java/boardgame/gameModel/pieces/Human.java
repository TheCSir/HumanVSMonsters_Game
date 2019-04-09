package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public abstract class Human extends Piece implements IAttackingPiece {

    public Human(int moveSpeed, Location location) { super(moveSpeed, location); }

    public abstract void basicAttack();

}
