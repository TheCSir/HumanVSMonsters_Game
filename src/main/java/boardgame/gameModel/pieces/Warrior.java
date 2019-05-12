package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class Warrior extends Human {

    private int moveSpeed = 3;

    Warrior(Location location) {
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
        System.out.println("Bash");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.MELEE;
    }
}
