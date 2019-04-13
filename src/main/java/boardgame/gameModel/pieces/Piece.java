package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
    This piece represents a character on the map.
 */
public abstract class Piece implements IPiece {

    private IntegerProperty health;
    private int attackStrength;
    private int moveSpeed;
    private boolean isShielded;
    private int shieldTurn;

    public Piece(int moveSpeed, Location location) {
        this.moveSpeed = moveSpeed;
        locationProperty = new SimpleObjectProperty<>(location);
        this.isShielded = false;
    }

    @Override
    public int getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void move(int direction) { }

    @Override
    public Location getLocation() {
        //return this.location;
        return locationProperty.get();
    }

    private ObjectProperty<Location> locationProperty;

    @Override
    public Location getLocationProperty() {
        return locationProperty.get();
    }

    //Used to make piece location observable.
    public ObjectProperty<Location> locationPropertyProperty() {
        return locationProperty;
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
    public void checkShieldTurn(int turnNumber){
        if(turnNumber >= this.shieldTurn + 2)
            this.setIsShielded(false);
    }

    //endregion
}
