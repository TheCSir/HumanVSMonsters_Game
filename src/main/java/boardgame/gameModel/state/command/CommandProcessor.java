package boardgame.gameModel.state.command;

import java.util.Stack;

public class CommandProcessor {

    private Stack<Command> undoList = new Stack<>();
    private Stack<Command> redoList = new Stack<>();
    private ListenerList listeners = new ListenerList();

    public void execute(Command command) {
        //Step 1: Clear the redo list
        redoList.clear();

        //Step 2: Execute the Command
        command.execute();

        //Step 3: Add the command to the history.
        undoList.push(command);

        //Step 4: Notify all observers.
        fireCommandHistoryChanged();
    }

    public void undo() {

    }

    public void redo() {

    }

    private void fireCommandHistoryChanged() {
    }

    private class ListenerList {
    }
}
