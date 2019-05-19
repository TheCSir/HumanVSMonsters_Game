package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.util.HexGridUtil;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class HighlightTilesVisitor implements HighlightVisitor {

    private IPiece selectedPiece;
    private IBoardGrid boardGrid;
    private IGameManager gm;
    private TurnFacade tf;
    private List<TileView> visited;
    private SpecialVisitor sv;
    private int highlightDistance;

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
        //Colour pieces that can be hit red. Color pieces that can't be hit a different colour.
        for (IPiece piece : enemyPieces) {
            System.out.println("HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()) = " + HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()));
            System.out.println("highlightDistance = " + highlightDistance);
            if (HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()) <= highlightDistance) {
                boardGrid.getTile(piece.getLocation()).setFill(Color.RED);
            } else boardGrid.getTile(piece.getLocation()).setFill(Color.BLANCHEDALMOND);
        }

    }

    @Override
    public void visit(SpecialAttackState s) {
        List<IPiece> ownPieces = gm.getActivePlayer().getPieces();
        List<IPiece> allpieces = gm.getAllPieces();
        List<IPiece> enemyPieces = new ArrayList<>();
        for (IPiece piece : allpieces) {
            if (!ownPieces.contains(piece)) {
                enemyPieces.add(piece);
            }
        }
        //Colour pieces that can be hit red. Color pieces that can't be hit a different colour.
        for (IPiece piece : enemyPieces) {
            System.out.println("HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()) = " + HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()));
            System.out.println("highlightDistance = " + highlightDistance);
            if (HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()) <= highlightDistance) {
                boardGrid.getTile(piece.getLocation()).setFill(Color.RED);
            } else boardGrid.getTile(piece.getLocation()).setFill(Color.BLANCHEDALMOND);
        }
    }

    @Override
    public void visit(SummonState s) {

    }

    @Override
    public void visit(HealState h) {
        List<IPiece> ownPieces = gm.getActivePlayer().getPieces();
        for (IPiece piece : ownPieces) {
            boardGrid.getTile(piece.getLocation()).setFill(Color.GREEN);
        }
    }

    @Override
    public void visit(EnemyPieceSel enemyPieceSel) {

    }

    @Override
    public void visit(IdleState idleState) {
        //Reset all tile colours
        for (TileView tile : visited) {
            tile.setFill(Color.ANTIQUEWHITE);
        }

        //Highlight tiles depending on player.


    }

    @Override
    public void visit(MoveState moveState) {

    }

    @Override
    public void visit(AttackState attackState) {

        //If within range make highlight blue.
        //If enemy piece make highlight red.
        highlightDistance = 1;
        List<IPiece> ownPieces = gm.getActivePlayer().getPieces();
        List<IPiece> allpieces = gm.getAllPieces();
        List<IPiece> enemyPieces = new ArrayList<>();
        for (IPiece piece : allpieces) {
            if (!ownPieces.contains(piece)) {
                enemyPieces.add(piece);
            }
        }
        //Colour pieces that can be hit red. Color pieces that can't be hit a different colour.
        for (IPiece piece : enemyPieces) {
            System.out.println("HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()) = " + HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()));
            System.out.println("highlightDistance = " + highlightDistance);
            if (HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()) <= highlightDistance) {
                boardGrid.getTile(piece.getLocation()).setFill(Color.RED);
            } else boardGrid.getTile(piece.getLocation()).setFill(Color.BLANCHEDALMOND);
        }

    }

    @Override
    public void visit(DefenceState defenceState) {
        //If within range make highlight blue.
        //If enemy piece make highlight red.
        highlightDistance = 1;
        List<IPiece> ownPieces = gm.getActivePlayer().getPieces();

        //Colour pieces that can be hit red. Color pieces that can't be hit a different colour.
        for (IPiece piece : ownPieces) {
            boardGrid.getTile(piece.getLocation()).setFill(Color.GREEN);
        }
    }


    public void setHighlightVariables(IPiece selectedPiece, IBoardGrid boardGrid, IGameManager gm, TurnFacade tf, List<TileView> visited, SpecialVisitor sv) {
        this.selectedPiece = selectedPiece;
        this.boardGrid = boardGrid;
        this.gm = gm;
        this.tf = tf;
        this.visited = visited;
        this.sv = sv;
    }

    public void setHighlightVariables(IBoardGrid boardGrid, IGameManager gm, List<TileView> visited) {
        this.boardGrid = boardGrid;
        this.gm = gm;
        this.visited = visited;
    }

    public void setHighlightDistance(int rangedDistance) {
        this.highlightDistance = rangedDistance;
    }
}
