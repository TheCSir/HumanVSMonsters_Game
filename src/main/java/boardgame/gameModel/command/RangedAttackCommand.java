package boardgame.gameModel.command;

import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Minion;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.HexGridUtil;
import boardgame.view.TileView;

public class RangedAttackCommand extends SpecialCommand {

    private double rangedAttackValue;
    private TurnFacade tf;
    private IPiece selectedPiece;
    private double health;
    private int rangedDistance;
    private IPiece ownPiece;
    private Minion minion;

    @Override
    public void execute() {

        if (!tf.abilityUsedStatus()) {

            int dist = HexGridUtil.offset_distance(ownPiece.getLocation(), selectedPiece.getLocation());

            if (dist <= rangedDistance) {
                // Handle attack if attack is to minion piece
                if (selectedPiece.getClass().getSimpleName().equals(PieceConstants.MINION)) {
                    minion = (Minion) selectedPiece;
                    health = tf.calculateEnemyDamage(rangedAttackValue, selectedPiece);
                } else {
                    //Store how much damage the attack will reduce for later undo action.
                    health = tf.calculateEnemyDamage(rangedAttackValue, selectedPiece);

                    //reduce health.
                    tf.applyEnemyDamage(selectedPiece, health);
                }

                //set ability used counter
                tf.getActivePlayer().setIsAbilityUsed(tf.getTurnNumber());

                // end turn
                tf.nextTurn();
            }
        }
        else {
            System.out.println("Special ability already used!!");
        }
    }

    @Override
    public void undo() {

        if (minion != null) {
            minion.setHealth(minion.getHealth() + rangedAttackValue);
        }

        IPlayer player = tf.getAttackedPlayer(selectedPiece);
        player.healthProperty().setValue(player.healthProperty().get() + health);

        //Roll back turn.
        tf.goBackOneTurn();

    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public void setCommand(IPiece ownPiece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        this.selectedPiece = selectedPiece;
        this.ownPiece = ownPiece;
    }

    public void setRangedAttackValue(double rangedAttackValue) {
        this.rangedAttackValue = rangedAttackValue;
    }

    public void setRangedAttackDistance(int rangedDistance) {
        this.rangedDistance = rangedDistance;
    }
}
