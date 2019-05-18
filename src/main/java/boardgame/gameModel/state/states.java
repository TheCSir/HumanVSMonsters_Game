package boardgame.gameModel.state;

/**
 * All available states have a related enum held here as the StateFactory is the class that uses these enums.
 */
public enum states {
    ATTACK,
    MOVE,
    SWAP,
    SPECIAL,
    DEFENCE,
    IDLE,
    OWNPIECESELECTED,
    ENEMYPIECESELECTED,
    BASH, SUMMON, HEALSTATE
}
