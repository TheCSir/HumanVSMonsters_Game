package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.HexagonTileViewPiece;

public class AttackCommand implements Command {
    private IGameManager gm;
    private IPiece enemyPiece;

    @Override
    public void execute() {

        System.out.println("enemy piece is: " + enemyPiece.getClass().getName());

        System.out.println("Current player is: " + gm.getTurn().getActivePlayer().getPlayerName());
        System.out.println("Attacked player is: " + gm.getAttackedPlayer(enemyPiece).getPlayerName());
        // get attacked player
        gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(enemyPiece);

        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public void setCommand(IGameManager gm, HexagonTileViewPiece enemyPiece) {
        this.gm = gm;
        this.enemyPiece = enemyPiece.getiPiece();
    }
}
