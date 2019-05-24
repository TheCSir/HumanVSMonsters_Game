package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class MonsterPieceFactory extends AbstractPieceFactory {


    private static MonsterPieceFactory instance;

    private MonsterPieceFactory() {
    }

    public static MonsterPieceFactory getInstance() {
        if (instance == null) {
            instance = new MonsterPieceFactory();
        }
        return instance;
    }

    @Override
    public Piece getPiece(String pieceClass, Location location) {

        Piece prototypePiece = MonsterPrototypes.getInstance().getPrototype(pieceClass);

        Piece newPiece = (Piece) prototypePiece.clone();
        newPiece.setLocationProperty(location);

        return newPiece;
    }

}

