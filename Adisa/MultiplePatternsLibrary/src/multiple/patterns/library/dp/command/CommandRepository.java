package multiple.patterns.library.dp.command;

import java.util.LinkedList;
import multiple.patterns.library.model.Shape;

/**
 * This class plays the role of the invoker in the Command design pattern
 * 
 */
public class CommandRepository {

    /**
     * Stack to store the executed commands
     */
    private LinkedList<ICommand> actionStack = new LinkedList<ICommand>();
    /**
     * Stack to store the undone actions to be redone on demand
     */
    private LinkedList<ICommand> redoStack = new LinkedList<ICommand>();

    /**
     * This method is used to execute the action
     * depending on the command parameter
     * The action executed is added to the actions stack
     * @param command
     * @return the shape object created or modified
     */
    public Shape execute(ICommand command) {
        // Execution of the command
        Shape result = command.execute();
        //Add the command to the command stack from undo purposes
        actionStack.addFirst(command);
        //clear the redo stack
        redoStack.clear();
        return result;
    }

    /**
     * Undo the last command
     * This operation is done by compensating the actual action
     */
    public void undo() {
        if (actionStack.isEmpty()) {
            return;
        }
        //Remove the action to be undone from the command stack
        ICommand command = actionStack.removeFirst();
        // Undo the command
        command.undo();
        // Add the command to the redo stack
        redoStack.addFirst(command);
    }

    /**
     * Redo the undone commands
     * Executes the action again
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        // Remove the command to be redone from the redo stack
        ICommand command = redoStack.removeFirst();
        // Redo the command
        command.redo();
        // Send back the redone command to the command stack
        actionStack.addFirst(command);
    }
    
    /**
     * checks if there is actions to undo
     * @return true if the actions stack contains elements, false otherwise
     */
    public boolean undoable(){
        return !actionStack.isEmpty();
    }
    
     /**
     * checks if there is actions to redo
     * @return true if the redo stack contains elements, false otherwise
     */
    public boolean redoable(){
        return !redoStack.isEmpty();
    }

}
