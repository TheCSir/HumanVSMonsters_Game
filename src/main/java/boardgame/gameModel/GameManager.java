package boardgame.gameModel;

import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.board.BoardFactory;
import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.Griffin;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceFactory;
import boardgame.gameModel.pieces.Warrior;
import boardgame.gameModel.players.IPlayer;
import boardgame.gameModel.players.PlayerFactory;
import boardgame.util.Constants;
import boardgame.util.LocationFactory;
import boardgame.view.BoardGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

class GameManager implements IGameManager {
    private ArrayList<IPlayer> players;
    private IBoard iBoard;
    private Turn turn;

    private ObservableList<IPiece> humanPieces = FXCollections.observableArrayList();
    private ObservableList<IPiece> monsterPieces = FXCollections.observableArrayList();

    private BoardGrid boardGrid;

    //Default constructor
    GameManager(BoardGrid boardGrid) {
        players = new ArrayList<>();
        this.boardGrid = boardGrid;
    }

    @Override
    public ObservableList<IPiece> getHumanPieces() {
        return humanPieces;
    }

    @Override
    public void setHumanPieces(ObservableList<IPiece> humanPieces) {
        this.humanPieces = humanPieces;
    }

    @Override
    public ObservableList<IPiece> getMonsterPieces() {
        return monsterPieces;
    }

    @Override
    public void setMonsterPieces(ObservableList<IPiece> monsterPieces) {
        this.monsterPieces = monsterPieces;
    }

    @Override
    public IBoard setUpBoard(String boardType, int rows, int columns) {
        return BoardFactory.createBoard(boardType, rows, columns);
    }

    @Override
    public void setUpMonsterPieces() {

        IPiece piece = PieceFactory.createPiece(Griffin.class.getName(), 5, LocationFactory.createLocation(0, 0));
        monsterPieces.add(piece);
    }

    @Override
    public void setUpHumanPieces() {

        IPiece piece = PieceFactory.createPiece(Warrior.class.getName(), 5, LocationFactory.createLocation(9, 9));
        humanPieces.add(piece);

    }

    @Override
    public void defaultGameSetup(){
        //Add default Human piece
        setUpHumanPieces();

        //Add default Monster piece
        setUpMonsterPieces();

        IPlayer player1 = PlayerFactory.createPlayer(Constants.PLAYER1, 1, Constants.PLAYERNAME1, Constants.INITIALHEALTH, humanPieces, this);
        IPlayer player2 = PlayerFactory.createPlayer(Constants.PLAYER2, 2, Constants.PLAYERNAME2, Constants.INITIALHEALTH, monsterPieces, this);
        players.add(player1);
        players.add(player2);

        //Set up default board.
        iBoard = setUpBoard(Board2DHex.class.getName(), Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS);

        turn = new Turn();
        turn.initialiseTurns(players);

    }

    @Override
    public IBoard getiBoard() {
        return iBoard;
    }

    @Override
    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public void setiBoard(IBoard iBoard) {
        this.iBoard = iBoard;
    }

    @Override
    public Turn getTurn() { return turn; }

    public IPlayer getAttackedPlayer(IPiece attackedPiece){


        for(IPlayer player : players){
            for(IPiece playerPiece : player.getPieces()){
                if(playerPiece.getClass().getSimpleName().equals(attackedPiece.getClass().getSimpleName()))
                    return player;
            }
        }

        return null;
    }

    @Override
    public ObservableList<IPiece> getAllPieces() {
        ObservableList<IPiece> allpieces = FXCollections.observableArrayList();
        allpieces.addAll(players.get(0).getPieces());
        allpieces.addAll(players.get(1).getPieces());
        return allpieces;
    }


    @Override
    public void setUpGame() {
        addPieces(getAllPieces());
    }

    //Add game pieces to the game board.
    private void addPieces(List<IPiece> pieceList) {
        for (IPiece piece : pieceList) {
            addPiece(piece);
        }
    }


    /**
     * Remove a piece from the view.
     *
     * @param piece the piece
     */
    @Override
    public void removePiece(IPiece piece) {
        boardGrid.removePiece(piece);
    }


    /**
     * Add a piece to the board in response to a change in the model.
     *
     * @param piece the piece
     */
    @Override
    public void addPiece(IPiece piece) {
        boardGrid.addPiece(piece);
    }
}
