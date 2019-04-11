package boardgame.gameModel;

import boardgame.gameModel.players.IPlayer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class Turn {
    private IntegerProperty turnNumber;
    private ObjectProperty<IPlayer> activePlayerProperty;

    Turn(){
        this.turnNumber = new SimpleIntegerProperty();
        this.activePlayerProperty = new SimpleObjectProperty<>();
    }

    public IntegerProperty turnNumberProperty() { return turnNumber; }

    public void setTurnNumberProperty(int turnNumber) { this.turnNumber.set(turnNumber); }

    public int getTurnNumber() { return turnNumber.getValue(); }

    public ObjectProperty<IPlayer> getActivePlayerProperty(){ return this.activePlayerProperty; }

    public IPlayer getActivePlayer(){
        return this.activePlayerProperty.get();
    }

    public void setActivePlayer(IPlayer activePlayer) {
        this.activePlayerProperty.set(activePlayer);
    }

    public void initialiseTurns(List<IPlayer> players){
        int firstTurn = 1;
        this.setTurnNumberProperty(firstTurn);

        int firstPlayerIndex = this.getTurnNumber() % players.size();

        setActivePlayer(players.get(firstPlayerIndex));
    }

    public void nextTurn(List<IPlayer> players){
        int nextTurn = this.getTurnNumber() + 1;
        this.setTurnNumberProperty(nextTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = this.getTurnNumber() % players.size();

        setActivePlayer(players.get(nextPlayerIndex));
    }
}
