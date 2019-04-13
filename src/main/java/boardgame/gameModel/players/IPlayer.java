package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;

/**
 * IPlayer is the entry point for access to each Player. The IPlayer interface and implementing classes
 * are information experts for their own pieces.
 */
public interface IPlayer {
    /**
     * Gets a player ID to uniquely identify the player.
     *
     * @return integer player id
     */
    int getPlayerID();

    /**
     * Sets player integer playerId.
     *
     * @param id playerID
     */
    void setPlayerID(int id);

    /**
     * Gets player name.
     *
     * @return the player name
     */
    String getPlayerName();

    /**
     * Sets player name.
     *
     * @param name the name
     */
    void setPlayerName(String name);

    /**
     * JAVAFX Health property. Used for observing the health of a player by the view.
     *
     * @return the integer property
     */
    IntegerProperty healthProperty();

    /**
     * Sets health property.
     *
     * @param value the value
     */
    void setHealthProperty(int value);

    /**
     * Decrease health property.
     */
    void decreaseHealthProperty();

    /**
     * Gets player status. Player status could be Shield, Normal, Poisoned etc...
     *
     * @return the player status
     */
    String getPlayerStatus();

    /**
     * Sets player status.
     *
     * @param status the status
     */
    void setPlayerStatus(String status);

    /**
     * Create shield.
     */
    void createShield();

    /**
     * Gets pieces.
     *
     * @return the pieces
     */
    ObservableList<IPiece> getPieces();

    /**
     * Sets pieces.
     *
     * @param pieces the pieces
     */
    void setPieces(ObservableList<IPiece> pieces);
}
