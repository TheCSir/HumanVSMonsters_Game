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

    private Map<String, Piece> nameMap = new HashMap<>();

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
        nameMap.put(w.getPieceName().get(), w);

        Archer a = new Archer(new Location(0, 0));
        hP.put(a.getPieceClass(), a);
        nameMap.put(a.getPieceName().get(), a);

        Priest p = new Priest(new Location(0, 0));
        hP.put(p.getPieceClass(), p);
        nameMap.put(p.getPieceName().get(), p);
    }


    public Piece getPrototype(String classtype) {
        return hP.get(classtype);
    }

    @Override
    public Piece getPrototypeByName(String name) {
        return nameMap.get(name);
    }
}
