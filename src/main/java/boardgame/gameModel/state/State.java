package boardgame.gameModel.state;

/**
 * The interface for our State classes. In our project we use the State pattern to implement our
 */
public interface State {

    /**
     * On move.
     *
     * @param gameContext the game context
     */
    void onMove(GameContext gameContext);

    /**
     * On attack.
     *
     * @param gameContext the game context
     */
    void onAttack(GameContext gameContext);

    /**
     * On special.
     *
     * @param gameContext the game context
     */
    void onSpecial(GameContext gameContext);

    /**
     * On defence.
     *
     * @param gameContext the game context
     */
    void onDefence(GameContext gameContext);

    /**
     * On swap.
     *
     * @param gameContext the game context
     */
    void onSwap(GameContext gameContext);

    /**
     * On select own piece.
     *
     * @param gameContext the game context
     */
    void onSelectOwnPiece(GameContext gameContext);

    /**
     * On select tile.
     *
     * @param gameContext the game context
     */
    void onSelectTile(GameContext gameContext);

    /**
     * On select enemy piece.
     *
     * @param gameContext the game context
     */
    void onSelectEnemyPiece(GameContext gameContext);

    /**
     * On swap one.
     *
     * @param gameContext the game context
     */
    void onSwapOne(GameContext gameContext);

    /**
     * On swap two.
     *
     * @param gameContext the game context
     */
    void onSwapTwo(GameContext gameContext);

    /**
     * Accept.
     *
     * @param v the v
     */
    void accept(HighlightVisitor v);
}