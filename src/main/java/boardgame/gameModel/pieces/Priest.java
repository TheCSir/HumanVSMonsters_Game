package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public class Priest extends Human {
    public Priest(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}
    public void heal(){}
}
