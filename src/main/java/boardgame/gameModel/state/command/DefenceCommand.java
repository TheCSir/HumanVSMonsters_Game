package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.view.HexagonTileViewPiece;

public class DefenceCommand implements Command {

    private HexagonTileViewPiece ownPiece;
    private IGameManager gm;
    private Turn turn;


    @Override
    public void execute() {
        ownPiece.getiPiece().createShield(gm.getTurn().getTurnNumber());
        System.out.println("Defending");
        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    @Override
    public void undo() {
        //Roll back turn.
        int previousTurn = turn.getTurnNumber() - 1;
        turn.setTurnNumberProperty(previousTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = turn.getTurnNumber() % gm.getPlayers().size();
        turn.setActivePlayer(gm.getPlayers().get(nextPlayerIndex));

        ownPiece.getiPiece().setIsShielded(false);
    }

    @Override
    public void redo() {
        execute();
    }

    public void SetCommand(IGameManager gm, HexagonTileViewPiece ownPiece) {
        this.gm = gm;
        this.ownPiece = ownPiece;
        turn = gm.getTurn();
    }
}
