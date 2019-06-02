package boardgame.gameModel;

import boardgame.controller.GameController;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.board.BoardFactory;
import boardgame.gameModel.board.IBoard;
import boardgame.gameModel.pieces.AbstractPieceFactory;
import boardgame.gameModel.pieces.FactoryProducer;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.PieceConstants;
import boardgame.gameModel.players.IPlayer;
import boardgame.gameModel.players.IPlayerComponent;
import boardgame.gameModel.players.PlayerFactory;
import boardgame.gameModel.players.PlayerGroup;
import boardgame.gameModel.state.GameContext;
import boardgame.gameModel.state.stateImp.IdleState;
import boardgame.util.Constants;
import boardgame.util.LocationFactory;
import boardgame.view.BoardGridFactory;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.IBoardGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GameManager implements IGameManager {

    private IPlayerComponent allPlayers = new PlayerGroup();

    private IBoard iBoard;
    private Turn turn;

    private ObservableList<IPiece> humanPieces = FXCollections.observableArrayList();
    private ObservableList<IPiece> monsterPieces = FXCollections.observableArrayList();

    private final IBoardGrid IBoardGrid;
    private final GameContext gameContext;
    private final GameController gc;

    //Default constructor
    GameManager(Pane boardPane, GameController gameController) {
        IBoardGrid = BoardGridFactory.createBoardGrid(boardPane, gameController);
        this.gc = gameController;
        gameContext = new GameContext(new IdleState(), IBoardGrid, this, gameController);
    }

    @Override
    public IPlayer getActivePlayer() {
        return turn.getActivePlayer();
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
    public void setUpCustomPieces(String playerType, ObservableList<IPiece> playerPieces,
                                  int numberOfPieces, int gridRows, int gridColumns, String orientation) {
        // Create a list of pieces
        List<String> pieces = new ArrayList<>();
        pieces.add(PieceConstants.MELEE);
        pieces.add(PieceConstants.RANGED);
        pieces.add(PieceConstants.SUPPORT);

        // Randomise list
        Collections.shuffle(pieces);

        int start = 0;
        if (orientation.equals("left")) {
            start = gridRows - 1;
        }

        List<Integer> locationPositions = positions(gridColumns, numberOfPieces);

        for (int i = 0; i < numberOfPieces; i++) {
            // generate random location for each piece on the grid

            AbstractPieceFactory apf = FactoryProducer.getFactory(playerType);
            assert apf != null;
            IPiece ipiece = apf.getPiece(pieces.get(i), LocationFactory.createLocation(locationPositions.get(i), start));
            playerPieces.add(ipiece);
        }
    }

    private List<Integer> positions(int upperBound, int pieceNum) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < upperBound; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        List<Integer> returnNumbers = new ArrayList<>();
        for (int i = 0; i < pieceNum; i++) {
            returnNumbers.add(numbers.get(i));
        }

        return returnNumbers;
    }



    @Override
    public void customGameSetup(String humanPlayerName, String monsterPlayerName,
                                int numberOfPieces, int gridRows, int gridColumns) {

        //Add custom pieces for each player
        setUpCustomPieces(PieceConstants.HUMANPLAYER, humanPieces, numberOfPieces, gridRows, gridColumns, "left");
        setUpCustomPieces(PieceConstants.MONSTERPLAYER, monsterPieces, numberOfPieces, gridRows, gridColumns, "right");

        IPlayer player1 = PlayerFactory.createPlayer(Constants.PLAYER1, 1, humanPlayerName, Constants.INITIALHEALTH, humanPieces, this);
        IPlayer player2 = PlayerFactory.createPlayer(Constants.PLAYER2, 2, monsterPlayerName, Constants.INITIALHEALTH, monsterPieces, this);
        allPlayers.addPlayer(player1);
        allPlayers.addPlayer(player2);

        //Set up custom board.
        iBoard = setUpBoard(Board2DHex.class.getName(), gridRows, gridColumns);

        turn = new Turn();
        turn.initialiseTurns(allPlayers.getPlayerGroup());

        IBoardGrid.drawBasicGrid(new ArrayList<>(getiBoard().getTiles().values()), gridRows, gridColumns, IBoardGrid.getBoardPane());
    }

    @Override
    public IBoard getiBoard() {
        return iBoard;
    }

    @Override
    public ArrayList<IPlayer> getPlayers() {
        return allPlayers.getPlayerGroup();
    }

    @Override
    public Turn getTurn() { return turn; }

    public IPlayer getAttackedPlayer(IPiece attackedPiece){

        for(IPlayer player : allPlayers.getPlayerGroup()){
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
        allpieces.addAll(allPlayers.getPlayerGroup().get(0).getPieces());
        allpieces.addAll(allPlayers.getPlayerGroup().get(1).getPieces());
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
        IBoardGrid.removePiece(piece);
    }

    /**
     * Add a piece to the board in response to a change in the model.
     *
     * @param piece the piece
     */
    @Override
    public void addPiece(IPiece piece) {
        HexagonTileViewPiece hp = IBoardGrid.addPiece(piece);
        hp.setOnMouseClicked(event -> gc.handlePieceClicked(hp));
    }

    @Override
    public GameContext getGameContext() {
        return gameContext;
    }

    @Override
    public void endTurn() {
        getTurn().nextTurn(allPlayers.getPlayerGroup());
    }


    @Override
    public void togglePieceSelectionOn(Boolean isShielded){
        gc.togglePieceSelectionOn(isShielded);
    }

    @Override
    public void toggleMinionSelectionOff() {
        gc.toggleMinionSelectionOff();
    }

    @Override
    public void toggleMinionSelectionOn(String healthText) {
        gc.toggleMinionSelectionOn(healthText);
    }

    @Override
    public void specialAbilityOnCD() {
        gc.specialAbilityOnCD();

    }
    @Override
    public void specialAbilityOffCD() {
        gc.specialAbilityOffCD();
    }
}
