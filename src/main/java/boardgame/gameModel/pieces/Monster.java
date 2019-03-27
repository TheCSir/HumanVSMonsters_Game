package boardgame.gameModel.pieces;

public abstract class Monster extends Piece implements IAttackingPiece {
    public Monster(int _health, int moveSpeed) {
        super(_health, moveSpeed);
    }

    public abstract void basicAttack();
}
