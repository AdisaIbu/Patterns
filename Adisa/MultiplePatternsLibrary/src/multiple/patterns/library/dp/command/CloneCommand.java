package multiple.patterns.library.dp.command;

import multiple.patterns.library.model.Coordinate;
import multiple.patterns.library.model.Shape;
import multiple.patterns.library.model.ShapeManager;

/**
 * The concrete implementation of the clone action
 */
public class CloneCommand implements ICommand {

    /**
     * The action receiver
     */
    private ShapeManager manager;
    /**
     * The relative coordinates of the clone
     */
    private Coordinate relativeCoordinate;
    /**
     * The shape to be clones
     */
    private Shape selectedShape;
    /**
     * The clone of the selected object
     */
    private Shape clone;

    /**
     * Clone command constructor
     * @param manager
     * @param selectedShape
     * @param relativeCoordinate 
     */
    public CloneCommand(ShapeManager manager, Shape selectedShape, Coordinate relativeCoordinate) {
        this.manager = manager;
        this.selectedShape = selectedShape;
        this.relativeCoordinate = relativeCoordinate;
    }

    /**
     * Execution of the command action
     * @return 
     */
    @Override
    public Shape execute() {
        this.clone = manager.clone(selectedShape, relativeCoordinate);
        return clone;
    }

    /**
     * Undo action, by compensation
     */
    @Override
    public void undo() {
        clone.removeCloneFromSource();
        manager.delete(clone);
    }

    /**
     * re-cloning the object
     */
    @Override
    public void redo() {
        selectedShape.addClone(clone);
        manager.add(clone);
    }

}
