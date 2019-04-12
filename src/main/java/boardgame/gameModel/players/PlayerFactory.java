package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import javafx.collections.ObservableList;

public class PlayerFactory {

    public static Player createPlayer(String playerType, int playerID, String playerName, int _health, String playerStatus, ObservableList<IPiece> pieces) {
        if (playerType.equals("MonsterPlayer")) {
            return new MonsterPlayer(playerID, playerName, _health, playerStatus, pieces);
        }else if (playerType.equals("HumanPlayer")){
            return new HumanPlayer(playerID, playerName, _health, playerStatus, pieces);
        }
        return null;
    }

}
