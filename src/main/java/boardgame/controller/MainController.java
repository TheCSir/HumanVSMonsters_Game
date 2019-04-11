package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManagerFactory;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.IPlayer;
import boardgame.gameModel.Turn;
import boardgame.gameModel.board.Board2DHex;
import boardgame.gameModel.pieces.*;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Location;
import boardgame.util.LocationFactory;
import boardgame.view.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static boardgame.util.Constants.TILERADIUS;

public class MainController implements Initializable {

    @FXML // fx:id="turnTime"
    private Text turnTime; // Value injected by FXMLLoader

    @FXML
    private Pane boardPane;

    @FXML
    private VBox tileInfoPane;

    @FXML
    private Text currentPlayer;

    @FXML
    private Text pieceSelected;

    @FXML
    private Button moveButton;

    @FXML
    private Button attackButton;

    @FXML
    private Text pieceHealth;

    @FXML
    private Text pieceLocation;

    @FXML
    private Label turnNumber;

    @FXML
    private Label humanHealth;

    @FXML
    private Label monsterHealth;

    @FXML
    private Button debugAddPiece;

    @FXML
    private Button swapButton;

    @FXML
    private Pane SwapPane;

    @FXML
    private Button Opt_one;

    @FXML
    private Button Opt_two;

    private enum State{
        MOVE,
        ATTACK,
        SPECIAL_ABILITY,
        DEFENSE,
        SWAP,
        NONE
    }

    private State currentState = State.NONE;

    //Store a reference to the Game manager for main entry point to game.
    private IGameManager gm;

    private Board2DHex board2DHex;

    private BoardGrid boardGrid;

    private HexagonTileViewPiece selectedTilePiece = null;

    private boolean tileSelected = false;

    private HexagonTileView targetTile = null;

    private HexagonTileViewPiece targetTilePiece = null;

    private IPlayer activePlayer = null;

    public static final String MOVE = "move";
    private String state = null;

    private String PieceSelectionOne;
    private String PieceSelectionTwo;


    public MainController() {
        //Get a reference to the game manager. Currently sets up a game with default settings.
        gm = GameManagerFactory.createGameManager();

        gm.defaultGameSetup();

        board2DHex = (Board2DHex) gm.getiBoard();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Map<Location, ITile> board = board2DHex.getTiles();
        List<ITile> boardTiles = new ArrayList<>(board.values());

        boardGrid = new BoardGrid();
        boardGrid.drawBasicGrid(boardTiles, TILERADIUS, boardPane);
        assertJFXInjection();


        // register piece actions
        moveButton.setOnMouseClicked(e -> handleMoveClicked());
        attackButton.setOnAction(e -> chooseAttackTargetPiece());
        swapButton.setOnAction(e-> handleSwapAction());
        Opt_one.setOnAction(e-> doSwap(PieceSelectionOne));
        Opt_two.setOnAction(e-> doSwap(PieceSelectionTwo));

//        addPieces(pieces, boardPane);

        registerTileListenersForMove(boardGrid.getHexagonTileViews());
        registerPlayerListeners(gm.getPlayers());
        registerTurnListeners(gm.getTurn());

        initialiseTextFields();
        initialiseBoardBackGround();

        addPieces(gm.getAllPieces());
        registerPieceListListener();

//        IPiece medusa = PieceFactory.createPiece(Medusa.class.getName(), 5, LocationFactory.createLocation(3, 3));
//        IPiece archer = PieceFactory.createPiece(Archer.class.getName(), 5, LocationFactory.createLocation(7, 7));
//        gm.getPlayers().get(0).getPieces().add(archer);
//        gm.getPlayers().get(1).getPieces().add(medusa);
//        gm.getPlayers().get(1).getPieces().remove(medusa);

    }

    private void initialiseBoardBackGround() {
        // Set up the background.
        try {
            FileInputStream input = new FileInputStream("src/main/resources/wood_table_background.jpeg");
            boardPane.setBackground(new Background(new BackgroundImage(new Image(input), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
        } catch (FileNotFoundException e) {
            System.out.println("what");
        }
    }

    private void initialiseTextFields() {

        currentPlayer.setText("Current Player: " + gm.getTurn().getActivePlayer().getPlayerName());
        turnNumber.setText("Turn: " +
                gm.getTurn().getTurnNumber());


        humanHealth.setText("Gandalf Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        monsterHealth.setText("Sauron Health: " +
                gm.getTurn().getActivePlayer().healthProperty().getValue());

        turnTime.setText("Turn Time " + 60);
    }

    private void assertJFXInjection() {
        //Assertions to ensure that injection works
        assert currentPlayer != null : "fx:id=\"currentPlayer\" was not injected: check your FXML file 'mainView.fxml'.";
        assert turnTime != null : "fx:id=\"turnTime\" was not injected: check your FXML file 'mainView.fxml'.";
        assert boardPane != null : "fx:id=\"boardPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert tileInfoPane != null : "fx:id=\"tileInfoPane\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceSelected != null : "fx:id=\"pieceSelected\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceHealth != null : "fx:id=\"pieceHealth\" was not injected: check your FXML file 'mainView.fxml'.";
        assert pieceLocation != null : "fx:id=\"pieceLocation\" was not injected: check your FXML file 'mainView.fxml'.";
    }

    private void chooseAttackTargetPiece() {
        currentState = State.ATTACK;

        if(selectedTilePiece != null)
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.ANTIQUEWHITE);
    }

    private void chooseAbilityTargetPiece() {
        currentState = State.SPECIAL_ABILITY;
    }

    private void chooseDefenseTargetPiece() {
        currentState = State.DEFENSE;
    }

    private void chooseSwapTargetPiece() {
        currentState = State.SWAP;
    }

    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.

    private void registerTileListenersForMove(List<HexagonTileView> boardTiles) {

        for (HexagonTileView hexagonalTile : boardTiles) {

            //Set tile handlers
            hexagonalTile.setOnMouseClicked(e -> handleTileClicked(hexagonalTile));
        }
    }

    //Register listeners for the board pieces.
    private void registerPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            registerPieceListener(piece);
        }
    }

    private void registerPlayerListeners(List<IPlayer> players) {

        for (IPlayer player : players) {
            PlayerView playerView = new PlayerView();
            player.healthProperty().addListener((observable) ->
                    playerView.decreaseHealthBar(player, targetTilePiece));

            if(player.getClass().getSimpleName().equals("HumanPlayer")) {
                player.healthProperty().addListener((observable) ->
                        humanHealth.setText("Gandalf Health: " +
                                player.healthProperty().getValue())
                );
            }

            else if(player.getClass().getSimpleName().equals("MonsterPlayer")) {
                player.healthProperty().addListener((observable) ->
                        monsterHealth.setText("Sauron Health: " +
                                player.healthProperty().getValue())
                );
            }
        }
    }

    private void registerPieceListListener() {
        for (IPlayer iPlayer : gm.getPlayers()) {
            ObservableList<IPiece> pieces = iPlayer.getPieces();
            pieces.addListener((ListChangeListener<IPiece>) c -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (IPiece piece : c.getAddedSubList()) {
                            addPiece(piece);
                        }
                    } else if (c.wasRemoved()) {
                        c.getRemoved();
                        for (IPiece piece : c.getRemoved()) {
                            removePiece(piece);
                        }
                    }

                    //TODO debugging - remove
                    if (c.wasAdded()) {
                        System.out.println(c.getAddedSubList().get(0)
                                + " was added to the list!");
                    } else if (c.wasRemoved()) {
                        System.out.println(c.getRemoved().get(0)
                                + " was removed from the list!");
                    }
                }
            });
        }

    }

    private void removePiece(IPiece piece) {
        boardGrid.removePiece(piece, boardPane);
    }

    private void registerTurnListeners(Turn turn){
        // increment Turn number label
        turn.turnNumberProperty().addListener(observable ->
                turnNumber.setText("Turn: " +
                        turn.getTurnNumber())
        );

        // Change Current Player label
        gm.getTurn().getActivePlayerProperty().addListener(observable ->
                currentPlayer.setText("Current Player: " + turn.getActivePlayer().getPlayerName()));

        // reset currentState
        turn.turnNumberProperty().addListener(observable ->
                currentState = State.NONE
        );
    }

    private void unRegisterPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            PieceView pieceView = new PieceView();
            piece.locationPropertyProperty().removeListener((observable) ->
                    pieceView.changePiecePosition(selectedTilePiece, targetTile));
        }
    }

    //Add game pieces to the game board.

    private void addPieces(List<IPiece> pieceList) {

        for (IPiece piece : pieceList) {
            addPiece(piece);
        }
    }

    private void addPiece(IPiece piece) {
        //Register move listener
        registerPieceListener(piece);

        boardGrid.addPiece(piece, boardPane);

        //set view piece handler.
        for (HexagonTileViewPiece hexagonTileViewPiece : boardGrid.getPieceObservableList()) {
            if (hexagonTileViewPiece.getiPiece().equals(piece)) {
                hexagonTileViewPiece.setOnMouseClicked(event -> handlePieceClicked(hexagonTileViewPiece));
            }
        }

    }

    private void registerPieceListener(IPiece piece) {
        PieceView pieceView = new PieceView();
        piece.locationPropertyProperty().addListener((observable) ->
                pieceView.changePiecePosition(selectedTilePiece, targetTile));
    }

    //Selects piece.
    private void handlePieceClicked(HexagonTileViewPiece piece) {
        // Reset tiles color
        if(selectedTilePiece != null)
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.ANTIQUEWHITE);

        this.selectedTilePiece = piece;
        this.tileSelected = true;

        pieceSelected.setText("Class: " + piece.getiPiece().getClass().getSimpleName());
        pieceLocation.setText("Location: "
                + "X: " + piece.getiPiece().getLocation().getX()
                + ", "
                + "Y: " + piece.getiPiece().getLocation().getY());


        switch (currentState){
            case MOVE:
                if(isActivePlayerPiece())
                    boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.RED);
                break;
            case ATTACK:
                if(!isActivePlayerPiece()) {
                    // get attacked player
                    IPlayer attackedPLayer = gm.getAttackedPlayer(selectedTilePiece.getiPiece());

                    attackedPLayer.decreaseHealthProperty();

                    // end turn
                    gm.getTurn().nextTurn(gm.getPlayers());
                }
                break;
            case SPECIAL_ABILITY:
                break;
            case DEFENSE:
                break;
            case SWAP:
                break;
        }
    }


    //Gets input and updates model for piece position.
    private void handleTileClicked(HexagonTileView tile) {
        assert tile != null;
        targetTile = tile;

        if (selectedTilePiece != null && tileSelected && isActivePlayerPiece() && currentState.equals(State.MOVE)) {
            // Reset tiles color
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.ANTIQUEWHITE);

            //Update model.
            boolean pieceMoved = gm.getiBoard().movePiece(selectedTilePiece.getiPiece(), tile.getLocation());

            if(pieceMoved) {
                // end turn
                gm.getTurn().nextTurn(gm.getPlayers());
            }
        }
    }

    private void handleMoveClicked() {
        currentState = State.MOVE;
        if (selectedTilePiece != null && tileSelected && isActivePlayerPiece()) {
            boardGrid.setNeighbourTilesColor(selectedTilePiece, Color.RED);
        }
    }

    // Checks if selected piece belongs to the active player
    private boolean isActivePlayerPiece(){
        if(selectedTilePiece == null)
            return false;

        for(IPiece piece : gm.getTurn().getActivePlayer().getPieces()) {
            if(piece.getClass().getSimpleName().equals(selectedTilePiece.getiPiece().getClass().getSimpleName())) {
                return true;
            }
        }

        return false;
    }

    private void handleSwapAction() {

        //Switch the disabled status
        SwapPane.setVisible(!SwapPane.isVisible());

        //get current piece class
        String oldPieceName = gm.getTurn().getActivePlayer().getPieces().get(0).getClass().getName();

        // Button label store location;
        String OptOne="";
        String OptTwo="";


        // Check and populate Gui according to current situation
        if (oldPieceName.equals(Warrior.class.getName())) {
            OptOne = Archer.class.getSimpleName();
            PieceSelectionOne = Archer.class.getName();
            OptTwo = Priest.class.getSimpleName();
            PieceSelectionTwo = Priest.class.getName();

        }else if (oldPieceName.equals(Priest.class.getName())) {
            OptOne = Warrior.class.getSimpleName();
            PieceSelectionOne = Warrior.class.getName();
            OptTwo = Archer.class.getSimpleName();
            PieceSelectionTwo = Archer.class.getName();

        }else if (oldPieceName.equals(Archer.class.getName())) {
            OptOne = Warrior.class.getSimpleName();
            PieceSelectionOne = Warrior.class.getName();
            OptTwo = Priest.class.getSimpleName();
            PieceSelectionTwo = Priest.class.getName();

        }else if (oldPieceName.equals(Medusa.class.getName())) {
            OptOne = Griffin.class.getSimpleName();
            PieceSelectionOne = Griffin.class.getName();
            OptTwo = Minotaur.class.getSimpleName();
            PieceSelectionTwo = Minotaur.class.getName();

        }else if (oldPieceName.equals(Griffin.class.getName())) {
            OptOne = Medusa.class.getSimpleName();
            PieceSelectionOne = Medusa.class.getName();
            OptTwo = Minotaur.class.getSimpleName();
            PieceSelectionTwo = Minotaur.class.getName();

        }else if (oldPieceName.equals(Minotaur.class.getName())) {
            OptOne = Griffin.class.getSimpleName();
            PieceSelectionOne = Griffin.class.getName();
            OptTwo = Medusa.class.getSimpleName();
            PieceSelectionTwo = Medusa.class.getName();

        }

        // Set button labels
        Opt_one.setText(OptOne);
        Opt_two.setText(OptTwo);

    }

    private void doSwap(String Piece) {

        //Get current piece and it's location
        IPiece oldPiece = gm.getTurn().getActivePlayer().getPieces().get(0);
        int x = oldPiece.getLocation().getX();
        int y = oldPiece.getLocation().getY();

        //Remove current piece
        gm.getTurn().getActivePlayer().getPieces().remove(oldPiece);

        //Create new piece and add to board
        IPiece newPiece= PieceFactory.createPiece(Piece, 5, LocationFactory.createLocation(x,y));
        gm.getTurn().getActivePlayer().getPieces().add(newPiece);


        //Handle GUI validations
        SwapPane.setVisible(false);

        //End turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

}
