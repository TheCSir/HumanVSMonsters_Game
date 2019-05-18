package boardgame.gameModel.state.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;

public class RangedAttackCommand extends SpecialCommand {

    private double rangedAttackValue;
    private IGameManager gm;
    private IPiece piece;
    private SpecialVisitor sv;
    private TurnFacade tf;
    private IPiece iPiece;
    private IPiece selectedPiece;

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece iPiece, IPiece selectedPiece) {
        this.gm = gm;
        this.piece = piece;
        this.sv = sv;
        this.tf = tf;
        this.iPiece = iPiece;
        this.selectedPiece = selectedPiece;
    }

    public void setRangedAttackValue(double rangedAttackValue) {
        this.rangedAttackValue = rangedAttackValue;
    }
}
