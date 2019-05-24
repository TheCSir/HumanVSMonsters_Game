package boardgame.gameModel.pieces.factories;

import boardgame.gameModel.pieces.AbstractPieceFactory;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Piece;
import boardgame.gameModel.pieces.prototypes.MonsterPrototypes;
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

    @Override
    public IPiece getPieceByName(String pieceName, Location location) {

        Piece prototypePiece = MonsterPrototypes.getInstance().getPrototypeByName(pieceName);

        Piece newPiece = (Piece) prototypePiece.clone();
        newPiece.setLocationProperty(location);

        return newPiece;
    }

}

