package boardgame.gameModel.state.stateImp;

import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.HighlightVisitor;
import boardgame.gameModel.state.states;

public class SummonState extends SpecialState {

    @Override
    public void onSelectTile(GameContext gameContext) {

        gameContext.launchSpecialAbility();
        gameContext.setState(states.IDLE);
    }

    @Override
    public void accept(HighlightVisitor hv) {
        hv.visit(this);
    }
}
