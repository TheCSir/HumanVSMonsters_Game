package boardgame.gameModel;

import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.Griffin;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceFactory;
import boardgame.gameModel.pieces.Warrior;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class GameManager implements IGameManager {
    private ArrayList<IPlayer> players;
    private IBoard iBoard;
    private IPiece pieces;
    private Turn turn;
    private Stack<Turn> turnStack;
    private List<IPiece> humanPieces;
    private List<IPiece> monsterPieces;

    //Default constructor
    public GameManager(){

        //Add default players etc.
        defaultGameSetup();

        //Set up default board.
        iBoard = setUpBoard();

        turn = new Turn();
        turn.initialiseTurns(players);
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
    public List<IPiece> setUpMonsterPieces() {
        List<IPiece> monsters = new ArrayList<>();
        IPiece piece = PieceFactory.createPiece(Griffin.class.getName(), 5, LocationFactory.createLocation(0, 0));
        monsters.add(piece);
        return monsters;
    }

    @Override
    public List<IPiece> setUpHumanPieces() {

        ArrayList<IPiece> humans = new ArrayList<>();
        IPiece piece = PieceFactory.createPiece(Warrior.class.getName(), 5, LocationFactory.createLocation(9, 9));
        humans.add(piece);

        return humans;
    }

    @Override
    public void defaultGameSetup(){
        //Add default 3 human pieces
        humanPieces = setUpHumanPieces();

        //Add default 3 monster pieces
        monsterPieces = setUpMonsterPieces();

        players = new ArrayList<>();

        Player player1 = PlayerFactory.createPlayer("HumanPlayer", 1, "Gandalf", 10, humanPieces);
        Player player2 = PlayerFactory.createPlayer("MonsterPlayer", 2, "Sauron", 10, monsterPieces);
        players.add(player1);
        players.add(player2);
        Board2DHex board2DHex = (Board2DHex) setUpBoard();
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
}
