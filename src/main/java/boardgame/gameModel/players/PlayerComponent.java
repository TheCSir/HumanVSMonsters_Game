package boardgame.gameModel.players;

import java.util.ArrayList;

public abstract class PlayerComponent implements IPlayerComponent {

    // We throw UnsupportedOperationException so that if
    // it doesn't make sense for a Player, or PlayerGroup
    // to inherit a method they can just inherit the
    // default implementation

    public void addPlayer(IPlayerComponent playerComponent) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<IPlayer> getPlayerGroup() {
        throw new UnsupportedOperationException();
    }
}
