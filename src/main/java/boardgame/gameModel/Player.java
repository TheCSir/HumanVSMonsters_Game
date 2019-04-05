package boardgame.gameModel.tiles;

import boardgame.gameModel.IPlayer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Player implements IPlayer {

    private int playerID;
    private String playerName;
    private IntegerProperty health;

    public Player(int playerID, String playerName, int _health) {
        this.playerID = playerID;
        this.playerName = playerName;
        health = new SimpleIntegerProperty(_health);
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public int getPlayerID(){
        return playerID;
    }

    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }

    @Override
    public IntegerProperty healthProperty() { return health; }

    @Override
    public void setHealthProperty(int value) { health.set(value); }

    @Override
    public void decreaseHealthProperty() {
        System.out.println("BANG!");
        int decrementedHeath = this.healthProperty().getValue() - 1;
        this.setHealthProperty(decrementedHeath);
    }
}
