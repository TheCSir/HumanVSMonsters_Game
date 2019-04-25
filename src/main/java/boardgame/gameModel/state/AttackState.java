package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;

public class AttackState extends OwnPieceSelected {

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Already in attack state");
    }


    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {
        IGameManager gm = gameContext.getGm();

        IPiece enemyPiece = gameContext.getEnemyPiece().getiPiece();

        System.out.println("enemy piece is: " + enemyPiece.getClass().getName());

        System.out.println("Current player is: " + gm.getTurn().getActivePlayer().getPlayerName());
        System.out.println("Attacked player is: " + gm.getAttackedPlayer(enemyPiece).getPlayerName());
        // get attacked player
        gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(enemyPiece);

        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());

        gameContext.setState(new IdleState());
    }

}
