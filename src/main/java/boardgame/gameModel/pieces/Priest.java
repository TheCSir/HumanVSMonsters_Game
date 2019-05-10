package boardgame.gameModel.pieces;

import boardgame.util.Location;


public class Priest extends Human {

    private int moveSpeed = 2;

    Priest(Location location) {
        super(location);
    }

    @Override
    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    @Override
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void basicAttack(){}

    public void specialAbility(){
        System.out.println("Healing!");
    }
}
