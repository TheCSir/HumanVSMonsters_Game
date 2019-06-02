package boardgame.gameModel;

import boardgame.gameModel.players.IPlayer;
import boardgame.util.Constants;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;

import java.util.List;

/**
 * This represents a game turn.
 */
public class Turn {
    private final IntegerProperty turnNumber;
    private final ObjectProperty<IPlayer> activePlayerProperty;

    /**
     * Instantiates a new Turn.
     */
    Turn(){
        this.turnNumber = new SimpleIntegerProperty();
        this.activePlayerProperty = new SimpleObjectProperty<>();
    }

    /**
     * Turn number property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty turnNumberProperty() { return turnNumber; }

    /**
     * Sets turn number property.
     *
     * @param turnNumber the turn number
     */
    public void setTurnNumberProperty(int turnNumber) { this.turnNumber.set(turnNumber); }

    /**
     * Gets turn number.
     *
     * @return the turn number
     */
    public int getTurnNumber() { return turnNumber.getValue(); }

    /**
     * Get active player property object property.
     *
     * @return the object property
     */
    public ObjectProperty<IPlayer> getActivePlayerProperty(){ return this.activePlayerProperty; }

    /**
     * Get the player whose turn it is.
     *
     * @return the player
     */
    public IPlayer getActivePlayer(){
        return this.activePlayerProperty.get();
    }

    /**
     * Sets player whose turn it is as the active player.
     *
     * @param activePlayer the active player IPlayer
     */
    public void setActivePlayer(IPlayer activePlayer) {
        this.activePlayerProperty.set(activePlayer);
    }

    /**
     * Initialise turns.
     *
     * @param players the players
     */
    public void initialiseTurns(List<IPlayer> players){
        int firstTurn = 1;
        this.setTurnNumberProperty(firstTurn);

        int firstPlayerIndex = this.getTurnNumber() % players.size();

        setActivePlayer(players.get(firstPlayerIndex));
    }

    /**
     * Start the next turn.
     *
     * @param players list of players in the game.
     */
    public void nextTurn(List<IPlayer> players){

        int nextTurn = this.getTurnNumber() + 1;
        this.setTurnNumberProperty(nextTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = this.getTurnNumber() % players.size();

        checkWin(players);
        setActivePlayer(players.get(nextPlayerIndex));
    }

    /**
     * Check if a player has won. Called at the end of a turn.
     *
     * @param players List of IPlayers
     */
    public void checkWin(List<IPlayer> players) {

        boolean isOver = false;
        int winner = 0;

        if (players.get(0).healthProperty().getValue() <= 0) {

            isOver = true;
            winner = 1;

        } else if (players.get(1).healthProperty().getValue() <= 0) {
            isOver = true;
            winner = 0;
        } else if (turnNumber.get() > Constants.MAXTURNS) {
            isOver = true;
        }

        if (isOver) {

            System.out.println("Game Ended");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Ended");
            alert.setHeaderText("congratulations ! Player " + players.get(winner).getPlayerName() + " Won");
            alert.showAndWait();
        }
    }
}
