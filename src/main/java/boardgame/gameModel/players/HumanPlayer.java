package boardgame.gameModel.players;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceConstants;
import javafx.collections.ObservableList;

public class HumanPlayer extends Player {

    public HumanPlayer(int playerID, String playerName, int _health, ObservableList<IPiece> pieces, IGameManager gameManager) {
        super(playerID, playerName, _health, pieces, gameManager);
    }

    @Override
    public IPiece getPiece(IPiece piece) {
        return null;
    }


    @Override
    public String playerType() {
        return PieceConstants.HUMANPLAYER;
    }
}