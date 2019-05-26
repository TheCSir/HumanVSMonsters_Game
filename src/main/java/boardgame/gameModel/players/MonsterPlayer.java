package boardgame.gameModel.players;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceConstants;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class MonsterPlayer extends Player {

    public MonsterPlayer(int playerID, String playerName, int _health, ObservableList<IPiece> pieces, IGameManager gameManager) {
        super(playerID, playerName, _health, pieces, gameManager);
    }

    @Override
    public IPiece getPiece(IPiece piece) {
        return null;
    }

    @Override
    public String playerType() {
        return PieceConstants.MONSTERPLAYER;
    }

    @Override
    public void checkAbilityUsed(int turnNumber) {

    }

    @Override
    public void addPlayer(IPlayerComponent playerComponent) {

    }

    @Override
    public void removePlayer(IPlayerComponent newSongComponent) {

    }

    @Override
    public IPlayerComponent getPlayer(int componentIndex) {
        return null;
    }

    @Override
    public ArrayList<IPlayer> getPlayerGroup() {
        return null;
    }
}
