package boardgame.gameModel.command;

import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Minion;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.Constants;

public class AttackCommand implements Command {
    private IPiece enemyPiece;
    private double health;
    private TurnFacade tf;
    private Minion minion;

    @Override
    public void execute() {

        // Handle attack if attack is to minion piece
        if (enemyPiece.getClass().getSimpleName().equals(PieceConstants.MINION)) {
            minion = (Minion) enemyPiece;
            enemyPiece.decreaseHealth(Constants.MINIONDAMAGERECIVE);

            if (enemyPiece.getHealth()== Constants.PIECEMINIMIMHP) {
                tf.removePiece(enemyPiece);
                tf.resetAbilityUsed();
            }

        }
        else {

            //Store how much damage the attack will reduce for later undo action.
            health = tf.calculateEnemyDamage(enemyPiece);

            // get attacked player
            tf.applyEnemyDamage(enemyPiece);

        }

        tf.nextTurn();
    }

    @Override
    public void undo() {

        if (minion != null) {
            if (minion.getHealth() == Constants.PIECEMINIMIMHP) {
                minion.setHealth(1);
                tf.addPiece(minion);
            } else minion.setHealth(minion.getHealth() + Constants.MINIONDAMAGERECIVE);
        }

        IPlayer player = tf.getAttackedPlayer(enemyPiece);
        player.healthProperty().setValue(player.healthProperty().get() + health);

        //Roll back turn.
        tf.goBackOneTurn();
    }

    @Override
    public void redo() {
        execute();
    }

    public void setCommand(TurnFacade tf, IPiece enemyPiece) {
        this.tf = tf;
        this.enemyPiece = enemyPiece;
    }


}
