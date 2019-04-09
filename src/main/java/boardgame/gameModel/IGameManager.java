package boardgame.gameModel;

import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.IPiece;

import java.util.ArrayList;
import java.util.List;

public interface IGameManager {
    IBoard setUpBoard();

    IBoard setUpBoard(int rows, int columns);

    List<IPiece> setUpMonsterPieces();

    List<IPiece> setUpHumanPieces();

    void defaultGameSetup();

    IBoard getiBoard();

    ArrayList<IPlayer> getPlayers();

    void setiBoard(IBoard iBoard);

    Turn getTurn();

    IPlayer getAttackedPlayer(IPiece getiPiece);
}
