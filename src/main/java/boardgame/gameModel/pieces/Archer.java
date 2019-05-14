package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Archer extends Human {
    private final StringProperty pieceName = new SimpleStringProperty("Archer");
    //default move speed for character.
    private int moveSpeed = 2;

    Archer(Location location) {
        super(location);
    }

    public void basicAttack(){}

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
    public int getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

}
