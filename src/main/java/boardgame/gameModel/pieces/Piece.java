package boardgame.gameModel.pieces;

import boardgame.util.Constants;
import boardgame.util.Location;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
    This piece represents a character on the map.
 */
public abstract class Piece implements IPiece, Cloneable {

    private boolean isShielded;
    private int shieldTurn;
    private ObjectProperty<Location> locationProperty;

    public void setLocationProperty(Location locationProperty) {
        this.locationProperty = new SimpleObjectProperty<>(locationProperty);
    }
    private int health;

    //Used to make piece location observable.
    public ObjectProperty<Location> locationPropertyProperty() {
        return locationProperty;
    }

    public Piece(Location location) {
        locationProperty = new SimpleObjectProperty<>(location);
        this.isShielded = false;
        this.health = Constants.INITIALMINIONHEALTH;
    }

    @Override
    public abstract int getMoveSpeed();

    @Override
    public abstract void setMoveSpeed(int moveSpeed);


    @Override
    public Location getLocation() {
        //return this.location;
        return locationProperty.get();
    }

    @Override
    public Location getLocationProperty() {

        return locationProperty.get();
    }

    //Doesn't allow a new location to be passed. Just take the coordinates to avoid affecting the observable.
    @Override
    public void setLocation(Location location) {
        //this.location.changeLocation(location.getX(), location.getY());
        locationProperty.setValue(location);
    }

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
    public void createShield(int turnNumber) {
        this.setIsShielded(true);

        // Set which turn shield has been activated
        this.shieldTurn = turnNumber;
    }

    // Checks if shield is activated and expires if shield lasted for more than one turn
    public void checkShieldTurn(int turnNumber) {
        if (turnNumber >= this.shieldTurn + 2)
            this.setIsShielded(false);
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void decreaseHealth(int health) {
        this.health -= health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }

    //endregion
}
