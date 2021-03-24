package multiple.patterns.library.model;

/**
 *
 * The factory that returns the implementation of the shape
 */
public class ShapeFactory {
    
    /**
     * Method to create the appropriate shape depending on its type
     * @param ordinalId
     * @param type
     * @param coordinate
     * @param radius
     * @param color
     * @return 
     */
    public Shape getShape(Integer ordinalId, ShapeType type, Coordinate coordinate, Double radius, ColorPalette color){
        switch (type) {
            case TRIANGLE:
                return new Triangle(ordinalId, coordinate, radius, color);
            case DIAMOND:
                return new Diamond(ordinalId, coordinate, radius, color);
            case PENTAGON:
                return new Pentagon(ordinalId, coordinate, radius, color);
            default:
                return null;
        }
    }
    
}
