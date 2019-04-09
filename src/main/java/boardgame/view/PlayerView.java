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
        // TODO decrease attacked player health from view
    }
}
