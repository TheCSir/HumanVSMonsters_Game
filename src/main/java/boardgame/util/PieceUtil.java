package boardgame.util;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.AbstractPieceFactory;
import boardgame.gameModel.pieces.FactoryProducer;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.gameModel.players.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class PieceUtil {

    public static String[] getSwapAlternatives(String pieceClass, IPlayer currentPlayer) {
        List<String> alternateClasses = alternativeClasses(pieceClass);
        return swapAlternateClassNames(alternateClasses, currentPlayer);
    }

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

    private static String[] swapAlternateClassNames(List<String> alternateClasses, IPlayer currentPlayer) {
        AbstractPieceFactory a = FactoryProducer.getFactory(currentPlayer.playerType());
        assert a != null;
        IPiece alternative1 = a.getPiece(alternateClasses.get(0), new Location(0, 0));
        IPiece alternative2 = a.getPiece(alternateClasses.get(1), new Location(0, 0));
        return new String[]{alternative1.getPieceName().get(), alternative2.getPieceName().get()};
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
