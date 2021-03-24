package multiple.patterns.library.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import multiple.patterns.library.dp.iterator.ShapeIteratorImpl;

/**
 *
 * The class that manages the shapes
 * It also represents the receiver in the command design pattern
 */
public class ShapeManager implements Iterable<Shape> {

    /**
     * Map that contains the shapes
     */
    private HashMap<Integer, Shape> shapesMap = new HashMap<Integer, Shape>();

    /**
     * Id of the shape
     */
    private static Integer ordinalId = 1;

    /**
     * Getter of the ordinalId
     *
     * @return
     */
    public static int getNextId() {
        return ordinalId++;
    }

    /**
     * Returns the shapes contained in shapesMap
     *
     * @return
     */
    public Collection<Shape> getShapes() {
        return this.shapesMap.values();
    }

    /**
     * Add a new Shape to the map
     *
     * @param newShape
     */
    public void add(Shape newShape) {
        shapesMap.put(newShape.getId(), newShape);
    }

    /**
     * Creates a new shape and adds it to the map
     *
     * @param type
     * @param center
     * @param radius
     * @param color
     * @return
     */
    public Shape create(ShapeType type, Coordinate center, Double radius, ColorPalette color) {
        // Get the corresponding shape through the factory
        ShapeFactory factory = new ShapeFactory();
        Shape createdShape = factory.getShape(getNextId(), type, center, radius, color);
        // Add the new shape to the map of shapes
        this.add(createdShape);
        // Return the created Shape
        return createdShape;
    }

    /**
     * Move the selected color by the delta coordinates in input
     *
     * @param selectedShape
     * @param center
     * @return
     */
    public Shape move(Shape selectedShape, Coordinate center) {
        // Moves the selected shape
        selectedShape.move(center);
        // Return the moved Shape
        return selectedShape;
    }

    /**
     * Change the color of the selected shape
     *
     * @param selectedShape
     * @param color
     * @return
     */
    public Shape changeColor(Shape selectedShape, ColorPalette color) {
        selectedShape.changeColor(color);
        return selectedShape;
    }

    /**
     * Scale the size of a shape, by increasing or decreasing it
     *
     * @param selectedShape
     * @param factor
     * @return
     */
    public Shape scale(Shape selectedShape, Double factor) {
        selectedShape.scale(factor);
        return selectedShape;
    }

    /**
     * Clone a shape with a relative move from the source shape
     *
     * @param selectedShape
     * @param relativeCoordinate
     * @return
     */
    public Shape clone(Shape selectedShape, Coordinate relativeCoordinate) {
        Shape clone = selectedShape.clone(relativeCoordinate, getNextId());
        this.add(clone);
        return clone;
    }

    /**
     * Delete shape from shapesMap
     *
     * @param selectedShape
     */
    public void delete(Shape selectedShape) {
        // Removes only the selected shape if it is a clone
        if (selectedShape.isClone()) {
            shapesMap.remove(selectedShape.getId());
            selectedShape.removeCloneFromSource();
        } else {
            // Removes the shape and its clones if it is a source shape
            selectedShape.getClonesIds().forEach((idClone) -> {
                shapesMap.remove(idClone);
            });
            shapesMap.remove(selectedShape.getId());
        }
    }

    /**
     * Get Shape from shapesMap by ordinalId
     *
     * @param id
     * @return
     */
    public Shape getShape(Integer id) {
        return shapesMap.get(id);
    }

    /**
     * Return the iterator implementation for the shapes
     * @return 
     */
    @Override
    public Iterator<Shape> iterator() {
        return new ShapeIteratorImpl(new ArrayList<Shape>(this.getShapes()));
    }
}
