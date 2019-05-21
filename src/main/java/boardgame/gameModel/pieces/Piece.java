package boardgame.gameModel.pieces;

import boardgame.util.Location;
import boardgame.util.Constants;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
    This piece represents a character on the map.
 */
public abstract class Piece implements IPiece {

    private boolean isShielded;
    private boolean isAbilityUsed;
    private int shieldTurn;
    private int health;
    private final ObjectProperty<Location> locationProperty;

    //Used to make piece location observable.
    public ObjectProperty<Location> locationPropertyProperty() {
        return locationProperty;
    }


    public Piece(Location location) {
        locationProperty = new SimpleObjectProperty<>(location);
        this.isShielded = false;
        this.isAbilityUsed = false;
        this.health = Constants.MININONINITIALHEALTH;
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
    public boolean getIsAbilityUsed(){
        return isAbilityUsed;
    }

    @Override
    public void setIsAbilityUsed(boolean isAbilityUsed){
        this.isAbilityUsed = isAbilityUsed;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void decreaseHealth(int health) {
        this.health -= health;
    }

    //endregion
}
