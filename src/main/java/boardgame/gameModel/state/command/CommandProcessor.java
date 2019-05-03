package boardgame.gameModel.state.command;

import java.util.Stack;

import static org.valid4j.Assertive.require;

public class CommandProcessor {

    private Stack<Command> undoList = new Stack<>();
    private Stack<Command> redoList = new Stack<>();
    private ListenerList listeners = new ListenerList();

    public void execute(Command command) {
        //Step 1: Clear the redo list. This is important
        redoList.clear();

        //Step 2: Execute the Command
        command.execute();

        //Step 3: Add the command to the history.
        undoList.push(command);

        //Step 4: Notify all observers.
        fireCommandHistoryChanged();
    }

    public void undo() {

        //Steo 1: Assert that the undo list is empty. Cannt undo if nothing left to undo.
        require(!undoList.isEmpty());

        //Step 2: Pop off the top of the stack and assign the command to a variable.
        Command cmd = undoList.pop();

        //Step 3: Execute the internal undo method.
        cmd.undo();

        //Step 4: Add the command to the redo stack.
        redoList.push(cmd);

        fireCommandHistoryChanged();
    }

    public void redo(Command command) {
        System.out.println("redo not implemented.");
    }

    private void fireCommandHistoryChanged() {
    }

    private class ListenerList {
    }
}
