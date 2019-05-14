package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Priest extends Human {

    private int moveSpeed = 2;
    private final StringProperty pieceName = new SimpleStringProperty("Priest");

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

    public void basicAttack(){}

    public void specialAbility(IGameManager gm){
        System.out.println("Healing!");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.SUPPORT;
    }

    @Override
    public StringProperty getPieceName() {
        return pieceName;
    }
}
