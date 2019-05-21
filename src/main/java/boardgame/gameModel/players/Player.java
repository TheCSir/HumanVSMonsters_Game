package boardgame.gameModel.players;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Constants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import static org.valid4j.Assertive.require;

public abstract class Player implements IPlayer {

    //region private Player properties

    private int playerID;
    private String playerName;
    private String playerStatus = Constants.IDEALSTATUS;
    private final DoubleProperty health;
    private ObservableList<IPiece> pieces;

    //endregion

    public Player(int playerID, String playerName, double _health, ObservableList<IPiece> pieces, IGameManager gm) {

        //Preconditions. This uses the valid4j library.
        require(playerID > 0);
        require(playerName != null);
        require(_health >= 0);
        require(pieces != null);

        this.playerID = playerID;
        this.playerName = playerName;
        this.health = new SimpleDoubleProperty(_health);
        this.pieces = pieces;

        pieces.addListener((ListChangeListener<IPiece>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (IPiece piece : c.getAddedSubList()) {
                        gm.addPiece(piece);
                    }
                } else if (c.wasRemoved()) {
                    c.getRemoved();
                    for (IPiece piece : c.getRemoved()) {
                        gm.removePiece(piece);
                    }
                }
            }
        });
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
    public DoubleProperty healthProperty() {
        return health;
    }

    @Override
    public void setHealthProperty(double value) {
        health.set(value);
    }

    @Override
    public void decreaseHealthProperty(IPiece piece) {

        // Calculate taken damage value
        double damageValue = calculateDamage(piece);

        //Decrease health
        this.setHealthProperty(this.healthProperty().getValue() - damageValue);
    }

    @Override
    public double calculateDamage(IPiece piece) {
        double attackValue = piece.getAttack();

        //Shield halves damage.
        if (piece.getIsShielded())
            return attackValue / 2;
        else
            return attackValue;
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

    @Override
    public void increaseHealthProperty(double healingValue) {

        //Increase health
        this.setHealthProperty(this.healthProperty().getValue() + healingValue);
    }
}
