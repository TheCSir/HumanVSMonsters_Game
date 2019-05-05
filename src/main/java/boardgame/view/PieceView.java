package boardgame.view;

import javafx.scene.transform.Translate;

public class PieceView {

    public PieceView() {

    }

    public static void changePiecePosition(HexagonTileViewPiece hexagonPiece, TileView desiredTilePosition) {

        Translate translate = new Translate();
        translate.setX(desiredTilePosition.getBoundsInParent().getCenterX() - hexagonPiece.getBoundsInParent().getCenterX());
        translate.setY(desiredTilePosition.getBoundsInParent().getCenterY() - hexagonPiece.getBoundsInParent().getCenterY());
        hexagonPiece.getTransforms().addAll(translate);
        //Bring piece to front so that it doesn't get stuck behind background tile.
        hexagonPiece.toFront();
    }

    public static void changePieceFromAbsoluteValue(HexagonTileViewPiece hexagonPiece, double x, double y) {
        Translate translate = new Translate();
        translate.setX(x - hexagonPiece.getBoundsInParent().getCenterX());
        translate.setY(y - hexagonPiece.getBoundsInParent().getCenterY());
        hexagonPiece.getTransforms().addAll(translate);
        //Bring piece to front so that it doesn't get stuck behind background tile.
        hexagonPiece.toFront();
    }


}
