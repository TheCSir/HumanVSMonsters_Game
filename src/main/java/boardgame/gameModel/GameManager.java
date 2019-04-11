package boardgame.gameModel;

import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.Griffin;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceFactory;
import boardgame.gameModel.pieces.Warrior;
import boardgame.util.LocationFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Stack;

class GameManager implements IGameManager {
    private ArrayList<IPlayer> players;
    private IBoard iBoard;
    private Turn turn;
    private Stack<Turn> turnStack;

    private ObservableList<IPiece> humanPieces = FXCollections.observableArrayList();
    private ObservableList<IPiece> monsterPieces = FXCollections.observableArrayList();

    //Default constructor
    public GameManager() {
        players = new ArrayList<>();

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
    public IBoard setUpBoard(){
        Board2DHex board2DHex = new Board2DHex();
        board2DHex.setUpTiles(10, 10);
        return board2DHex;
    }

    @Override
    public IBoard setUpBoard(int rows, int columns) {
        Board2DHex board2DHex = new Board2DHex();
        board2DHex.setUpTiles(rows, columns);
        return board2DHex;
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
        //Add default 3 human pieces
        setUpHumanPieces();

        //Add default 3 monster pieces
        setUpMonsterPieces();

        Player player1 = PlayerFactory.createPlayer("HumanPlayer", 1, "Gandalf", 10, humanPieces);
        Player player2 = PlayerFactory.createPlayer("MonsterPlayer", 2, "Sauron", 10, monsterPieces);
        players.add(player1);
        players.add(player2);

        //Set up default board.
        iBoard = setUpBoard();

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
}
