package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;

public class AttackCommand implements Command {
    private IGameManager gm;
    private IPiece enemyPiece;
    private double health;
    private TurnFacade tf;

    @Override
    public void execute() {

        System.out.println("enemy piece is: " + enemyPiece.getClass().getName());

        System.out.println("Current player is: " + gm.getTurn().getActivePlayer().getPlayerName());
        System.out.println("Attacked player is: " + gm.getAttackedPlayer(enemyPiece).getPlayerName());

        //Store how much damage the attack will reduce for later undo action.
        health = gm.getAttackedPlayer(enemyPiece).calculateDamage(enemyPiece);

        // get attacked player
        gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(enemyPiece);
        // end turn
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
}
