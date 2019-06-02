package boardgame.gameModel.players;

import java.util.ArrayList;

public class PlayerGroup extends PlayerComponent {
    ArrayList playerList = new ArrayList();

    public PlayerGroup() {}

    public void addPlayer(IPlayerComponent playerComponent) {
        playerList.add(playerComponent);
    }

    public ArrayList<IPlayer> getPlayerGroup() {
        return playerList;
    }
}
