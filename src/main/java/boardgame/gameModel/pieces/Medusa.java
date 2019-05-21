package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Constants;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

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

    public void specialAbility(IGameManager gm){

        Random rand = new Random();

        int x = rand.nextInt(Constants.CUSTOMBOARDROWS);
        int y = rand.nextInt(Constants.CUSTOMBOARDCOLUMNS);

        Location newLocation = new Location(x,y);
        IPiece newPiece = new Minion(newLocation,"Snake");
        gm.addPiece(newPiece);
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
