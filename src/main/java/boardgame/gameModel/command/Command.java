package boardgame.gameModel.command;

//Turn

public interface Command {
    void execute();

    void undo();

    void redo();

}
