package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
    This piece represents a character on the map.
 */


public abstract class Piece implements IPiece {

    private IntegerProperty health;
    private int _health;
    private int attackStrength;
    private int moveSpeed;
    private Location location;

    public void move(int direction){}

    public IntegerProperty healthProperty() {
        if (health==null) {
            return new SimpleIntegerProperty(_health);
        }
        return health;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    public Piece(int _health, int moveSpeed) {
        this._health = _health;
        this.moveSpeed = moveSpeed;
        this.location = location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
