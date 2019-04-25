package boardgame.gameModel.state;

import boardgame.controller.MainController;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Warrior;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.TileView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameContext {

    private final IGameManager gm;
    private final MainController mc;
    private final BoardGrid boardGrid;
    private State state;
    private HexagonTileViewPiece ownPiece;
    private HexagonTileViewPiece enemyPiece;
    private TileView tileView;
    private Pane swapPane;
    private Button opt_one;
    private Button opt_two;
    private IPlayer activePlayer;

    public GameContext(State state, BoardGrid boardGrid, IGameManager gm, MainController mc) {
        this.state = state;
        this.boardGrid = boardGrid;
        this.gm = gm;
        this.mc = mc;
    }

    public void pressMove() {
        state.onMove(this);
    }

    public void pressAttack() {
        state.onAttack(this);
    }

    public void pressSpecial(Pane swapPane, Button opt_one, Button opt_two) {

        state.onSpecial(this);
    }

    public void pressDefence(GameContext gameContext) {
        //TODO Add implementation for defence.
        System.out.println("To be implemented");
        state.onDefence(this);
    }

    public void clickTile(TileView tile) {
        this.tileView = tile;
        boardGrid.setTargetTile(tile);
        state.onSelectTile(this);
    }

    public void selectOwnPiece(HexagonTileViewPiece ownpiece) {
        this.ownPiece = ownpiece;
        state.onSelectOwnPiece(this);
    }

    public void selectEnemyPiece(HexagonTileViewPiece enemyPiece) {
        this.enemyPiece = enemyPiece;
        state.onSelectEnemyPiece(this);
    }

    public void selectPiece(HexagonTileViewPiece piece) {
        setActivePlayer(piece.getiPiece());
        if (isActivePlayerPiece(piece.getiPiece())) {
            this.ownPiece = piece;
            state.onSelectOwnPiece(this);
        } else {
            this.enemyPiece = piece;
            state.onSelectEnemyPiece(this);
        }
    }

    public void attackEnemyPiece(HexagonTileViewPiece enemyPiece) {
        this.enemyPiece = enemyPiece;
    }

    public void pressSwapButton(Pane swapPane, Button opt_one, Button opt_two) {
        this.swapPane = swapPane;
        this.opt_one = opt_one;
        this.opt_two = opt_two;
        state.onSwap(this);
    }

    public void pressSwapOne(Pane swapPane, Button opt_one) {
        state.onSwapOne(this);
    }

    public void pressSwapTwo(Pane swapPane, Button opt_two) {
        state.onSwapTwo(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public BoardGrid getBoardGrid() {
        return boardGrid;
    }

    public HexagonTileViewPiece getEnemyPiece() {
        return enemyPiece;
    }

    public void setEnemyPiece(HexagonTileViewPiece piece) {
        enemyPiece = piece;
    }

    public HexagonTileViewPiece getOwnPiece() {
        return ownPiece;
    }

    public void setOwnPiece(HexagonTileViewPiece piece) {
        ownPiece = piece;
    }

    public TileView getTileView() {
        return tileView;
    }

    public IGameManager getGm() {
        return gm;
    }

    public MainController getMc() {
        return mc;
    }

    public Pane getSwapPane() {
        return swapPane;
    }

    public Button getOpt_one() {
        return opt_one;
    }

    public Button getOpt_two() {
        return opt_two;
    }

    public void resetTileColours() {
        boardGrid.setNeighbourTilesColor(boardGrid.getSelectedTilePiece(), Color.ANTIQUEWHITE);
    }

    // Checks if selected piece belongs to the active player
    private boolean isActivePlayerPiece(IPiece ipiece) {

        String[] humanPieces = {"Warrior", "Priest", "Archer"};
        String[] monsterPieces = {"Medusa", "Minotaur", "Griffin"};
        System.out.println(Warrior.class.getSimpleName());
        System.out.println("Active player is: " + gm.getTurn().getActivePlayer().getPlayerName());
        for (IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if (piece.getClass().getSuperclass().equals(ipiece.getClass().getSuperclass())) {
                return true;
            }
        }
        return false;
    }

    public IPlayer getActivePlayer() {
        return activePlayer;
    }

    private void setActivePlayer(IPiece ipiece) {

        for (IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if (piece.getClass().getSuperclass().equals(ipiece.getClass().getSuperclass())) {
                activePlayer = gm.getTurn().getActivePlayer();
            }
        }
    }
}
