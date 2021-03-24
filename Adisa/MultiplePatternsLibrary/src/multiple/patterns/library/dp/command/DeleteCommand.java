package multiple.patterns.library.dp.command;

import java.util.List;
import multiple.patterns.library.model.Polygon;
import multiple.patterns.library.model.Shape;
import multiple.patterns.library.model.ShapeManager;

/**
 *
 * The concrete implementation of the delete command
 */
public class DeleteCommand implements ICommand {

    /**
     * The receiver of the command
     */
    private ShapeManager manager;
    /**
     * The shape to be deleted
     */
    private Shape shapeToDelete;
    /**
     * The clones of the shape to delete
     */
    private List<Polygon> clonesToDelete;

    /**
     * Deletion command constructor
     * @param manager
     * @param shapeToDelete 
     */
    public DeleteCommand(ShapeManager manager, Shape shapeToDelete) {
        this.manager = manager;
        this.shapeToDelete = shapeToDelete;
    }

    /**
     * The execution of the deletion action
     * @return 
     */
    @Override
    public Shape execute() {
        clonesToDelete = ((Polygon) shapeToDelete).getClones();
        clonesToDelete.forEach(shape -> System.out.println(shape));
        manager.delete(shapeToDelete);
        return null;
    }

    /**
     * Undoing the deletion action by compensation
     */
    @Override
    public void undo() {
        
        for (Polygon clone : clonesToDelete){
            manager.add(clone);
        }
        manager.add(shapeToDelete);
    }

    /**
     * re-deleting the shape
     */
    @Override
    public void redo() {
        manager.delete(shapeToDelete);
    }

}
