package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.HexagonTileViewPiece;

public class SpecialCommand implements Command {

    private IPiece iPiece;
    private IGameManager gm;
    private IPiece enemyPiece;

    @Override
    public void execute() {
        iPiece.specialAbility(enemyPiece, gm);
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public void setCommand(IGameManager gm, IPiece piece, HexagonTileViewPiece enemyPiece) {
        this.iPiece = piece;
        this.gm = gm;
        this.enemyPiece = enemyPiece.getiPiece();
    }
}
