package boardgame.gameModel.pieces.factories;

import boardgame.gameModel.pieces.AbstractPieceFactory;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Piece;
import boardgame.gameModel.pieces.prototypes.HumanPrototypes;
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

    @Override
    public IPiece getPieceByName(String pieceName, Location location) {

        Piece prototypePiece = HumanPrototypes.getInstance().getPrototypeByName(pieceName);

        Piece newPiece = (Piece) prototypePiece.clone();
        newPiece.setLocationProperty(location);

        return newPiece;
    }

}
