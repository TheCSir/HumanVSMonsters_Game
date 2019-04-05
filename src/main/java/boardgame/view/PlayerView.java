package boardgame.view;

import boardgame.gameModel.IPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayerView {
    @FXML
    private Label humanHealth;

    @FXML
    private Label monsterHealth;

    public PlayerView(){}

    public void decreaseHealthBar(IPlayer targetedPlayer, HexagonTileViewPiece targetedHexagonPiece) {
        System.out.println("Player Class: " +
                targetedPlayer.getClass().getSimpleName() +
                ". Player Health" +
                targetedPlayer.healthProperty()
        );
        System.out.println("Piece Class: " +
                targetedHexagonPiece.getiPiece().getClass().getSimpleName()
        );
    }
}
