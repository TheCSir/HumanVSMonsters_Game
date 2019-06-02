package boardgame.gameModel.command;

import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.HexGridUtil;
import boardgame.view.TileView;

public class RangedAttackCommand extends SpecialCommand {

    private double rangedAttackValue;
    private SpecialVisitor sv;
    private TurnFacade tf;
    private IPiece selectedPiece;
    private double health;
    private double finalDamage;
    private double healthOfEnemyPlayer;
    private int rangedDistance;
    private IPiece ownPiece;

    @Override
    public void execute() {

        if (!tf.abilityUsedStatus()) {

            int dist = HexGridUtil.offset_distance(ownPiece.getLocation(), selectedPiece.getLocation());

            if (dist <= rangedDistance) {
                //If shielded halve the amount of damage.
                if (selectedPiece.getIsShielded()) {
                    rangedAttackValue = rangedAttackValue / 2;
                }

                //Double the amount of damage.
                healthOfEnemyPlayer = tf.getAttackedPlayer(selectedPiece).healthProperty().get();

                //Store how much damage the attack will reduce for later undo action.
                health = rangedAttackValue;

                //reduce health.

                tf.getAttackedPlayer(selectedPiece).healthProperty().setValue(healthOfEnemyPlayer - rangedAttackValue);

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
        this.sv = sv;
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
