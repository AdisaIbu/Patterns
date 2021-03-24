package multiple.patterns.library.model;

/**
 *
 * The diamond shape, a child of the polygon object
 * In this project all the shapes are equidistant from the center
 * This distance is represented by the radius value
 */
public class Diamond extends Polygon {
    
    /**
     * Constructor of the Diamond Shape
     * @param id
     * @param center
     * @param radius
     * @param color 
     */
    public Diamond(Integer id, Coordinate center, Double radius, ColorPalette color) {
    this.id = id;
    this.coordinates = center;
    this.radius = radius;
    this.color = color;
    this.clone = false;
    this.sideCount = 4;
}
    
    /**
     * The string representation of the diamond shape
     * @return 
     */
    @Override
    public String toString() {
        return "Diamond Id : " + id + ", Center Coordinates ( "+ coordinates.getX() + " , " + coordinates.getY() + " ), radius " + radius + ", it's color is " + color + " and is " + ((isClone())? ("a clone of the shape " + source.getId()) : "not a clone");
    }
}
