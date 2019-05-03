package boardgame.gameModel.state;

import boardgame.controller.MainController;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Warrior;
import boardgame.gameModel.players.IPlayer;
import boardgame.gameModel.state.command.MoveCommand;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameContext {

    private final IGameManager gm;
    private final MainController mc;
    private final IBoardGrid IBoardGrid;
    private State state;
    private HexagonTileViewPiece ownPiece;
    private HexagonTileViewPiece enemyPiece;
    private TileView tileView;
    private Pane swapPane;
    private Button opt_one;
    private Button opt_two;

    public GameContext(State state, IBoardGrid IBoardGrid, IGameManager gm, MainController mc) {
        this.state = state;
        this.IBoardGrid = IBoardGrid;
        this.gm = gm;
        this.mc = mc;
    }

    public void pressMove() {
        state.onMove(this);
    }

    public void pressAttack() {
        state.onAttack(this);
    }

    public void pressSpecial() {

        state.onSpecial(this);
    }

    public void pressDefence(GameContext gameContext) {
        //TODO Add implementation for defence.
        System.out.println("To be implemented");
        state.onDefence(this);
    }

    public void clickTile(TileView tile) {
        this.tileView = tile;
        IBoardGrid.setTargetTile(tile);
        state.onSelectTile(this);
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

    public void pressSwapButton(Pane swapPane, Button opt_one, Button opt_two) {
        this.swapPane = swapPane;
        this.opt_one = opt_one;
        this.opt_two = opt_two;
        state.onSwap(this);
    }

    public void pressSwapOne() {
        state.onSwapOne(this);
    }

    public void pressSwapTwo() {
        state.onSwapTwo(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public IBoardGrid getBoardGrid() {
        return IBoardGrid;
    }

    public HexagonTileViewPiece getEnemyPiece() {
        return enemyPiece;
    }

    public HexagonTileViewPiece getOwnPiece() {
        return ownPiece;
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
        // IBoardGrid.setNeighbourTilesColor(IBoardGrid.getSelectedTilePiece(), Color.ANTIQUEWHITE);
        System.out.println("resetting tile colours");

        // Reset tiles color
        IBoardGrid bg = getBoardGrid();
        bg.setNeighbourTilesColor(getOwnPiece(), Color.ANTIQUEWHITE);
    }

    // Checks if selected piece belongs to the active player
    private boolean isActivePlayerPiece(IPiece ipiece) {

        System.out.println(Warrior.class.getSimpleName());
        System.out.println("Active player is: " + gm.getTurn().getActivePlayer().getPlayerName());
        for (IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if (piece.getClass().getSuperclass().equals(ipiece.getClass().getSuperclass())) {
                return true;
            }
        }
        return false;
    }

    private void setActivePlayer(IPiece ipiece) {

        for (IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if (piece.getClass().getSuperclass().equals(ipiece.getClass().getSuperclass())) {
                IPlayer activePlayer = gm.getTurn().getActivePlayer();
            }
        }
    }

    public void updatePieceDetails() {
        HexagonTileViewPiece piece = getOwnPiece();

        //Update View.
        Text pieceLocation = getMc().getPieceLocation();
        Text pieceSelected = getMc().getPieceSelected();
        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());
    }

    void updateEnemyPieceDetails() {
        IPiece piece = getEnemyPiece().getiPiece();
        Text pieceLocation = getMc().getPieceLocation();
        Text pieceSelected = getMc().getPieceSelected();
        pieceSelected.setText("Class: " + piece.getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getLocation().getX()
                + ", "
                + "Y: " + piece.getLocation().getY());
    }

    void highlightMove() {
        IBoardGrid bg = getBoardGrid();
        bg.setNeighbourTilesColor(getOwnPiece(), Color.RED);

    }

    void attackPiece() {
        IGameManager gm = getGm();

        IPiece enemyPiece = getEnemyPiece().getiPiece();

        System.out.println("enemy piece is: " + enemyPiece.getClass().getName());

        System.out.println("Current player is: " + gm.getTurn().getActivePlayer().getPlayerName());
        System.out.println("Attacked player is: " + gm.getAttackedPlayer(enemyPiece).getPlayerName());
        // get attacked player
        gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(enemyPiece);

        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    public void createShield() {
        //TODO Fix bug here.
        getOwnPiece().getiPiece().createShield(getGm().getTurn().getTurnNumber());
        System.out.println("Defending");
        // end turn
        getGm().getTurn().nextTurn(getGm().getPlayers());
    }

    public void updateTileInfo() {
    }


    public void movePiece() {
        MoveCommand command = new MoveCommand();
        command.SetCommand(getGm(), getTileView().getModelTile().getLocation(), getOwnPiece().getiPiece());
        command.execute();
    }

    public void setUpSwap() {
    }
}
