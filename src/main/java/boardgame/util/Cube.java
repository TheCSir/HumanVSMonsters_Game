package boardgame.util;

/**
 * A Cube object. The cube class purpose is to convert Hex coordinates into Cube coordinates.
 * This is because it is easier to perform calculations such as distance between two hexes if
 * changing to a Cube first.
 */
public class Cube {
    /**
     * The X.
     */
    private int x;
    /**
     * The Y.
     */
    private int y;
    /**
     * The Z.
     */
    private int z;

    /**
     * @param x the x
     * @param y the y
     * @param z the z
     */
    Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
