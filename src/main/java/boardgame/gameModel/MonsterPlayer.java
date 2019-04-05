package boardgame.gameModel;

import boardgame.gameModel.pieces.IPiece;

import java.util.List;

public class MonsterPlayer extends Player {

    public MonsterPlayer(int playerID, String playerName, int _health, List<IPiece> pieces){
        super(playerID, playerName, _health, pieces);
    }
}
