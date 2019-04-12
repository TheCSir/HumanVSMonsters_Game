package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import javafx.collections.ObservableList;

public class HumanPlayer extends Player {

    public HumanPlayer(int playerID, String playerName, int _health, String playerStatus, ObservableList<IPiece> pieces) {
        super(playerID, playerName, _health, playerStatus, pieces);
    }
}
