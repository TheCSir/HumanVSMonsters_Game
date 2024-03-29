package boardgame.gameModel.command;

import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Minion;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.HexagonTileViewPiece;

public class AttackCommand implements Command {
    private IPiece enemyPiece;
    private double damage;
    private TurnFacade tf;
    private Minion minion;
    private IPiece ownPiece;

    @Override
    public void execute() {

        // Handle attack if attack is to minion piece
        if (enemyPiece.getClass().getSimpleName().equals(PieceConstants.MINION)) {
            minion = (Minion) enemyPiece;
            damage = tf.calculateEnemyDamage(ownPiece.getAttack(), enemyPiece);
        }
        else {

            //Store how much damage the attack will reduce for later undo action.
            damage = tf.calculateEnemyDamage(ownPiece.getAttack(), enemyPiece);

            // get attacked player and apply damage
            tf.applyEnemyDamage(enemyPiece, damage);

        }

        tf.nextTurn();
    }

    @Override
    public void undo() {

        if (minion != null) {
            minion.setHealth(minion.getHealth() + ownPiece.getAttack());
        }

        IPlayer player = tf.getAttackedPlayer(enemyPiece);
        player.healthProperty().setValue(player.healthProperty().get() + damage);

        //Roll back turn.
        tf.goBackOneTurn();
    }

    @Override
    public void redo() {
        execute();
    }

    public void setCommand(TurnFacade tf, IPiece enemyPiece, HexagonTileViewPiece ownPiece) {
        this.tf = tf;
        this.enemyPiece = enemyPiece;
        this.ownPiece = ownPiece.getiPiece();
    }

}
