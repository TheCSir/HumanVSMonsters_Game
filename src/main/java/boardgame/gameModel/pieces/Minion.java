package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Minion extends Monster {

    private int moveSpeed = 3;
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

    public void specialAbility(){ }

    @Override
    public String getPieceClass() {
        return PieceConstants.MINION;
    }

    @Override
    public StringProperty getPieceName() {
        return pieceName;
    }
}
