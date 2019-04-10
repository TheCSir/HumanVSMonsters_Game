package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/*
    This piece represents a character on the map.
 */

public abstract class Piece implements IPiece {

    private IntegerProperty health;
    private int attackStrength;
    private int moveSpeed;

    public Piece(int moveSpeed, Location location) {
        this.moveSpeed = moveSpeed;
        locationProperty = new SimpleObjectProperty<>(location);
    }
    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    //TODO This should return how far can move instead. NO need to take parameter of direction.
    public void move(int direction) { }

    @Override
    public Location getLocation() {
        //return this.location;
        return locationProperty.get();
    }

    private ObjectProperty<Location> locationProperty;

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
}
