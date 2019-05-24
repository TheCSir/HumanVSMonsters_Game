package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.gameModel.pieces.PieceConstants;

public class AttackCommand implements Command {
    private IGameManager gm;
    private IPiece enemyPiece;
    private double health;
    private TurnFacade tf;

    @Override
    public void execute() {

        System.out.println("enemy piece is: " + enemyPiece.getClass().getName());

        System.out.println("Current player is: " + gm.getTurn().getActivePlayer().getPlayerName());

        // Handle attack if attack is to minion piece
        if (enemyPiece.getClass().getSimpleName().equals(PieceConstants.MINION)) {

            enemyPiece.decreaseHealth(1);
            System.out.println("Enemy hp is " + enemyPiece.getHealth());


            if (enemyPiece.getHealth()==0) {
                gm.removePiece(enemyPiece);
                gm.getPlayers().get(getOpponentPlayerID(gm.getActivePlayer())).setIsAbilityUsed(false);
            }

        }
        else {
            System.out.println("Attacked player is: " + gm.getAttackedPlayer(enemyPiece).getPlayerName());

            //Store how much damage the attack will reduce for later undo action.
            health = gm.getAttackedPlayer(enemyPiece).calculateDamage(enemyPiece);

            // get attacked player
            gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(enemyPiece);
        }

        tf.nextTurn();
    }

    @Override
    public void undo() {

        IPlayer player = gm.getAttackedPlayer(enemyPiece);
        player.healthProperty().setValue(player.healthProperty().get() + health);

        //Roll back turn.
        tf.goBackOneTurn();

    }

    @Override
    public void redo() {
        execute();
    }

    public void setCommand(TurnFacade tf, IGameManager gm, IPiece enemyPiece) {
        this.tf = tf;
        this.gm = gm;
        this.enemyPiece = enemyPiece;
    }

    private int getOpponentPlayerID(IPlayer activePlayer){
        if (gm.getPlayers().get(0).equals(activePlayer)) {
            return 1;
        }
        else
            return 0;
    }
}
