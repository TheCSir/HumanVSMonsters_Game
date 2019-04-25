package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.view.BoardGrid;
import boardgame.view.TileView;
import javafx.scene.paint.Color;

public class MoveState implements State {

    public MoveState() {
        System.out.println("In move state");
    }

    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Already in move state");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Moving to attack state");
        gameContext.setState(new AttackState());
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("changing to special state");
    }

    @Override
    public void onDefence(GameContext gameContext) {
        //TODO add once defence State added.
    }

    @Override
    public void onSwap(GameContext gameContext) {
        //TODO Add once Swap State added.
    }

    @Override
    public void notSelected(GameContext gameContext) {

    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {

    }

    @Override
    public void onSelectTile(GameContext gameContext) {
        System.out.println("The piece should try to move now. If it can't move stay in this state or go to idle");
        TileView tile = gameContext.getTileView();
        // Reset tiles color
        BoardGrid bg = gameContext.getBoardGrid();
        bg.setNeighbourTilesColor(gameContext.getOwnPiece(), Color.ANTIQUEWHITE);
        IGameManager gm = gameContext.getGm();
        //Update model.
        boolean pieceMoved = gm.getiBoard().movePiece(bg.getSelectedTilePiece().getiPiece(), tile.getLocation());

        if (pieceMoved) {
            // end turn
            gm.getTurn().nextTurn(gm.getPlayers());
            System.out.println("Piece moved");
        }
        gameContext.setState(new IdleState());
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

    }

    @Override
    public void attackPiece(GameContext gameContext) {

    }
}
