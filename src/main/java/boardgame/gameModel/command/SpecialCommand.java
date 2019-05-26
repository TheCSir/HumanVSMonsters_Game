package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.TileView;

/**
 * The type Special command.
 */
public abstract class SpecialCommand implements Command {

    @Override
    public abstract void execute();

    @Override
    public abstract void undo();

    @Override
    public abstract void redo();

    /**
     * Sets command.
     *
     * @param gm            the gm
     * @param piece         the piece
     * @param sv            the sv
     * @param tf            the tf
     * @param selectedPiece the selected piece
     * @param tileView      the tile view
     */
    public abstract void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView);


}
