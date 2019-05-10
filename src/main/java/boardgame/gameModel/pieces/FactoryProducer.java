package boardgame.gameModel.pieces;

public class FactoryProducer {

    public static AbstractPieceFactory getFactory(String playerType) {
        if (playerType.equals("Human")) {
            return new HumanPieceFactory();
        } else
            return new MonsterPieceFactory();
    }
}

