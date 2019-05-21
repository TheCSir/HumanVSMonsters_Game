package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;

public abstract class SpecialCommand implements Command {

    @Override
    public abstract void execute();

    @Override
    public abstract void undo();

    @Override
    public abstract void redo();

    public abstract void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece iPiece, IPiece selectedPiece);


}
