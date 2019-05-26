package boardgame.gameModel.state;

import boardgame.gameModel.state.stateImp.*;

/**
 * The interface Highlight visitor. The HighlightVisitor interface is part of a Visitor pattern. In our game tiles need
 * to be highlighted for different purposes depending on the game state. For instance when a player clicks the move
 * button the tiles around the seleceted piece are highlight to give a visual indication where the piece can be moved
 * to. That logic was previously in methods in the GameContext class. However, the logic started to get too complicated
 * and the code became very hard to debug as if a small change was made to some game logic it would cause errors
 * with how the tiles were highlighted or cleared of their highlights. As a solution to this we implemented the Visitor
 * pattern by adding an accept(HighlightVisitor v) method which allows different highlighting actions to be taken
 * polymorphically depending on which State the game is currently in.
 */
public interface HighlightVisitor {

    /**
     * Visit a ranged AttackState
     *
     * @param r the State RangedAttackState
     */
    void visit(RangedAttackState r);

    /**
     * Visit a SpcialAttackState and highlight enemy tiles that contain pieces that can be attacked.
     *
     * @param s the State SpecialAttack.
     */
    void visit(SpecialAttackState s);

    /**
     * Visit a Summon State and highlight tiles that are valid to summon a creature to.
     *
     * @param s the State SummonState
     */
    void visit(SummonState s);

    /**
     * If a HealState is visited highlight all of the player's own pieces. This shows the player what piece's
     * can be healed. Currently set to unlimited range.
     * @param h the State HealState
     */
    void visit(HealState h);

    /**
     * Visit and enemyPieceSelectedState.
     *
     * @param enemyPieceSel the enemy piece sel
     */
    void visit(EnemyPieceSel enemyPieceSel);


    /**
     * If an IdleState is visited first reset set all of the current player's pieces to Blue
     * @param idleState the State IdleState.
     */

    void visit(IdleState idleState);

    /**
     * Visit a MoveState and highlight tiles that are valid for a piece to move to.
     *
     * @param moveState the move state
     */
    void visit(MoveState moveState);

    /**
     * Visit an attack State and highlight pieces depending on attack range.
     *
     * @param attackState the attack state
     */
    void visit(AttackState attackState);

    /**
     * Visit defence State and highlight pieces that the defence status can be applied to.
     *
     * @param defenceState the defence state
     */
    void visit(DefenceState defenceState);

    void visit(OwnPieceSelected ownPieceSelected);
}
