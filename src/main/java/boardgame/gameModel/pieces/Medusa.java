package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medusa extends Monster {

    private int moveSpeed = 3;
    private int attack = 2;
    private final StringProperty pieceName = new SimpleStringProperty("Medusa");


    public Medusa(Location location) {
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
    public void accept(SpecialVisitor v) {
        v.visit(this);
    }

    @Override
    public String getSpecialAbilityDescription() {
        return "Summon Snakes";
    }
}
