package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Priest extends Human {

    private int moveSpeed = 2;
    private int attack = 1;
    private final StringProperty pieceName = new SimpleStringProperty("Priest");
    private double healValue = 3;


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

    @Override
    public void specialAbility() {

    }


    @Override
    public String getPieceClass() {
        return PieceConstants.SUPPORT;
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

    public double getHealValue() {
        return healValue;
    }

    public void setHealValue(double healValue) {
        this.healValue = healValue;
    }
}
