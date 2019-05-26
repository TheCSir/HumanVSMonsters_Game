package boardgame.gameModel.pieces;


/**
 * The PiecePrototypes implementation is used to get a prototype for use by the Factory classes to create a
 * new piece. Each of our Pieces has a class type (MELEE, RANGED OR SUPPORT). So for example the Monster player's
 * Melee class is the Minotaur. The Human's Melee class is the Warrior. The client doesn't need to know what type
 * of piece to request. This enables flexibility as if in the future we were to add another player type such as
 * an Alien Player with different pieces we can just add a Melee, Support and Ranged piece for the new player without
 * having to change client code to explicity create the new pieces. This in turn makes other areas of the code simpler
 * and easier to use polymorphism instead of if/else or switch statements.
 */
public interface PiecePrototypes {


    /**
     * @param classtype A string holding the type of class requested (MELEE, RANGED, SUPPORT)
     * @return A concrete Piece returned polymorphically depending on which set of Prototypes called.
     */
    Piece getPrototype(String classtype);

    Piece getPrototypeByName(String name);
}
