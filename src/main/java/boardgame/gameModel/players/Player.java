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
    private boolean isShielded;
    //endregion

    public Player(int playerID, String playerName, double _health, ObservableList<IPiece> pieces) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.health = new SimpleDoubleProperty(_health);
        this.pieces = pieces;
        this.isShielded = false;
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
    public void decreaseHealthProperty() {
        // Calculate taken damage value
        double damageValue;
        if(this.getIsShielded())
            damageValue = 0.5;
        else
            damageValue = 1;

        double decrementedHeath = this.healthProperty().getValue() - damageValue;
        this.setHealthProperty(decrementedHeath);
    }

    //endregion

    //region Defense methods

    @Override
    public boolean getIsShielded() {
        return isShielded;
    }

    @Override
    public void setIsShielded(boolean isShielded) {
        this.isShielded = isShielded;
    }

    @Override
    public void createShield() {
        this.setIsShielded(true);
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
