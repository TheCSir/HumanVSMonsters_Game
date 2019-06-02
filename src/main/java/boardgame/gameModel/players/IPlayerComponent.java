package boardgame.gameModel.players;
/**
 * The IPlayerComponent showcases the Composite Pattern. Its job is to describe a group of Players through the use of
 * the composite class PlayerGroup, and use the compositions of these objects the same way as we would for individual
 * Player objects. This allows the Client to have a tree structure of Players, but still deal with each node in the
 * tree uniformly.
 */

import java.util.ArrayList;

public interface IPlayerComponent {
    void addPlayer(IPlayerComponent playerComponent);

    ArrayList<IPlayer> getPlayerGroup();
}
