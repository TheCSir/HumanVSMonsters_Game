package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public abstract class Human extends Piece implements IPiece {
    public Human(int _health, int moveSpeed, Location location) {
        super(_health, moveSpeed, location);
    }

    public abstract void basicAttack();


}
