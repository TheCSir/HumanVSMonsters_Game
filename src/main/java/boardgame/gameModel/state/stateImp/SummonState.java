package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;
import boardgame.util.Location;
import boardgame.view.TileView;

import java.util.List;

public class SummonState extends SpecialState {

    @Override
    public void onSelectTile(GameContext gameContext) {

        //Highlighted tiles will only include the target tiles that can be reached.
        //This is set in HighlightTilesVisitor.
        List<TileView> highlightedTiles = gameContext.getHighlightedTiles();
        for (TileView tile : highlightedTiles) {
            Location l = tile.getLocation();
            if (l.equals(gameContext.getTileView().getLocation())) {
                gameContext.launchSpecialAbility();
            }
        }
        gameContext.setState(states.IDLE);
    }

    @Override
    public void accept(HighlightVisitor hv) {
        hv.visit(this);
    }
}
