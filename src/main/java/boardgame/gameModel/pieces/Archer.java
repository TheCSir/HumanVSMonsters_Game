package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class Archer extends Human {
    Archer(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    public void specialAbility() {
        System.out.println("Ranged Attack!");
    }
}
