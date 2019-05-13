package boardgame.gameModel.state;

import java.util.HashMap;
import java.util.Map;

enum states {
    ATTACK,
    MOVE,
    SWAP,
    SPECIAL,
    DEFENCE,
    IDLE,
    OWNPIECESELECTED,
    ENEMYPIECESELECTED
}

public class StateFactory {

    private static Map<states, State> allStates = new HashMap<>();

    private static StateFactory instance;

    private StateFactory() {
    }

    //Singleton for state factory. No need for multiple instances.
    private static void getInstance() {
        if (instance == null) {
            instance = new StateFactory();

            //Put a new State of each type into map. States are very lightweight objects so
            //no issue instantiating them all once at initialization.
            allStates.put(states.ATTACK, new AttackState());
            allStates.put(states.MOVE, new MoveState());
            allStates.put(states.SPECIAL, new SpecialState());
            allStates.put(states.SWAP, new SwapState());
            allStates.put(states.DEFENCE, new DefenceState());
            allStates.put(states.IDLE, new IdleState());
            allStates.put(states.OWNPIECESELECTED, new OwnPieceSelected());
            allStates.put(states.ENEMYPIECESELECTED, new EnemyPieceSel());
        }
    }

    public static State getState(states state) {
        if (instance == null) {
            getInstance();
        }
        return allStates.get(state);
    }
}
