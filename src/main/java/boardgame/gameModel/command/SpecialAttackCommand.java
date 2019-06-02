package boardgame.gameModel.command;

import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Minion;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.TileView;

public class SpecialAttackCommand extends SpecialCommand {
    private TurnFacade tf;
    private double health;
    private double specialAttackMultiplier;
    private double finalDamage;
    private IPiece selectedPiece;
    private IPiece ownPiece;
    private Minion minion;

    @Override
    public void execute() {
        IPlayer activePlayer = tf.getActivePlayer();
        if (!activePlayer.getIsAbilityUsed()) {
            finalDamage = ownPiece.getAttack() * specialAttackMultiplier;
            // Handle attack if attack is to minion piece
            if (selectedPiece.getClass().getSimpleName().equals(PieceConstants.MINION)) {
                minion = (Minion) selectedPiece;
                health = tf.calculateEnemyDamage(finalDamage, selectedPiece);
            } else {
                //Store how much damage the attack will reduce for later undo action.
                health = tf.calculateEnemyDamage(finalDamage, selectedPiece);

                tf.applyEnemyDamage(selectedPiece, health);
            }

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
        if (minion != null) {
            minion.setHealth(minion.getHealth() + finalDamage);
        }

        tf.goBackOneTurn();
        tf.increaseEnemyHealth(selectedPiece, health);
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

    public void setSpecialAttackMultiplier(double sAV) {
        this.specialAttackMultiplier = sAV;
    }
}
