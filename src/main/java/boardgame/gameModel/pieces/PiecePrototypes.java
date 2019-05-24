package boardgame.gameModel.pieces;

import boardgame.util.Location;

import java.util.HashMap;
import java.util.Map;

public class PiecePrototypes {

    private static PiecePrototypes instance;
    private Map<String, Piece> hP = new HashMap<>();
    private Map<String, Piece> mP = new HashMap<>();


    private PiecePrototypes() {
        intializeHumanPieceMap();
        inintializeMonsterPieceMap();
    }

    public static PiecePrototypes getInstance() {

        if (instance == null) {
            instance = new PiecePrototypes();
        }
        return instance;
    }

    public Piece getHumanPrototype(String classtype) {
        return hP.get(classtype);
    }

    public Piece getMonsterPrototype(String classType) {
        return mP.get(classType);
    }

    public void intializeHumanPieceMap() {

        Warrior w = new Warrior(new Location(0, 0));
        hP.put(w.getPieceClass(), w);

        Archer a = new Archer(new Location(0, 0));
        hP.put(a.getPieceClass(), a);

        Priest p = new Priest(new Location(0, 0));
        hP.put(p.getPieceClass(), p);
    }

    private void inintializeMonsterPieceMap() {
        Medusa medusa = new Medusa(new Location(0, 0));
        mP.put(medusa.getPieceClass(), medusa);

        Minotaur minotaur = new Minotaur(new Location(0, 0));
        mP.put(minotaur.getPieceClass(), minotaur);

        Griffin griffin = new Griffin(new Location(0, 0));
        mP.put(griffin.getPieceClass(), griffin);

    }
}
