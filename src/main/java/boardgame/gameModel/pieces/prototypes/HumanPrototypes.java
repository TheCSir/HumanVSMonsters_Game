package boardgame.gameModel.pieces.prototypes;

import boardgame.gameModel.pieces.*;
import boardgame.util.Location;

import java.util.HashMap;
import java.util.Map;


/**
 * The concrete implementation for prototype pieces type HumanPlayer.
 */
public class HumanPrototypes implements PiecePrototypes {

    private static HumanPrototypes instance;

    private Map<String, Piece> hP = new HashMap<>();

    private HumanPrototypes() {
        initializeHumanPieceMap();
    }

    /**
     * Gets the instance or creates a new instance (singleton).
     *
     * @return the only instance of HumanPrototypes
     */
    public static HumanPrototypes getInstance() {
        if (instance == null) {
            instance = new HumanPrototypes();
        }

        return instance;
    }

    private void initializeHumanPieceMap() {
        Warrior w = new Warrior(new Location(0, 0));
        hP.put(w.getPieceClass(), w);

        Archer a = new Archer(new Location(0, 0));
        hP.put(a.getPieceClass(), a);

        Priest p = new Priest(new Location(0, 0));
        hP.put(p.getPieceClass(), p);
    }


    public Piece getPrototype(String classtype) {
        return hP.get(classtype);
    }
}
