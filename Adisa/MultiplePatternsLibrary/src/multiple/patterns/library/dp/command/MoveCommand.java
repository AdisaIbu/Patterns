package multiple.patterns.library.dp.command;

import multiple.patterns.library.model.Coordinate;
import multiple.patterns.library.model.Shape;
import multiple.patterns.library.model.ShapeManager;

/**
 *
 * The concrete implementation of the move command
 */
public class MoveCommand implements ICommand {

    /**
     * The delta coordinates to move by
     */
    private Coordinate center;
    /**
     * The command receiver
     */
    private ShapeManager manager;
    /**
     * The shape to move
     */
    private Shape selectedShape;
    
    /**
     * Move command constructor
     * @param manager
     * @param center
     * @param shape 
     */
    public MoveCommand(ShapeManager manager, Coordinate center, Shape shape) {
        this.center = center;
        this.manager = manager;
        this.selectedShape = shape;
    }
    
    /**
     * Execution fo the move command
     * @return 
     */
    @Override
    public Shape execute() {
        Shape movedShape = manager.move(selectedShape, center);
        return movedShape;
    }

    /**
     * undoing the move by compensation
     */
    @Override
    public void undo() {
        manager.move(selectedShape, center.invertCoordinates());
    }

    /**
     * Redoing the move action
     */
    @Override
    public void redo() {
        manager.move(selectedShape, center);
    }

}
