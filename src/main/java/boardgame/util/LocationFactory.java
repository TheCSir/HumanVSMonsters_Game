package boardgame.util;

import java.util.HashMap;
import java.util.Map;

public class LocationFactory {

    private static Map<String, Location> locationCache = new HashMap<>();

    //Use of flyweight pattern. Prior to flyweight pattern 1302 instances of location were being created for a 10 x 10
    //grid. After flyweight pattern only 142 instances of location were created.
    public static Location createLocation(int x, int y) {
        String locationCacheKey = x + " " + y;

        //This line is just to show the different
        //System.out.println(locationCache.size());
        return locationCache.computeIfAbsent(locationCacheKey, k -> new Location(x, y));
    }

    //Uncomment this code to see difference between flyweight and no flyweight.
//    private static int locationNumber = 0;
//    public static Location createLocation(int x, int y) {
//        System.out.println(++locationNumber);
//        return new Location(x, y);
//    }
}
