package boardgame.util;

public class LocationFactory {

    public static Location createLocation(int x, int y) {
        return new Location(x, y);
    }
}
