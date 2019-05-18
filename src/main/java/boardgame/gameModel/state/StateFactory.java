package boardgame.gameModel.state;

import java.util.HashMap;
import java.util.Map;

/**
 * State Factory class creates States for use by the State Pattern. This class was created as before this
 * the State transition was gamecontext.setState(new State()). This class means that the responsibility for creating
 * a State is moved out of the State(or gamecontext) classes and to this class. This is so that there is better
 * abstraction and it also eliminates coupling between State classes. As there is no need for multiple instances
 * this class is lazily instantiated (Singleton pattern) and the constructor is private. States are not intended to be
 * accessed outside the state package and so the class is made 'package-private' to improve encapsulation.
 */
public class StateFactory {

    //Holds allinstances of State classes.
    private static Map<states, State> allStates = new HashMap<>();

    private static StateFactory instance;

    private StateFactory() {
    }

    //Singleton for state factory. No need for multiple instances.
    private static void newInstance() {
        if (instance == null) {
            instance = new StateFactory();

            //Put a new State of each type into map. States are very lightweight objects so
            //no issue instantiating them all once at initialization.
            allStates.put(states.ATTACK, new AttackState());
            allStates.put(states.MOVE, new MoveState());
            allStates.put(states.SWAP, new SwapState());
            allStates.put(states.DEFENCE, new DefenceState());
            allStates.put(states.IDLE, new IdleState());
            allStates.put(states.OWNPIECESELECTED, new OwnPieceSelected());
            allStates.put(states.ENEMYPIECESELECTED, new EnemyPieceSel());
            allStates.put(states.HEALSTATE, new HealState());
            allStates.put(states.SUMMON, new SummonState());
            allStates.put(states.SPECIALATTACKSTATE, new SpecialAttackState());
            allStates.put(states.RANGEDATTACK, new RangedAttackState());
        }
    }

    /**
     * This is the only non private method in the State factory. When a state is called for the first time
     * the state map is initialized. Every request for a State must go through this method.
     */
    public static State getState(states state) {
        if (instance == null) {
            newInstance();
        }
        return allStates.get(state);
    }
}
