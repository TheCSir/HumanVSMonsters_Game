package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.ObjectProperty;

/**
 * The interface Piece. Provides the contract for new pieces on the board. All pieces must at least implement
 * these methods. The piece class is solely responsible for providing a piece's attributes and behaviours.
 * The Piece classes are well abstracted. They use polymorphism as demonstrated by the specialAbility (still to
 * be implemented.) The pieces are observed by the view through the Location Property.
 */
public interface IPiece {

    String classType = PieceConstants.SUPPORT;

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

    void specialAbility();

    String getPieceClass();
}
