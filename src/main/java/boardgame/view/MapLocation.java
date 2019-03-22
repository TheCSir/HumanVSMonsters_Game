package boardgame.view;

public class Location {

    private int xGridValue;
    private int yGridValue;

    public Location(int xGridValue, int yGridValue) {
        this.xGridValue = xGridValue;
        this.yGridValue = yGridValue;
    }

    @Override
    public String toString() {
        return "Location{" +
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
}
