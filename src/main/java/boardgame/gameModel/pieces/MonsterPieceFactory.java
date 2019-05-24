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
        PiecePrototypes p = PiecePrototypes.getInstance();
        Piece prototypePiece = p.getMonsterPrototype(pieceClass);

        Piece newPiece = (Piece) prototypePiece.clone();
        newPiece.setLocation(location);

        return newPiece;
    }

}

