package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.Turn;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.view.HexagonTileViewPiece;
import boardgame.view.PieceView;
import boardgame.view.PlayerView;
import boardgame.view.TileView;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.util.List;

public class RegisterListeners {

    private final MainController mainController;
    private IGameManager gm;

    public RegisterListeners(MainController mainController, IGameManager gm) {
        this.mainController = mainController;
        this.gm = gm;
    }

    public void registerListeners() {

    }

    public void registerTileListenersForMove(List<TileView> boardTiles) {

        for (TileView hexagonalTile : boardTiles) {

            //Set tile handlers
            hexagonalTile.setOnMouseClicked(e -> mainController.handleTileClicked(hexagonalTile));
        }
    }

    public void registerPieceListener(IPiece piece) {
        piece.locationPropertyProperty().addListener((observable) ->
                PieceView.changePiecePosition(mainController.getSelectedTilePiece(), mainController.getTargetTile()));
    }

    public void registerPlayerListeners(List<IPlayer> players, HexagonTileViewPiece targetTilePiece, Label humanHealth, Label monsterHealth) {

        for (IPlayer player : players) {
            PlayerView playerView = new PlayerView();
            player.healthProperty().addListener((observable) ->
                    playerView.decreaseHealthBar(player, targetTilePiece));

            if (player.getClass().getSimpleName().equals("HumanPlayer")) {
                player.healthProperty().addListener((observable) ->
                        humanHealth.setText("Gandalf Health: " +
                                player.healthProperty().getValue())
                );
            } else if (player.getClass().getSimpleName().equals("MonsterPlayer")) {
                player.healthProperty().addListener((observable) ->
                        monsterHealth.setText("Sauron Health: " +
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
                            mainController.getGameController().addPiece(piece);
                        }
                    } else if (c.wasRemoved()) {
                        c.getRemoved();
                        for (IPiece piece : c.getRemoved()) {
                            mainController.getGameController().removePiece(piece);
                        }
                    }
                }
            });
        }
    }

    void registerTurnListeners(Turn turn) {
        // increment Turn number label
        turn.turnNumberProperty().addListener(observable ->
                mainController.getTurnNumber().setText("Turn: " +
                        turn.getTurnNumber())
        );

        // Change Current Player label
        gm.getTurn().getActivePlayerProperty().addListener(observable ->
                mainController.getCurrentPlayer().setText("Current Player: " + turn.getActivePlayer().getPlayerName()));

        // reset currentState
        turn.turnNumberProperty().addListener(observable ->
                mainController.setCurrentState(MainController.State.NONE)
        );
    }

    public void unRegisterPieceListeners(List<IPiece> pieces) {

        for (IPiece piece : pieces) {
            piece.locationPropertyProperty().removeListener((observable) ->
                    PieceView.changePiecePosition(mainController.getSelectedTilePiece(), mainController.getTargetTile()));
        }
    }

}
