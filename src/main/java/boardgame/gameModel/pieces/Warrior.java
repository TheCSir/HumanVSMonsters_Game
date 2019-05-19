package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Warrior extends Human {

    private int moveSpeed = 3;
    private double attack = 2;
    private final StringProperty pieceName = new SimpleStringProperty("Warrior");
    private double specialAttackMultiplier = 2;


    public Warrior(Location location) {
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


    public void specialAbility(){
        System.out.println("Heavy Smash");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.MELEE;
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
    public void accept(SpecialVisitor v) {
        v.visit(this);
    }

    @Override
    public String getSpecialAbilityDescription() {
        return "Heavy Smash";
    }

    public double getSpecialAttackMultiplier() {
        return specialAttackMultiplier;
    }

    public void setSpecialAttackMultiplier(double specialAttackMultiplier) {
        this.specialAttackMultiplier = specialAttackMultiplier;
    }

}
