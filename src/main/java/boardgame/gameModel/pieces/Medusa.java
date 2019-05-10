package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class Medusa extends Monster {

    private int moveSpeed = 3;

    Medusa(Location location) {
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
        System.out.println("Summoning Snakes!");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.RANGED;
    }
}
