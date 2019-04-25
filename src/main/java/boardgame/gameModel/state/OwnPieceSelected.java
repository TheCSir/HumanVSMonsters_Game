package boardgame.gameModel.state;

import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileViewPiece;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class OwnPieceSelected implements State {

    public OwnPieceSelected() {
        System.out.println("In own piece selected state");
    }

    @Override
    public void onMove(GameContext gameContext) {
        //TODO highlight surrounding tiles.
        BoardGrid bg = gameContext.getBoardGrid();
        bg.setNeighbourTilesColor(bg.getSelectedTilePiece(), Color.RED);
        System.out.println("Setting surrounding colour to Red");
        System.out.println("Setting move state");
        gameContext.setState(new MoveState());
    }

    @Override
    public void onAttack(GameContext gameContext) {
        //TODO highlight surrounding tiles.
        System.out.println("setting attack state");
        gameContext.setState(new AttackState());
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Set special state");
        //TODO set up new buttons to click.

        gameContext.setState(new SpecialState());
    }

    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Setting defense state");
    }

    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("setting swap state.");
    }

    @Override
    public void notSelected(GameContext gameContext) {
        System.out.println("Setting idle state");
    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        System.out.println("No change");
    }

    @Override
    public void onSelectTile(GameContext gameContext) {
        System.out.println("selecting tile");
        gameContext.setState(new IdleState());
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {
        System.out.println("selecting enemy piece");
        HexagonTileViewPiece piece = gameContext.getEnemyPiece();
        Text pieceLocation = gameContext.getMc().getPieceLocation();
        Text pieceSelected = gameContext.getMc().getPieceSelected();
        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());
        gameContext.setState(new EnemyPieceSel());
    }

    @Override
    public void attackPiece(GameContext gameContext) {

    }
}
