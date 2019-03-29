package boardgame.gameModel.pieces;

public abstract class Human extends Piece implements IPiece {
    public Human(int _health, int moveSpeed) {
        super(_health, moveSpeed);
    }

    public abstract void basicAttack();


}
