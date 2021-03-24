package multiple.patterns.library.dp.command;

import multiple.patterns.library.model.Shape;

/**
 * The command interface that has to be implemented by the 
 * The command object in this design pattern
 */
public interface ICommand {

    /**
     * Executing the action
     *
     * @return
     */
    Shape execute();

    /**
     * undo operation
     */
    void undo();

    /**
     * redo operation
     */
    void redo();
}
