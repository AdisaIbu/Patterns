package multiple.patterns.library.dp.iterator;

import java.util.Iterator;
import java.util.List;
import multiple.patterns.library.model.Shape;

/**
 *
 * The class that implements the iterator interface
 */
public class ShapeIteratorImpl implements Iterator {

    /**
     * List of the shapes to iterate over
     */
    private List<Shape> shapeList;
    /**
     * The current position of the iterator
     */
    private int position;

    /**
     * Constructor of the class
     * @param shapeList 
     */
    public ShapeIteratorImpl(List<Shape> shapeList) {
        this.shapeList = shapeList;
        this.position=0;
    }

    /**
     * Implementation of the has next operation
     * @return 
     */
    @Override
    public boolean hasNext() {
        if (position < shapeList.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implementation of the next operation
     * @return 
     */
    @Override
    public Shape next() {
        return  shapeList.get(position++);
    }

}
