package boardgame.gameModel;

import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.*;
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
    private String[] playerArray = {Constants.PLAYER1, Constants.PLAYER2};
    private StringProperty activePlayerLabel;

    //Default constructor
    public GameManager(){

        //Add default players etc.
        defaultGameSetup();

        //Set up default board.
        iBoard = setUpBoard();

        turn = new Turn();
        turn.initialiseTurns(players);

        activePlayerLabel = new SimpleStringProperty(playerArray[0]);
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
        monsters.add(new Griffin(5, new Location(3, 3)));
        monsters.add(new Medusa(5, new Location(2, 2)));
        monsters.add(new Minotaur(5, new Location(0, 0)));
        return monsters;
    }

    public List<IPiece> setUpHumanPieces() {

        ArrayList<IPiece> humans = new ArrayList<>();
        humans.add(new Warrior(5, new Location(5, 5)));
        humans.add(new Priest(5, new Location(6, 6)));
        humans.add(new Archer(5, new Location(7, 7)));

        return humans;
    }

    private void defaultGameSetup(){
        //Add default 3 human pieces
        humanPieces = setUpHumanPieces();

        //Add default 3 monster pieces
        monsterPieces = setUpMonsterPieces();

        //make sure to assign player id in this order
        //means human player id should always be '1'
        players = new ArrayList<>();
        players.add(new HumanPlayer(1, "Gandalf", 10, humanPieces,0));
        players.add(new MonsterPlayer(2, "Sauron", 10, monsterPieces,0));

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

    //Temporary solution for testing of changing player. Not intended for submission. change to model.
    public void changeActivePlayer() {
        if (activePlayerLabel.get().equals(playerArray[0])){
            activePlayerLabel.set(playerArray[1]);
            turn.nextTurn(players);
        }else {
            activePlayerLabel.set(playerArray[0]);
            turn.nextTurn(players);
        }
    }

    //Temp swap piece
    public void swapPiece () {

        int piece = turn.getActivePlayer().getActivePiece();

        if (piece<Constants.TypesOfPicees-1)
            turn.getActivePlayer().setActivePiece(piece+1);
        else
            turn.getActivePlayer().setActivePiece(0);


        activePlayerLabel.set(turn.getActivePlayer().getPieces().get(piece).getClass().getSimpleName());


        //activePlayerLabel.set(monsterPieces.get(0).getClass().getSimpleName());
    }

    public String getCurrentPlayer() {
        return activePlayerLabel.get();
    }

    public StringProperty playerProperty() {
        return activePlayerLabel;
    }

    public Turn getTurn() { return turn; }
}
