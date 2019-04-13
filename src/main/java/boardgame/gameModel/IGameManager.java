package boardgame.gameModel;

import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * The interface Game manager.
 */
public interface IGameManager {

    /**
     * Gets human pieces.
     *
     * @return the human pieces
     */
    ObservableList<IPiece> getHumanPieces();

    /**
     * Sets human pieces.
     *
     * @param humanPieces the human pieces
     */
    void setHumanPieces(ObservableList<IPiece> humanPieces);

    /**
     * Gets monster pieces.
     *
     * @return the monster pieces
     */
    ObservableList<IPiece> getMonsterPieces();

    /**
     * Sets monster pieces.
     *
     * @param monsterPieces the monster pieces
     */
    void setMonsterPieces(ObservableList<IPiece> monsterPieces);

    /**
     * Sets up board.
     *
     * @param boardType the board type
     * @param rows      the rows
     * @param columns   the columns
     * @return the up board
     */
    IBoard setUpBoard(String boardType, int rows, int columns);

    /**
     * Sets up monster pieces.
     */
    void setUpMonsterPieces();

    /**
     * Sets up human pieces.
     */
    void setUpHumanPieces();

    /**
     * Default game setup.
     */
    void defaultGameSetup();

    /**
     * Gets board.
     *
     * @return the board
     */
    IBoard getiBoard();

    /**
     * Sets board.
     *
     * @param iBoard the board
     */
    void setiBoard(IBoard iBoard);

    /**
     * Gets players.
     *
     * @return the players
     */
    ArrayList<IPlayer> getPlayers();

    /**
     * Gets turn.
     *
     * @return the turn
     */
    Turn getTurn();

    /**
     * Gets attacked player.
     *
     * @param getiPiece the geti piece
     * @return the attacked player
     */
    IPlayer getAttackedPlayer(IPiece getiPiece);

    /**
     * Gets all pieces.
     *
     * @return the all pieces
     */
    ObservableList<IPiece> getAllPieces();
}
