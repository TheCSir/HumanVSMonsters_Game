package boardgame.gameModel.pieces;


import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Minotaur extends Monster {

    private int moveSpeed = 3;
    private final StringProperty pieceName = new SimpleStringProperty("Minotaur");

    Minotaur(Location location) {
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

    @Override
    public void specialAbility(){
        System.out.println("Summoning Bulls!");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.MELEE;
    }

    @Override
    public StringProperty getPieceName() {
        return pieceName;
    }
}
