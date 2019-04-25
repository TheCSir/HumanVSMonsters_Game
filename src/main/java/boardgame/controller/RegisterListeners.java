package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.Constants;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

public class RegisterListeners {

    private final MainController mainController;
    private IGameManager gm;
    private StatusController statusController;

    public RegisterListeners(MainController mainController, IGameManager gm, StatusController tc) {
        this.mainController = mainController;
        this.gm = gm;
        this.statusController = tc;
    }


    public void registerPlayerListeners(List<IPlayer> players) {

        for (IPlayer player : players) {

            if (player.getClass().getSimpleName().equals(Constants.PLAYER1)) {
                player.healthProperty().addListener((observable) ->
                        statusController.getHumanHealth().setText(Constants.PLAYERNAME1 + " Health: " +
                                player.healthProperty().getValue())
                );
            } else if (player.getClass().getSimpleName().equals(Constants.PLAYER2)) {
                player.healthProperty().addListener((observable) ->
                        statusController.getMonsterHealth().setText(Constants.PLAYERNAME2 + " Health: " +
                                player.healthProperty().getValue())
                );
            }
        }
    }

    public void registerPieceListListener() {
        for (IPlayer iPlayer : gm.getPlayers()) {
            ObservableList<IPiece> pieces = iPlayer.getPieces();
            pieces.addListener((ListChangeListener<IPiece>) c -> {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (IPiece piece : c.getAddedSubList()) {
                            gm.addPiece(piece);
                        }
                    } else if (c.wasRemoved()) {
                        c.getRemoved();
                        for (IPiece piece : c.getRemoved()) {
                            gm.removePiece(piece);
                        }
                    }
                }
            });
        }
    }

    void registerTurnListeners(Turn turn) {
        // increment Turn number label

        turn.turnNumberProperty().addListener(observable ->
                statusController.getTurnNumber().setText("Turn: " +
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
