package boardgame.util;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
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

    public static List<IPiece> getEnemyPieces(IGameManager gm) {
        List<IPiece> ownPieces = gm.getActivePlayer().getPieces();
        List<IPiece> allpieces = gm.getAllPieces();
        List<IPiece> enemyPieces = new ArrayList<>();
        for (IPiece piece : allpieces) {
            if (!ownPieces.contains(piece)) {
                enemyPieces.add(piece);
            }
        }
        return enemyPieces;
    }
}
