package boardgame.gameModel.command;

import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.TileView;

public class SpecialAttackCommand extends SpecialCommand {
    private TurnFacade tf;
    private double health;
    private double specialAttackMultiplier;
    private double finalDamage;
    private double healthOfEnemyPlayer;
    private IPiece selectedPiece;

    @Override
    public void execute() {
        IPlayer activePlayer = tf.getActivePlayer();
        if (!activePlayer.getIsAbilityUsed()) {

            //Store how much damage the attack will reduce for later undo action.
            health = tf.calculateEnemyDamage(selectedPiece);

            //Double the amount of damage.
            healthOfEnemyPlayer = tf.getPlayerHealth(selectedPiece);

            finalDamage = health * specialAttackMultiplier;

            tf.setEnemyHealth(selectedPiece, healthOfEnemyPlayer, finalDamage);


            //set ability used counter
            tf.setAbilityUsed();


            // end turn
            tf.nextTurn();
        } else {
            System.out.println("Special ability already used!!");
        }

    }

    @Override
    public void undo() {
        tf.goBackOneTurn();
        tf.increaseEnemyHealth(selectedPiece, finalDamage);

    }

    @Override
    public void redo() {
        tf.setEnemyHealth(selectedPiece, healthOfEnemyPlayer, finalDamage);

        tf.nextTurn();
    }

    @Override
    public void setCommand(IPiece ownPiece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        this.selectedPiece = selectedPiece;
    }

    public void setSpecialAttackMultiplier(double sAV) {
        this.specialAttackMultiplier = sAV;
    }
}
