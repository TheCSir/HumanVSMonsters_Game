package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;

public class SpecialCommandSummon implements Command {

    private IPiece iPiece;
    private IGameManager gm;

    @Override
    public void execute() {}

    @Override
    public void undo(){}

    @Override
    public void redo(){}

    public void setCommand(IGameManager gm, IPiece piece) {
        this.iPiece = piece;
        this.gm = gm;
    }
}
