package boardgame.gameModel.players;

import java.util.ArrayList;

public abstract class PlayerComponent implements IPlayerComponent {

    // We throw UnsupportedOperationException so that if
    // it doesn't make sense for a Player, or PlayerGroup
    // to inherit a method they can just inherit the
    // default implementation

    public void addPlayer(PlayerComponent playerComponent) {
        throw new UnsupportedOperationException();
    }

    // This allows to remove components
    public void removePlayer(PlayerComponent newSongComponent) {
        throw new UnsupportedOperationException();
    }

    // This allows to get components
    public PlayerComponent getPlayer(int componentIndex) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<IPlayer> getPlayerGroup() {
        throw new UnsupportedOperationException();
    }
}
