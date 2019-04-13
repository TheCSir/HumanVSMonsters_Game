package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public abstract class Player implements IPlayer {

    //region private Player properties

    private int playerID;
    private String playerName;
    private DoubleProperty health;
    private ObservableList<IPiece> pieces;

    //endregion

    public Player(int playerID, String playerName, double _health, ObservableList<IPiece> pieces) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.health = new SimpleDoubleProperty(_health);
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

    //region Health methods

    @Override
    public DoubleProperty healthProperty() { return health; }

    @Override
    public void setHealthProperty(double value) { health.set(value); }

    @Override
    public void decreaseHealthProperty(IPiece piece) {
        // Calculate taken damage value
        double damageValue;
        if(piece.getIsShielded())
            damageValue = 0.5;
        else
            damageValue = 1;

        double decrementedHeath = this.healthProperty().getValue() - damageValue;
        this.setHealthProperty(decrementedHeath);
    }

    //endregion

    @Override
    public ObservableList<IPiece> getPieces() {
        return pieces;
    }

    @Override
    public void setPieces(ObservableList<IPiece> pieces) {
        this.pieces = pieces;
    }
}
