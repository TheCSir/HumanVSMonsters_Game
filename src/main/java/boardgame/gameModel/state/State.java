package boardgame.gameModel.state;

public interface State {

    void onMove(GameContext gameContext);

    void onAttack(GameContext gameContext);

    void onSpecial(GameContext gameContext);

    void onDefence(GameContext gameContext);

    void onSwap(GameContext gameContext);

    void onSelectOwnPiece(GameContext gameContext);

    void onSelectTile(GameContext gameContext);

    void onSelectEnemyPiece(GameContext gameContext);

    void onSwapOne(GameContext gameContext);

    void onSwapTwo(GameContext gameContext);

    void accept(HighlightVisitor v);
}