package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Minion extends Monster {

    private int moveSpeed = 2;
    private double attack = 1;

    private StringProperty pieceName = new SimpleStringProperty("Minion");

    public Minion(Location location) {
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


    public double getAttack(){ return attack; }

    public void specialAbility(){ }

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

    public void setPieceName(String pieceName) {
        this.pieceName.set(pieceName);
    }
}