package boardgame.gameModel.pieces.prototypes;

import boardgame.gameModel.pieces.*;
import boardgame.util.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of Monster Prototypes for the Monster Player. This class holds a Monster of each
 * type that can be cloned.
 */
public class MonsterPrototypes implements PiecePrototypes {


    private static MonsterPrototypes instance;
    private Map<String, Piece> mP = new HashMap<>();

    private Map<String, Piece> nameMap = new HashMap<>();

    private MonsterPrototypes() {
        initializeMonsterPieceMap();
    }

    public static MonsterPrototypes getInstance() {
        if (instance == null) {
            instance = new MonsterPrototypes();
        }
        return instance;
    }

    @Override
    public Piece getPrototype(String classType) {
        return mP.get(classType);
    }

    @Override
    public Piece getPrototypeByName(String name) {
        return nameMap.get(name);
    }

    private void initializeMonsterPieceMap() {
        Medusa medusa = new Medusa(new Location(0, 0));
        mP.put(medusa.getPieceClass(), medusa);
        nameMap.put(medusa.getPieceName().get(), medusa);

        Minotaur minotaur = new Minotaur(new Location(0, 0));
        mP.put(minotaur.getPieceClass(), minotaur);
        nameMap.put(minotaur.getPieceName().get(), minotaur);

        Griffin griffin = new Griffin(new Location(0, 0));
        mP.put(griffin.getPieceClass(), griffin);
        nameMap.put(griffin.getPieceName().get(), griffin);

    }
}
