package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Constants;
import javafx.collections.ObservableList;

public class PlayerFactory {

    public static Player createPlayer(String playerType, int playerID, String playerName, int _health, String playerStatus, ObservableList<IPiece> pieces) {
        if (playerType.equals(Constants.PLAYER1)) {
            return new MonsterPlayer(playerID, playerName, _health, playerStatus, pieces);
        }else if (playerType.equals(Constants.PLAYER2)){
            return new HumanPlayer(playerID, playerName, _health, playerStatus, pieces);
        }
        return null;
    }

}
