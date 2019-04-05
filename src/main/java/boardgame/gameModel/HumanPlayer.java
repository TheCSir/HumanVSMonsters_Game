package boardgame.gameModel;

import boardgame.gameModel.pieces.IPiece;

import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(int playerID, String playerName, int _health, List<IPiece> pieces) {
        super(playerID, playerName, _health, pieces);
    }
}
