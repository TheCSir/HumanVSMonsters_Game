package boardgame.gameModel.pieces;


import boardgame.util.Location;

public class Minotaur extends Monster {

    private int moveSpeed = 3;

    Minotaur(Location location) {
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

    @Override
    public void specialAbility(){
        System.out.println("Summoning Bulls!");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.MELEE;
    }
}
