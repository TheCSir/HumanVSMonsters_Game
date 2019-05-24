package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Minion extends Monster {

    private int moveSpeed = 2;
    private double attack = 1;
    private StringProperty pieceName;

    public Minion(Location location, String name) {
        super(location);
        pieceName = new SimpleStringProperty(name);
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
    @Override
    public void accept(SpecialVisitor v) {

    }

    @Override
    public String getSpecialAbilityDescription() {
        return "Summon Snakes";
    }
}