package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.gameModel.players.Player;
import boardgame.util.Constants;
import javafx.collections.ObservableList;

import java.util.List;

public class RegisterListeners {

    private final IGameManager gm;
    private final StatusController statusController;

    public RegisterListeners(IGameManager gm, StatusController tc) {
        this.gm = gm;
        this.statusController = tc;
    }


    public void registerPlayerListeners(List<IPlayer> players) {

        for (IPlayer player : players) {

            if (player.getClass().getSimpleName().equals(Constants.PLAYER1)) {
                player.healthProperty().addListener((observable) ->
                        statusController.getHumanHealth().setText(gm.getPlayers().get(0).getPlayerName() +
                                " Health: " + player.healthProperty().getValue())
                );
            } else if (player.getClass().getSimpleName().equals(Constants.PLAYER2)) {
                player.healthProperty().addListener((observable) ->
                        statusController.getMonsterHealth().setText(gm.getPlayers().get(1).getPlayerName() +
                                " Health: " + player.healthProperty().getValue())
                );
            }
        }
    }

    void registerTurnListeners(Turn turn) {
        // increment Turn number label

        turn.turnNumberProperty().addListener(observable ->
                statusController.setTurnNumber("Turn: " +
                        turn.getTurnNumber())
        );

        // Change Current Player label
        gm.getTurn().getActivePlayerProperty().addListener(observable ->
                statusController.getCurrentPlayer().setText("Current Player: " + turn.getActivePlayer().getPlayerName()));

        for (IPlayer iPlayer : gm.getPlayers()) {
            ObservableList<IPiece> pieces = iPlayer.getPieces();

            for (IPiece piece : pieces) {
                turn.turnNumberProperty().addListener(observable ->
                        piece.checkShieldTurn(turn.getTurnNumber())
                );
            }

        }
    }
}
