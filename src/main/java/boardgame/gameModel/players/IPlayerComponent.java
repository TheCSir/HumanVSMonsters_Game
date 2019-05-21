package boardgame.gameModel.players;

import java.util.ArrayList;

public interface IPlayerComponent {
    void addPlayer(PlayerComponent playerComponent);

    void removePlayer(PlayerComponent newSongComponent);

    PlayerComponent getPlayer(int componentIndex);

    ArrayList<IPlayer> getPlayerGroup();
}
