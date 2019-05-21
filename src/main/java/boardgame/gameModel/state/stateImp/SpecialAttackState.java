package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;
import boardgame.util.Location;
import boardgame.view.TileView;

import java.util.List;

public class SpecialAttackState extends SpecialState {

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        Location enemyPieceLocation = gameContext.getSelectedPiece().getLocation();

        //Highlighted tiles will only include the target tiles that can be reached.
        //This is set in HighlightTilesVisitor.
        List<TileView> highlightedTiles = gameContext.getHighlightedTiles();
        for (TileView tile : highlightedTiles) {
            Location l = tile.getLocation();
            if (l.equals(enemyPieceLocation)) {
                gameContext.launchSpecialAbility();
            }
        }

        gameContext.setState(states.IDLE);
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
