package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.HexagonTileViewPiece;

public class AttackCommand implements Command {
    private IGameManager gm;
    private IPiece enemyPiece;
    private double health;
    private Turn turn;

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
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    @Override
    public void undo() {

        IPlayer player = gm.getAttackedPlayer(enemyPiece);
        player.healthProperty().setValue(player.healthProperty().get() + health);

        //Roll back turn.
        int previousTurn = turn.getTurnNumber() - 1;
        turn.setTurnNumberProperty(previousTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = turn.getTurnNumber() % gm.getPlayers().size();
        turn.setActivePlayer(gm.getPlayers().get(nextPlayerIndex));

    }

    @Override
    public void redo() {
        execute();
    }

    public void setCommand(IGameManager gm, HexagonTileViewPiece enemyPiece) {
        this.gm = gm;
        this.enemyPiece = enemyPiece.getiPiece();
        turn = gm.getTurn();
    }
}
