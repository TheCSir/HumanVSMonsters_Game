package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class Griffin extends Monster {

    private int moveSpeed = 4;

    Griffin(Location location) {
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
        System.out.println("Summon Hawks!");
    }


}
