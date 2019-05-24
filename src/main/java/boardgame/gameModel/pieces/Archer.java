package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Archer is a ranged class. It implements two methods not present in other pieces. It has a ranged attack value
 * and a ranged attack distance which allow its Special Ability Long Range Shot to work. These are called by the Special
 * visitor class as part of the Visitor Pattern.
 */
public class Archer extends Human {
    private final StringProperty pieceName = new SimpleStringProperty("Archer");
    //default move speed for character.
    private int moveSpeed = 2;
    private int attack = 2;
    private double rangedAttackValue = 3;
    private int rangedDistance = 3;

    public Archer(Location location) {
        super(location);
    }

    public void specialAbility() {
        System.out.println("Long Range Shot");
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

    @Override
    public String getSpecialAbilityDescription() {
        return "Long Range Shot";
    }


    /**
     * @return a double representing the attack value of the Archer's special attack.
     */
    public double getRangedAttackValue() {
        return rangedAttackValue;
    }

    /**
     * @return an int representing how many tiles an archer can shoot.
     */
    public int getRangedDistance() {
        return rangedDistance;
    }

}
