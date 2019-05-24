package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class HumanPieceFactory extends AbstractPieceFactory {
    private static HumanPieceFactory instance;

    private HumanPieceFactory() {
    }

    public static AbstractPieceFactory getInstance() {

        if (instance == null) {
            instance = new HumanPieceFactory();
        }
        return instance;
    }

    @Override
    public Piece getPiece(String pieceClass, Location location) {

        Piece prototypePiece = HumanPrototypes.getInstance().getPrototype(pieceClass);

        Piece newPiece = (Piece) prototypePiece.clone();
        newPiece.setLocationProperty(location);

        return newPiece;
    }

}
