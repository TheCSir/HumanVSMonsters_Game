package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medusa extends Monster {

    private int moveSpeed = 3;
    private final StringProperty pieceName = new SimpleStringProperty("Medusa");

    Medusa(Location location) {
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

    public void basicAttack(){}

    public void specialAbility(){
        System.out.println("Summoning Snakes!");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.RANGED;
    }

    @Override
    public StringProperty getPieceName() {
        return pieceName;
    }
}
