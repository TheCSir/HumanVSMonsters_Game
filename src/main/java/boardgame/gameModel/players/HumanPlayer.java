package boardgame.gameModel.players;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.util.Constants;
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

    @Override
    public void checkAbilityUsed(int turnNumber) {
        if (turnNumber >= super.AbilityTurn + Constants.DEFAULTABILITYCD)
            this.resetIsAbilityUsed();
    }
}
