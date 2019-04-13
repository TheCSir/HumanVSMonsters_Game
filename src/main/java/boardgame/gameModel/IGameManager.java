package boardgame.gameModel;

import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface IGameManager {

    ObservableList<IPiece> getHumanPieces();

    void setHumanPieces(ObservableList<IPiece> humanPieces);

    ObservableList<IPiece> getMonsterPieces();

    void setMonsterPieces(ObservableList<IPiece> monsterPieces);

    IBoard setUpBoard(String boardType, int rows, int columns);

    void setUpMonsterPieces();

    void setUpHumanPieces();

    void defaultGameSetup();

    IBoard getiBoard();

    ArrayList<IPlayer> getPlayers();

    void setiBoard(IBoard iBoard);

    Turn getTurn();

    IPlayer getAttackedPlayer(IPiece getiPiece);

    ObservableList<IPiece> getAllPieces();


}
