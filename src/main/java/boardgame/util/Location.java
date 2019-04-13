package boardgame.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

/**
 * Simple wrapper to store x and y coordinates. This class is relied upon by many other classes. Whilst it
 * is highly coupled as many different classes access location we are less worried about the coupling as it is
 * simple wrapper class which provides a listener for a location. This allows the view to easily list to where a piece
 * is and redraw the piece when its location changes.
 */
public class Location {

    private final IntegerProperty xProperty;
    private final IntegerProperty yProperty;

    public Location(int x, int y) {
        xProperty = new SimpleIntegerProperty(this,"xProperty", x);
        yProperty = new SimpleIntegerProperty(this,"yProperty", y);
    }

    public final int getX() {
        return xProperty.get();
    }

    private void setX(int value) {
        this.xProperty.set(value);
    }

    public final int getY() {
        return yProperty.get();
    }

    private void setY(int value) {
        this.yProperty.set(value);
    }

    //Change both x & y together. Doesn't allow for setting them separately.
    public void changeLocation(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return getX() == location.getX() &&
                getY() == location.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + getX() +
                ", y=" + getY() +
                '}';
    }


}
