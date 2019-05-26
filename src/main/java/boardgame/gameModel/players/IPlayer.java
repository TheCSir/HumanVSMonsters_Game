package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;

/**
 * IPlayer is the entry point for access to each Player. The IPlayer interface and implementing classes
 * are information experts for their own pieces.
 */
public interface IPlayer extends IPlayerComponent{
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
    DoubleProperty healthProperty();

    /**
     * Sets health property.
     *
     * @param value the value
     */
    void setHealthProperty(double value);

    /**
     * Decrease health property.
     *
     * @param piece the piece
     */
    void decreaseHealthProperty(IPiece piece);

    double calculateDamage(IPiece piece);

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

    IPiece getPiece(IPiece piece);

    String playerType();

    /**
     * Increase health property.
     */
    void increaseHealthProperty(double healingValue);

    /**
     * Gets isAbilityUsed property.
     *
     * @return the isAbilityUsed property.
     */
    boolean getIsAbilityUsed();

    /**
     * Sets isAbilityUsed property.
     *
     * @param isAbilityUsed the isAbilityUsed property.
     */
    void setIsAbilityUsed(boolean isAbilityUsed);


}
