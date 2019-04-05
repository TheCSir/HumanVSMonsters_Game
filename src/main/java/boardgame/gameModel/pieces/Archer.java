package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public class Archer extends Human {
    public Archer(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}
    public void rangedAttack(){}
}
