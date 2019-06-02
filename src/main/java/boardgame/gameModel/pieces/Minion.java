package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Constants;
import boardgame.util.Location;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Minion extends Monster {

    private int moveSpeed = 2;
    private double attack = 1;

    private StringProperty pieceName = new SimpleStringProperty("Minion");
    private SimpleDoubleProperty health;

    public Minion(Location location) {
        super(location);
        health = new SimpleDoubleProperty(Constants.INITIALMINIONHEALTH);
    }

    @Override
    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    @Override
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }


    public double getAttack(){ return attack; }

    @Override
    public String getPieceClass() {
        return PieceConstants.MINION;
    }

    @Override
    public StringProperty getPieceName() {
        return pieceName;
    }

    //Minion does not have it's own special ability.
    @Override
    public void accept(SpecialVisitor v) {

    }

    @Override
    public String getSpecialAbilityDescription() {
        return "Summon Snakes";
    }

    //In the minion damage is removed directly from the minion instead. 0 is returned
    // as usually damage applies to the player directly. However, in this case health is
    // reduced on the minion instead.
    @Override
    public double calculateDamage(double damage) {
        decreaseHealth(damage);
        return 0;
    }

    public void setPieceName(String pieceName) {
        this.pieceName.set(pieceName);
    }

    public double getHealth() {
        return health.get();
    }

    public void setHealth(double value) {
        health.set(value);
    }

    public void decreaseHealth(double damage) {
        health.set(health.get() - damage);
    }

    public SimpleDoubleProperty healthProperty() {
        return health;
    }
}