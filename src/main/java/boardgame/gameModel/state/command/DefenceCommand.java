package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.view.HexagonTileViewPiece;

public class DefenceCommand implements Command {

    private HexagonTileViewPiece ownPiece;
    private IGameManager gm;

    @Override
    public void execute() {
        ownPiece.getiPiece().createShield(gm.getTurn().getTurnNumber());
        System.out.println("Defending");
        // end turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public void SetCommand(IGameManager gm, HexagonTileViewPiece ownPiece) {
        this.gm = gm;
        this.ownPiece = ownPiece;
    }
}
