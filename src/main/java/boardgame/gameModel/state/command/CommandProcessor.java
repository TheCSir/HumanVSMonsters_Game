package boardgame.gameModel.state.command;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

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
        System.out.println("Undo list size is: " + undoList.size());
        //Steo 1: Assert that the undo list is empty. Can't undo if nothing left to undo.
        require(!undoList.isEmpty());

        //Step 2: Pop off the top of the stack and assign the command to a variable.
        Command cmd = undoList.pop();

        //Step 3: Execute the internal undo method.
        cmd.undo();

        //Step 4: Add the command to the redo stack.
        redoList.push(cmd);

        //Step 5: Notify listeners.
        fireCommandHistoryChanged();

        System.out.println("undoList = " + undoList.size());
    }

    public void redo() {
        Command cmd = redoList.pop();
        undoList.push(cmd);
        cmd.redo();
    }

    private void fireCommandHistoryChanged() {


    }

    //replay all the moves that have taken place. Change the interval 
    public void replayMoves() {

        final int m = undoList.size();
        for (int j = 0; j < m; j++) {
            System.out.println("Undoing");
            undo();
        }

        final int r = redoList.size();
        System.out.println("r = " + r);
        int interval = 2;
        final Timeline timeline = new Timeline();
        for (int i = 0; i < redoList.size(); i++) {
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(interval), e -> redo()));
            interval += 2;
        }
        Platform.runLater(timeline::play);

    }

    private class ListenerList {

    }

}
