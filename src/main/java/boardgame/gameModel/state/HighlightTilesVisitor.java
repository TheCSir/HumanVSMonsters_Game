package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.state.stateImp.HealState;
import boardgame.gameModel.state.stateImp.RangedAttackState;
import boardgame.gameModel.state.stateImp.SpecialAttackState;
import boardgame.gameModel.state.stateImp.SummonState;
import boardgame.view.IBoardGrid;

public class HighlightTilesVisitor implements HighlightVisitor {

    private HightlightTiles highlightTiles;
    private IPiece selectedPiece;
    private IBoardGrid boardGrid;
    private IGameManager gm;
    private TurnFacade tf;

    @Override
    public void visit(RangedAttackState r) {
        //If within range make highlight blue.
        //If enemy piece make highlight red.

    }

    @Override
    public void visit(SpecialAttackState s) {

    }

    @Override
    public void visit(SummonState s) {

    }

    @Override
    public void visit(HealState h) {

    }


    public void setHighlightVariables(IPiece selectedPiece, IBoardGrid boardGrid, IGameManager gm, TurnFacade tf) {
        this.selectedPiece = selectedPiece;
        this.boardGrid = boardGrid;
        this.gm = gm;
        this.tf = tf;
    }
}
