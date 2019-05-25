package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.TileView;

public class RangedAttackCommand extends SpecialCommand {

    private double rangedAttackValue;
    private IGameManager gm;
    private SpecialVisitor sv;
    private TurnFacade tf;
    private IPiece selectedPiece;
    private double health;
    private double finalDamage;
    private double healthOfEnemyPlayer;

    @Override
    public void execute() {

        //If shielded halve the amount of damage.
        if (selectedPiece.getIsShielded()) {
            rangedAttackValue = rangedAttackValue / 2;
        }
        //Double the amount of damage.
        healthOfEnemyPlayer = gm.getAttackedPlayer(selectedPiece).healthProperty().get();

        //Store how much damage the attack will reduce for later undo action.
        health = rangedAttackValue;

        //reduce health.
        gm.getAttackedPlayer(selectedPiece).healthProperty().setValue(healthOfEnemyPlayer - rangedAttackValue);


        // end turn
        tf.nextTurn();
    }

    @Override
    public void undo() {

        IPlayer player = gm.getAttackedPlayer(selectedPiece);
        player.healthProperty().setValue(player.healthProperty().get() + health);

        //Roll back turn.
        tf.goBackOneTurn();

    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public void setCommand(IGameManager gm, IPiece ownPiece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.gm = gm;
        this.sv = sv;
        this.tf = tf;
        this.selectedPiece = selectedPiece;
    }

    public void setRangedAttackValue(double rangedAttackValue) {
        this.rangedAttackValue = rangedAttackValue;
    }
}
