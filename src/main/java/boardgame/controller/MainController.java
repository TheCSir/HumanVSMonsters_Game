package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.gameModel.GameManagerFactory;
import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.*;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.LocationFactory;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.PieceView;
import boardgame.view.TileView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static boardgame.util.Constants.TILERADIUS;

/**
 * The type Main controller.
 */
public class MainController implements Initializable {

    @FXML // fx:id="turnTime"
    private Text turnTime; // Value injected by FXMLLoader

    @FXML
    private Pane boardPane;

    @FXML
    private VBox tileInfoPane;

    private RegisterListeners registerListeners;

    @FXML
    private Text currentPlayer;

    @FXML
    private Text pieceSelected;

    @FXML
    private Button moveButton;

    @FXML
    private Button attackButton;

    @FXML
    private Button defendButton;

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
    private GameController gameController;

    private State currentState = State.NONE;

    //Store a reference to the Game manager for main entry point to game.
    private IGameManager gm;

    private BoardGrid boardGrid;

    /**
     * This is the main entry point after the App class is started. The MainController holds handler methods
     * for input actions. It also registers the listeners for the model pieces. As our application follows
     * an observer pattern these listeners will update the view when triggered.
     * The handle methods call the model through the gameManager interface when responding to user input as per the MVC
     * pattern.
     * The MainController class is the main part of application that currently requires major refactoring as
     * it has a bit too much responsibilty. Whilst it is reasonably cohesive it is highly coupled.
     */
    public MainController() {
        //Get a reference to the game manager. Currently sets up a game with default settings.
        gm = GameManagerFactory.createGameManager();

        gm.defaultGameSetup();

    }

    public Text getCurrentPlayer() {
        return currentPlayer;
    }

    public HexagonTileViewPiece getSelectedTilePiece() {
        return selectedTilePiece;
    }

    public void setSelectedTilePiece(HexagonTileViewPiece selectedTilePiece) {
        this.selectedTilePiece = selectedTilePiece;
    }

    public TileView getTargetTile() {
        return targetTile;
    }

    public void setTargetTile(TileView targetTile) {
        this.targetTile = targetTile;
    }

    private HexagonTileViewPiece selectedTilePiece = null;

    private boolean tileSelected = false;

    private TileView targetTile = null;

    private HexagonTileViewPiece targetTilePiece = null;

    public HexagonTileViewPiece getTargetTilePiece() {
        return targetTilePiece;
    }

    public void setTargetTilePiece(HexagonTileViewPiece targetTilePiece) {
        this.targetTilePiece = targetTilePiece;
    }

    public RegisterListeners getRegisterListeners() {
        return registerListeners;
    }

    private String PieceSelectionOne;
    private String PieceSelectionTwo;

    public void setRegisterListeners(RegisterListeners registerListeners) {
        this.registerListeners = registerListeners;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardGrid = new BoardGrid(boardPane);
        registerListeners = new RegisterListeners(this, gm);
        boardGrid.drawBasicGrid(new ArrayList<>(gm.getiBoard().getTiles().values()), TILERADIUS, boardPane);
        assertJFXInjection();

        // register piece actions
        moveButton.setOnMouseClicked(e -> handleMoveClicked());
        attackButton.setOnAction(e -> chooseAttackTargetPiece());
        swapButton.setOnAction(e -> handleSwapAction());
        Opt_one.setOnAction(e -> doSwap(PieceSelectionOne));
        Opt_two.setOnAction(e -> doSwap(PieceSelectionTwo));
        //defense code
        defendButton.setOnAction(e -> chooseDefenseTargetPiece());
        //end

        initialiseTextFields();

        registerListeners.registerTileListenersForMove(boardGrid.getHexagonTileViews());
        registerListeners.registerPlayerListeners(gm.getPlayers(), targetTilePiece, humanHealth, monsterHealth);
        registerListeners.registerTurnListeners(gm.getTurn());

        gameController = new GameController(gm, boardGrid, this);
        gameController.setUpGame();

        registerListeners.registerPieceListListener();


    }

    public Label getTurnNumber() {
        return turnNumber;
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


//    //Register listeners for the board pieces.
//    private void registerPieceListeners(List<IPiece> pieces) {
//
//        for (IPiece piece : pieces) {
//            registerPieceListener(piece);
//        }
//    }

    public void setTurnNumber(Label turnNumber) {
        this.turnNumber = turnNumber;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    //Selects piece.
    public void handlePieceClicked(HexagonTileViewPiece piece) {
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
                if (isActivePlayerPiece()) {
                    // get defense player
                    IPlayer defensePlayer = gm.getAttackedPlayer(selectedTilePiece.getiPiece());

                    defensePlayer.createShield();
                    // end turn
                    gm.getTurn().nextTurn(gm.getPlayers());
                }
                break;
            case SWAP:
                break;
        }
    }

    private void unRegisterPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            piece.locationPropertyProperty().removeListener((observable) ->
                    PieceView.changePiecePosition(selectedTilePiece, targetTile));
        }
    }

    //Gets input and updates model for piece position.
    public void handleTileClicked(TileView tile) {
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

    public enum State {
        /**
         * Move state.
         */
        MOVE,
        /**
         * Attack state.
         */
        ATTACK,
        /**
         * Special ability state.
         */
        SPECIAL_ABILITY,
        /**
         * Defense state.
         */
        DEFENSE,
        /**
         * Swap state.
         */
        SWAP,
        /**
         * None state.
         */
        NONE
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
        String OptOne = "";
        String OptTwo = "";


        // Check and populate Gui according to current situation
        if (oldPieceName.equals(Warrior.class.getName())) {
            OptOne = Archer.class.getSimpleName();
            PieceSelectionOne = Archer.class.getName();
            OptTwo = Priest.class.getSimpleName();
            PieceSelectionTwo = Priest.class.getName();

        } else if (oldPieceName.equals(Priest.class.getName())) {
            OptOne = Warrior.class.getSimpleName();
            PieceSelectionOne = Warrior.class.getName();
            OptTwo = Archer.class.getSimpleName();
            PieceSelectionTwo = Archer.class.getName();

        } else if (oldPieceName.equals(Archer.class.getName())) {
            OptOne = Warrior.class.getSimpleName();
            PieceSelectionOne = Warrior.class.getName();
            OptTwo = Priest.class.getSimpleName();
            PieceSelectionTwo = Priest.class.getName();

        } else if (oldPieceName.equals(Medusa.class.getName())) {
            OptOne = Griffin.class.getSimpleName();
            PieceSelectionOne = Griffin.class.getName();
            OptTwo = Minotaur.class.getSimpleName();
            PieceSelectionTwo = Minotaur.class.getName();

        } else if (oldPieceName.equals(Griffin.class.getName())) {
            OptOne = Medusa.class.getSimpleName();
            PieceSelectionOne = Medusa.class.getName();
            OptTwo = Minotaur.class.getSimpleName();
            PieceSelectionTwo = Minotaur.class.getName();

        } else if (oldPieceName.equals(Minotaur.class.getName())) {
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
        IPiece newPiece = PieceFactory.createPiece(Piece, 5, LocationFactory.createLocation(x, y));
        gm.getTurn().getActivePlayer().getPieces().add(newPiece);


        //Handle GUI validations
        SwapPane.setVisible(false);

        //End turn
        gm.getTurn().nextTurn(gm.getPlayers());
    }

}
