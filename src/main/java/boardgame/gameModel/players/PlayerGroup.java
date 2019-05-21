package boardgame.gameModel.players;

import java.util.ArrayList;

public class PlayerGroup extends PlayerComponent {
    ArrayList playerList = new ArrayList();

    public PlayerGroup() {}

    public void addPlayer(PlayerComponent playerComponent) {
        playerList.add(playerComponent);
    }

    public void removePlayer(PlayerComponent playerComponent) {
        playerList.remove(playerComponent);
    }

    public PlayerComponent getPlayer(int index) {
        return (PlayerComponent) playerList.get(index);
    }

    public ArrayList<IPlayer> getPlayerGroup() {
        return playerList;
    }
}
