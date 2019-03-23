package boardgame.view;

import java.util.Objects;

public class MapLocation {

    private int xGridValue;
    private int yGridValue;


    public MapLocation(int xGridValue, int yGridValue) {
        this.xGridValue = xGridValue;
        this.yGridValue = yGridValue;

    }

    @Override
    public String toString() {
        return "MapLocation{" +
                "xGridValue=" + xGridValue +
                ", yGridValue=" + yGridValue +
                '}';
    }

    public int getxGridValue() {
        return xGridValue;
    }

    public void setxGridValue(int xGridValue) {
        this.xGridValue = xGridValue;
    }

    public int getyGridValue() {
        return yGridValue;
    }

    public void setyGridValue(int yGridValue) {
        this.yGridValue = yGridValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapLocation that = (MapLocation) o;
        return getxGridValue() == that.getxGridValue() &&
                getyGridValue() == that.getyGridValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getxGridValue(), getyGridValue());
    }
}
