package multiple.patterns.library.dp.command;

import multiple.patterns.library.model.Shape;
import multiple.patterns.library.model.ShapeManager;

/**
 *
 * @author oski
 */
public class ScaleCommand implements ICommand {
    /**
     * The command receiver
     */
    private ShapeManager manager;
    /**
     * The shape to be scaled
     */
    private Shape selectedShape;
    /**
     * The scale factor
     * increasing for factor >1 and decreasing for factor between 0 and 1
     */
    private Double factor;

    /**
     * Scaling command constructor
     * @param manager
     * @param selectedShape
     * @param factor 
     */
    public ScaleCommand(ShapeManager manager, Shape selectedShape, Double factor) {
        this.manager = manager;
        this.selectedShape = selectedShape;
        this.factor = factor;
    }
    
    /**
     * Execution of the scaling action
     * @return 
     */
    @Override
    public Shape execute() {
        Shape scaledShape = manager.scale(selectedShape, factor);
        return scaledShape;
    }

    /**
     * Undoing the scale by compensation
     */
    @Override
    public void undo() {
       manager.scale(selectedShape, 1d/factor);
    }

    /**
     * re-scaling the shape
     */
    @Override
    public void redo() {
     manager.scale(selectedShape, factor);
    }
    
}
