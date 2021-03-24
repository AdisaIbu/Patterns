package multiple.patterns.library.dp.command;

import multiple.patterns.library.model.ColorPalette;
import multiple.patterns.library.model.Shape;
import multiple.patterns.library.model.ShapeManager;

/**
 *
 * The concrete implementation of the change color command
 */
public class ChangeColorCommand implements ICommand{
    /**
     * The action receiver
     */
    private ShapeManager manager;
    /**
     * The shape that will receive the action
     */
    private Shape selectedShape;
    /**
     * The shape's new color
     */
    private ColorPalette color;
    /**
     * The shape's old color
     */
    private ColorPalette oldColor;

    /**
     * Constructor for the change color command
     * @param manager
     * @param color
     * @param selectedShape 
     */
    public ChangeColorCommand(ShapeManager manager, ColorPalette color, Shape selectedShape) {
        this.manager = manager;
        this.selectedShape = selectedShape;
        this.color = color;
        this.oldColor = selectedShape.getColor();
    }
    

    /**
     * The execution of the change color action
     * @return 
     */
    @Override
    public Shape execute() {
        Shape shapeColored = manager.changeColor(selectedShape, color);
        return shapeColored;
    }

    /**
     * Undoing the change color action by compensation
     */
    @Override
    public void undo() {
        manager.changeColor(selectedShape, oldColor);
    }

    /**
     * redoing the change color action
     */
    @Override
    public void redo() {
        manager.changeColor(selectedShape, color);
    }
    
}
