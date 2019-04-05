package boardgame.gameModel;

import boardgame.gameModel.pieces.IPiece;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public abstract class Player implements IPlayer {

    private int playerID;
    private String playerName;
    private IntegerProperty health;
    private List<IPiece> pieces;

    public Player(int playerID, String playerName, int _health, List<IPiece> pieces) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.health = new SimpleIntegerProperty(_health);
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

    @Override
    public List<IPiece> getPieces() {
        return pieces;
    }

    @Override
    public void setPieces(List<IPiece> pieces) {
        this.pieces = pieces;
    }
}
