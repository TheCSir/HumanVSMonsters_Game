package boardgame.gameModel;

import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.Griffin;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Warrior;
import boardgame.util.Constants;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameManager {
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

    public IBoard setUpBoard(){
        Board2DHex board2DHex = new Board2DHex();
        board2DHex.setUpTiles(10, 10);
        return board2DHex;
    }

    public IBoard setUpBoard(int rows, int columns) {
        Board2DHex board2DHex = new Board2DHex();
        board2DHex.setUpTiles(rows, columns);
        return board2DHex;
    }

    public List<IPiece> setUpMonsterPieces() {
        List<IPiece> monsters = new ArrayList<>();
        monsters.add(new Griffin(5, new Location(0, 0)));

        return monsters;
    }

    public List<IPiece> setUpHumanPieces() {

        ArrayList<IPiece> humans = new ArrayList<>();
        humans.add(new Warrior(5, new Location(9, 9)));

        return humans;
    }

    private void defaultGameSetup(){
        //Add default 3 human pieces
        humanPieces = setUpHumanPieces();

        //Add default 3 monster pieces
        monsterPieces = setUpMonsterPieces();

        players = new ArrayList<>();
        players.add(new HumanPlayer(1, "Gandalf", 10, humanPieces));
        players.add(new MonsterPlayer(2, "Sauron", 10, monsterPieces));

        Board2DHex board2DHex = (Board2DHex) setUpBoard();
    }

    public IBoard getiBoard() {
        return iBoard;
    }

    public ArrayList<IPlayer> getPlayers() {
        return players;
    }

    public void setiBoard(IBoard iBoard) {
        this.iBoard = iBoard;
    }

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
