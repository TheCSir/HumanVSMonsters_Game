package boardgame.gameModel.state.command;

import java.util.Stack;

public class CommandProcessor {
    private Stack<Command> undoList;
    private Stack<Command> redoList;
}
