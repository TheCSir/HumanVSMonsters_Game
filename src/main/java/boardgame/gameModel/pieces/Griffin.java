package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Constants;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

public class Griffin extends Monster {

    private int moveSpeed = 4;
    private final StringProperty pieceName = new SimpleStringProperty("Griffin");

    Griffin(Location location) {
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
        Random rand = new Random();

        int x = rand.nextInt(Constants.CUSTOMBOARDROWS);
        int y = rand.nextInt(Constants.CUSTOMBOARDCOLUMNS);

        Location newLocation = new Location(x,y);
        IPiece newPiece = new Minion(newLocation,"Hawks");
        gm.addPiece(newPiece);
        System.out.println("Summoning Hawks!");
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
