package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class HighlightTilesVisitor implements HighlightVisitor {

    private HightlightTiles highlightTiles;
    private IPiece selectedPiece;
    private IBoardGrid boardGrid;
    private IGameManager gm;
    private TurnFacade tf;
    private List<TileView> visited;

    @Override
    public void visit(RangedAttackState r) {
        //If within range make highlight blue.
        //If enemy piece make highlight red.

        List<IPiece> ownPieces = gm.getActivePlayer().getPieces();
        List<IPiece> allpieces = gm.getAllPieces();
        List<IPiece> enemyPieces = new ArrayList<>();
        for (IPiece piece : allpieces) {
            if (!ownPieces.contains(piece)) {
                enemyPieces.add(piece);
            }
        }

        for (IPiece piece : enemyPieces) {
            boardGrid.getTile(piece.getLocation()).setFill(Color.LIGHTGREEN);
        }
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

    @Override
    public void visit(EnemyPieceSel enemyPieceSel) {

    }

    @Override
    public void visit(IdleState idleState) {

    }

    @Override
    public void visit(MoveState moveState) {

    }

    @Override
    public void visit(AttackState attackState) {

    }

    @Override
    public void visit(DefenceState defenceState) {

    }


    public void setHighlightVariables(IPiece selectedPiece, IBoardGrid boardGrid, IGameManager gm, TurnFacade tf, List<TileView> visited) {
        this.selectedPiece = selectedPiece;
        this.boardGrid = boardGrid;
        this.gm = gm;
        this.tf = tf;
        this.visited = visited;
    }
}
