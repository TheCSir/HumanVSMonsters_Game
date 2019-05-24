package boardgame.gameModel.pieces;

public class FactoryProducer {

    public static AbstractPieceFactory getFactory(String playerType) {
        if (playerType.equals(PieceConstants.HUMANPLAYER)) {
            return HumanPieceFactory.getInstance();
        } else if (playerType.equals(PieceConstants.MONSTERPLAYER)) {
            return MonsterPieceFactory.getInstance();
        }
        return null;
    }
}