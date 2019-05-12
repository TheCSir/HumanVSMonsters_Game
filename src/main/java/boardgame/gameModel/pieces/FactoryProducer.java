package boardgame.gameModel.pieces;

public class FactoryProducer {

    public static AbstractPieceFactory getFactory(String playerType) {
        if (playerType.equals(PieceConstants.HUMANPLAYER)) {
            return new HumanPieceFactory();
        } else if (playerType.equals(PieceConstants.MONSTERPLAYER)) {
            return new MonsterPieceFactory();
        }
        return null;
    }
}

