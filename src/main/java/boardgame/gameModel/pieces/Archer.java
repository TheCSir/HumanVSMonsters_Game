package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Archer extends Human {
    private final StringProperty pieceName = new SimpleStringProperty("Archer");
    //default move speed for character.
    private int moveSpeed = 2;
    private int attack = 2;
    private double rangedAttackValue = 4;

    public Archer(Location location) {
        super(location);
    }

    public void specialAbility() {
        System.out.println("Ranged Attack!");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.RANGED;
    }

    @Override
    public StringProperty getPieceName() {
        return pieceName;
    }

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public int getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void accept(SpecialVisitor v) {
        v.visit(this);
    }

    public double getRangedAttackValue() {
        return rangedAttackValue;
    }


//    public SpecialCommand getSpecialAbility() {
//        //return new HealCommand();
//    }

}
