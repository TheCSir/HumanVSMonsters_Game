package boardgame.gameModel.state;

import boardgame.view.HexagonTileViewPiece;
import javafx.scene.text.Text;

public class IdleState implements State {

    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void notSelected(GameContext gameContext) {

    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        System.out.println("transitioning to piece selection");
        HexagonTileViewPiece piece = gameContext.getOwnPiece();
        Text pieceLocation = gameContext.getMc().getPieceLocation();
        Text pieceSelected = gameContext.getMc().getPieceSelected();
        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());
        gameContext.setState(new OwnPieceSelected());
    }

    @Override
    public void onSelectTile(GameContext gameContext) {
        System.out.println("Print tile info?");
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {
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

    @Override
    public void onSwapOne(GameContext gameContext) {
        System.out.println("Shouldn't be possible to get here");
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        System.out.println("How did you get here?");
    }
}
