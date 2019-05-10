package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class Archer extends Human {

    //default move speed for character.
    private int moveSpeed = 2;

    Archer(Location location) {
        super(location);
    }

    public void basicAttack(){}

    public void specialAbility() {
        System.out.println("Ranged Attack!");
    }

    @Override
    public int getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }


}
