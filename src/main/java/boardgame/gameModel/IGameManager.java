package boardgame.gameModel;

import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * The interface Game manager. The Game Manager represents an individual game. It holds the
 * Board and Players. The Board class contains the tiles and the Player class contains the pieces.
 * A turn is handled by the Turn class. The Game Manager brings these together. It is abstracted by using
 * this interface so that a different implementation of Game Manager can be provided. As long as the model and
 * view adhere to this interface a different concrete implementation of game manager will work. As we developed
 * we had a little too much responsibility in Game Manager. For instance the pieces were held in this class. Instead that
 * responsibilty was given to the Player class.
 *
 *
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
     * Set up a game with default settings.
     */
    void defaultGameSetup();

    /**
     * The game manager is the information expert for the board.
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


    void setUpGame();

    void removePiece(IPiece piece);

    void addPiece(IPiece piece);
}
