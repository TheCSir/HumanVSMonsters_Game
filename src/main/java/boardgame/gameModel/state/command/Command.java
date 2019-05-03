package boardgame.gameModel.state.command;

//Turn

public interface Command {
    void execute();

    void undo();

    void redo();

}
