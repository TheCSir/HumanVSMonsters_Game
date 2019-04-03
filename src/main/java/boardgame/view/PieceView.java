package boardgame.view;

import javafx.scene.transform.Translate;

public class PieceView {


    public PieceView() {

    }


    public void changePiecePosition(HexagonTileViewPiece hexagonPiece, HexagonTileView desiredTilePosition) {


        //TODO add check valid move back in. But in model instead of view
        //if (checkValidMove(hexagonPiece, desiredTilePosition.getLocation())) {

        //Should probably be a view method.
        Translate translate = new Translate();
        translate.setX(desiredTilePosition.getBoundsInParent().getCenterX() - hexagonPiece.getBoundsInParent().getCenterX());
        translate.setY(desiredTilePosition.getBoundsInParent().getCenterY() - hexagonPiece.getBoundsInParent().getCenterY());
        hexagonPiece.getTransforms().addAll(translate);
        //Bring piece to front so that it doesn't get stuck behind background tile.
        hexagonPiece.toFront();
        System.out.println(hexagonPiece.getLocation().getXProperty() + ", " + hexagonPiece.getLocation().getYProperty());
    }
}
