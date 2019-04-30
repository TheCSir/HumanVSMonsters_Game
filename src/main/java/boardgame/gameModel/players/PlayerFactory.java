package boardgame.gameModel.players;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.util.Constants;
import javafx.collections.ObservableList;

public class PlayerFactory {

    public static Player createPlayer(String playerType, int playerID, String playerName, int _health, ObservableList<IPiece> pieces, IGameManager gameManager) {
        if (playerType.equals(Constants.PLAYER2)) {
            return new MonsterPlayer(playerID, playerName, _health, pieces, gameManager);
        }else if (playerType.equals(Constants.PLAYER1)){
            return new HumanPlayer(playerID, playerName, _health, pieces, gameManager);
        }
        return null;
    }

}
