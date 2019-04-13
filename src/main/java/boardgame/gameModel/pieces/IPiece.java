package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.ObjectProperty;

/**
 * The interface Piece. Provides the contract for new picees on the board. All pieces must at least implement
 * these methods.
 */
public interface IPiece {

    /**
     * Gets move speed.
     *
     * @return the move speed
     */
    int getMoveSpeed();

    /**
     * Sets move speed.
     *
     * @param moveSpeed the move speed
     */
    void setMoveSpeed(int moveSpeed);

    /**
     * Move.
     *
     * @param direction the direction
     */
    void move(int direction);

    /**
     * Gets location.
     *
     * @return the location
     */
    Location getLocation();

    /**
     * Sets location.
     *
     * @param location the location
     */
    void setLocation(Location location);

    /**
     * Gets location property.
     *
     * @return the location property
     */
    Location getLocationProperty();

    /**
     * Location property property object property.
     *
     * @return the object property
     */
    ObjectProperty locationPropertyProperty();

    /**
     * Gets isShielded property.
     *
     * @return the isShielded property.
     */
    boolean getIsShielded();

    /**
     * Sets isShielded property.
     *
     * @param isShielded the isShielded property.
     */
    void setIsShielded(boolean isShielded);

    /**
     * Creates a shield.
     */
    void createShield(int turnNumber);

    /**
     * Checks shield turn.
     */
    void checkShieldTurn(int turnNumber);
}
