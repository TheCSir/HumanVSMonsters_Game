package boardgame.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Util {
    public ArrayList<Location> convertJsonToObjectArray(String json) {
        @SuppressWarnings("unchecked")

        ArrayList<Location> data = new Gson().fromJson(json, new TypeToken<ArrayList<Location>>(){}.getType());

        return data;
    }

    public String readFile(String path)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
