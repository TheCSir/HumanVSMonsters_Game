package boardgame.gameModel.state;

import boardgame.view.HexagonTileViewPiece;
import javafx.scene.text.Text;

//TODO decide whether this actually needs to be a state.
public class EnemyPieceSel implements State {
    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Nothing happens");
    }

    @Override
    public void notSelected(GameContext gameContext) {

    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        System.out.println("Changing state to own piece");
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

    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

    }

    @Override
    public void attackPiece(GameContext gameContext) {

    }
}
