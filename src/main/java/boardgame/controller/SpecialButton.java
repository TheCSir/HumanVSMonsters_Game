package boardgame.controller;

import boardgame.gameModel.pieces.IPiece;
import javafx.scene.control.Button;

public class SpecialButton {

    private Button button;
    private IPiece iPiece;

    public SpecialButton() {
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public IPiece getiPiece() {
        return iPiece;
    }

    public void setiPiece(IPiece iPiece) {
        this.iPiece = iPiece;
    }

    public void setButtonText(String s) {
        button.setText(s);
    }

    public String getText() {
        return button.getText();
    }
}
