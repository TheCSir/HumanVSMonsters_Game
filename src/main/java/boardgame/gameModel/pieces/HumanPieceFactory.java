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
        PiecePrototypes p = PiecePrototypes.getInstance();
        Piece prototypePiece = p.getHumanPrototype(pieceClass);

        Piece newPiece = (Piece) prototypePiece.clone();
        newPiece.setLocation(location);

        return newPiece;
    }

}
