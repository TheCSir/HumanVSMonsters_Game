package boardgame.gameModel.players;

import java.util.ArrayList;

public abstract class PlayerComponent {

    // We throw UnsupportedOperationException so that if
    // it doesn't make sense for a Player, or PlayerGroup
    // to inherit a method they can just inherit the
    // default implementation

    public void addPlayer(PlayerComponent playerComponent) {

        throw new UnsupportedOperationException();
    }

    // This allows me to remove components
    public void removePlayer(PlayerComponent newSongComponent) {
        throw new UnsupportedOperationException();
    }

    // This allows me to get components
    public PlayerComponent getPlayer(int componentIndex) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Player> getPlayerGroup() {
        throw new UnsupportedOperationException();
    }
}
