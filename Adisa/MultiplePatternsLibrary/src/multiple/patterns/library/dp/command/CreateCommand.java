package multiple.patterns.library.dp.command;

import multiple.patterns.library.model.ColorPalette;
import multiple.patterns.library.model.Coordinate;
import multiple.patterns.library.model.Shape;
import multiple.patterns.library.model.ShapeManager;
import multiple.patterns.library.model.ShapeType;

/**
 *
 * The command class for the creation of the shape
 */
public class CreateCommand  implements ICommand {
    
    // Parameters for the shape creation
    private Coordinate center;
    
    private Double radius;
    
    private ColorPalette color;
    
    private ShapeType type;
    
    /**
     * The newly created shape
     */
    private Shape createdShape;
    
    /**
     * The receiver of the command
     */
    private ShapeManager manager;

    /**
     * The creation command constructor
     * @param manager
     * @param center
     * @param radius
     * @param color
     * @param type 
     */
    public CreateCommand(ShapeManager manager, Coordinate center, Double radius, ColorPalette color, ShapeType type) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.type = type;
        this.manager = manager;
    }
    

    /**
     * Creates a shape
     * @return 
     */
    @Override
    public Shape execute() {
        createdShape = manager.create(this.type, this.center, this.radius, this.color);
        return createdShape;
    }

    /**
     * Undo of the create action
     */
    @Override
    public void undo() {
        manager.delete(createdShape);
    }

    /**
     * redo of the create action
     */
    @Override
    public void redo() {
        manager.add(createdShape);
    }
    
}
