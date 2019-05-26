package boardgame.gameModel.players;

import java.util.ArrayList;

public class PlayerGroup extends PlayerComponent {
    ArrayList playerList = new ArrayList();

    public PlayerGroup() {}

    public void addPlayer(IPlayerComponent playerComponent) {
        playerList.add(playerComponent);
    }

    public void removePlayer(IPlayerComponent playerComponent) {
        playerList.remove(playerComponent);
    }

    public IPlayerComponent getPlayer(int index) {
        return (IPlayerComponent) playerList.get(index);
    }

    public ArrayList<IPlayer> getPlayerGroup() {
        return playerList;
    }
}
