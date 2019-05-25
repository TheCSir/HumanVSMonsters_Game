package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.TileView;

public class SpecialAttackCommand extends SpecialCommand {
    private TurnFacade tf;
    private IGameManager gm;
    private double health;
    private IPiece enemyPiece;
    private double specialAttackMultiplier;
    private double finalDamage;
    private double healthOfEnemyPlayer;
    private IPiece selectedPiece;

    @Override
    public void execute() {
        //Store how much damage the attack will reduce for later undo action.
        health = gm.getAttackedPlayer(selectedPiece).calculateDamage(selectedPiece);

        //Double the amount of damage.
        healthOfEnemyPlayer = gm.getAttackedPlayer(enemyPiece).healthProperty().get();

        finalDamage = health * specialAttackMultiplier;

        gm.getAttackedPlayer(selectedPiece).healthProperty().setValue(healthOfEnemyPlayer - finalDamage);

        // end turn
        tf.nextTurn();
    }

    @Override
    public void undo() {
        tf.goBackOneTurn();
        gm.getAttackedPlayer(enemyPiece).increaseHealthProperty(finalDamage);
    }

    @Override
    public void redo() {
        gm.getAttackedPlayer(enemyPiece).healthProperty().setValue(healthOfEnemyPlayer - finalDamage);
        tf.nextTurn();
    }

    @Override
    public void setCommand(IGameManager gm, IPiece ownPiece, SpecialVisitor sv, TurnFacade tf, IPiece enemyPiece, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        this.gm = gm;
        this.enemyPiece = enemyPiece;
        this.selectedPiece = selectedPiece;
    }

    public void setSpecialAttackMultiplier(double sAV) {
        this.specialAttackMultiplier = sAV;
    }
}
