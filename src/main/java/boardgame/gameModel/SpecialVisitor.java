package boardgame.gameModel;

import boardgame.gameModel.command.SpecialCommand;
import boardgame.gameModel.pieces.*;
import boardgame.gameModel.state.HighlightTilesVisitor;
import boardgame.gameModel.state.states;

/**
 * The interface Special visitor is used to determine which special ability gets triggered when a player selects a
 * piece and then presses the special ability button. This visitor pattern has been implemented because otherwise
 * determining which Special Ablility is triggered becomes a series of if/else statements and breaks very easily.
 * With the visitor pattern each Piece only needs the accept(Visitor v) method added to the class. The implementation
 * of the Special Visitor class (PieceVisitor) is passed to a piece. The piece then passes itself back to the
 * Piece Visitor which then determines the Special Ability to take depending on which piece is passed back. This
 * enables the Special Ability to be determined polymorphically with only a very small change to the Piece class.
 *
 *
 */
public interface SpecialVisitor {

    /**
     * Gets a HighlightTiles visitor. The Highlight Tiles visitor is stored in the Special Visitor and is
     * used to determine which tiles/pieces a Command should act on.
     *
     * @return the hv
     */
    HighlightTilesVisitor getHv();

    /**
     * Visit a Priest.
     *
     * @param piece the piece
     */
//Human visitors
    void visit(Priest piece);

    /**
     * Visit a Warrior.
     *
     * @param piece the piece
     */
    void visit(Warrior piece);

    /**
     * Visit an Archer.
     *
     * @param piece the piece
     */
    void visit(Archer piece);


    //Monster visiors.

    /**
     * Visit a Griffin.
     *
     * @param piece the piece
     */
    void visit(Griffin piece);

    /**
     * Visit a Medusa.
     *
     * @param piece the piece
     */
    void visit(Medusa piece);

    /**
     * Visit a Minotaur.
     *
     * @param piece the piece
     */
    void visit(Minotaur piece);

    /**
     * Gets a command. The Command from the Command Pattern is stored here and when a Special Ability is triggered the
     * Command stored here polymorphically determines which Special Ability Command to enact (eg. Ranged Attack).
     *
     * @return a Command
     */
    SpecialCommand getCommand();

    /**
     * Gets a game State. Special Visitor stores a game state for retrieval by game context.
     *
     * @return the state
     */
    states getState();
}
