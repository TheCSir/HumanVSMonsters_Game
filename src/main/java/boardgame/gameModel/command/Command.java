package boardgame.gameModel.command;


/**
 * The interface Command. Our game uses the Command pattern for when a player executes actions such as moving a piece
 * attacking etc. We chose to use the Command pattern for several reasons. First it enables us to store history. Because
 * a Command is an object and not a method the State variables used to execute a command can be stored in a data structure
 * such as a stack. Secondly, it is easy to add new Commands with minimal changes to existing classes. Thirdly it
 * supports polymorhically calling different command depending on the caller. In our game our pieces each have a special
 * ability. We can determine which ability to call by having an abstract SpecialCommand class which then has
 * subclasses with the type of Special Ability (Ranged Attack etc...). See the visitor pattern (Special Visitor and
 * PieceVisitor sublass) to see how we polymorphicaly determine which Special Ability Command to call depending on which
 * piece is calling it.
 */
public interface Command {
    /**
     * Execute a Command. This Command will go onto the Command history stack.
     */
    void execute();

    /**
     * Undo a Command. Undoing a Command pops the top off the history Stack of Command and takes actions to reverse
     * what was done using variables stored in the respective Command class.
     */
    void undo();

    /**
     * Redo a Command. Whilst sometimes the Command pattern leaves out Redo and just includes execute() we chose to
     * keep it in as it gives greater control over redoing actions. Often when redoing an action a lot of validation
     * can be skipped and this can make it easier and less buggy to bring the state back to where it should be.
     */
    void redo();

}
