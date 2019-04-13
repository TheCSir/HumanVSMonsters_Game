package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Constants;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public abstract class Player implements IPlayer {

    private int playerID;
    private String playerName;
    private IntegerProperty health;
    private String playerStatus = Constants.IDEALSTATUS;
    private ObservableList<IPiece> pieces;

    public Player(int playerID, String playerName, int _health, String playerStatus, ObservableList<IPiece> pieces) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.health = new SimpleIntegerProperty(_health);
        this.playerStatus = playerStatus;
        this.pieces = pieces;
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

    //code add for defense
    @Override
    public String getPlayerStatus() {
        return playerStatus;
    }

    @Override
    public void setPlayerStatus(String status) {
        this.playerStatus = status;
    }

    @Override
    public void createShield() {
        System.out.println("Shield created");
        this.setPlayerStatus("Shielded");
        System.out.println(this.playerStatus);
    }

    //end

    @Override
    public ObservableList<IPiece> getPieces() {
        return pieces;
    }

    @Override
    public void setPieces(ObservableList<IPiece> pieces) {
        this.pieces = pieces;
    }
}
