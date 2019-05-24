package boardgame.gameModel.pieces;


import boardgame.gameModel.pieces.factories.HumanPieceFactory;
import boardgame.gameModel.pieces.factories.MonsterPieceFactory;

/**
 * The FactoryProducer class is used to get concrete instances of a Factory. This is part of the Abstract Factory
 * design pattern. We made an exception to include a switch statement here due to the very simple logic. If in future
 * another player type (a Space Alien for instance) this class would need to be altered but the other classes in the
 * Abstract Factory pattern would need no changes. This means we could add another player type with different
 * pieces without major refactoring.
 */
public class FactoryProducer {

    /**
     * Gets a concrete factory class determined by which playertype is called.
     *
     * @param playerType the player type
     * @return the factory
     */
    public static AbstractPieceFactory getFactory(String playerType) {
        if (playerType.equals(PieceConstants.HUMANPLAYER)) {
            return HumanPieceFactory.getInstance();
        } else if (playerType.equals(PieceConstants.MONSTERPLAYER)) {
            return MonsterPieceFactory.getInstance();
        }
        return null;
    }
}