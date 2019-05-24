package boardgame.util;

import boardgame.gameModel.pieces.PieceConstants;

import java.util.ArrayList;
import java.util.List;

public class PieceUtil {

    public static List<String> alternativeClasses(String pieceClass) {
        List<String> classes = new ArrayList<>();
        classes.add(PieceConstants.MELEE);
        classes.add(PieceConstants.RANGED);
        classes.add(PieceConstants.SUPPORT);
        List<String> c = new ArrayList<>();
        for (String aClass : classes) {
            if (aClass.equals(pieceClass)) {
                continue;
            }
            c.add(aClass);
        }
        return c;
    }
}
