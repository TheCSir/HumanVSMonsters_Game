package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.state.stateImp.*;
import boardgame.util.HexGridUtil;
import boardgame.util.Location;
import boardgame.util.PieceUtil;
import boardgame.view.IBoardGrid;
import boardgame.view.TileView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static boardgame.util.HexGridUtil.offset_distance;

public class HighlightTilesVisitor implements HighlightVisitor {

    private IPiece selectedPiece;
    private IBoardGrid boardGrid;
    private IGameManager gm;
    private TurnFacade tf;
    private List<TileView> visited;
    private SpecialVisitor sv;
    private int highlightDistance;
    private List<TileView> targetTiles = new ArrayList<>();

    @Override
    public void visit(RangedAttackState r) {
        //If within range make highlight blue.
        //If enemy piece make highlight red.

        List<IPiece> enemyPieces = PieceUtil.getEnemyPieces(gm);
        //Colour pieces that can be hit red. Color pieces that can't be hit a different colour.
        highlightAttackTiles(enemyPieces);

    }

    @Override
    public void visit(SpecialAttackState s) {
        List<IPiece> enemyPieces = PieceUtil.getEnemyPieces(gm);
        //Colour pieces that can be hit red. Color pieces that can't be hit a different colour.
        highlightAttackTiles(enemyPieces);
    }

    public void highlightAttackTiles(List<IPiece> enemyPieces) {
        for (IPiece piece : enemyPieces) {
            if (HexGridUtil.offset_distance(piece.getLocation(), selectedPiece.getLocation()) <= highlightDistance) {
                targetTiles.add(boardGrid.getTile(piece.getLocation()));
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
        for (TileView tile : boardGrid.getTileViewObservableMap().values()) {
            tile.setFill(Color.ANTIQUEWHITE);
        }

        //Highlight tiles depending on player.
        for (IPiece piece : gm.getActivePlayer().getPieces()) {

            TileView t = boardGrid.getTile(piece.getLocation());
            t.setFill(Color.BLUE);
        }

    }

    /**
     * Highlight tiles that can be moved to.
     */
    @Override
    public void visit(MoveState moveState) {


        int movespeed = selectedPiece.getMoveSpeed();

        Location pieceLocation = selectedPiece.getLocation();

        for (TileView tileView : visited) {
            int offDist = offset_distance(pieceLocation, tileView.getLocation());


            if (offDist <= movespeed) {
                tileView.setFill(Color.rgb(200, 24, 0));
                targetTiles.add(tileView);
            }

        }
    }

    @Override
    public void visit(AttackState attackState) {

        //If within range make highlight blue.
        //If enemy piece make highlight red.
        highlightDistance = 1;
        List<IPiece> enemyPieces = PieceUtil.getEnemyPieces(gm);
        highlightAttackTiles(enemyPieces);
    }

    @Override
    public void visit(DefenceState defenceState) {

        List<IPiece> ownPieces = gm.getActivePlayer().getPieces();

        //Colour all own pieces green.
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

    public void setHighlightVariables(IBoardGrid boardGrid, IGameManager gm, List<TileView> visited, IPiece selectedPiece) {
        this.boardGrid = boardGrid;
        this.gm = gm;
        this.visited = visited;
        this.selectedPiece = selectedPiece;
    }

    public void setHighlightDistance(int rangedDistance) {
        this.highlightDistance = rangedDistance;
    }

    public List<TileView> getTargetTiles() {
        return targetTiles;
    }

}
