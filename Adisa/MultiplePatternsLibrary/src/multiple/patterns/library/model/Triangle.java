package multiple.patterns.library.model;

/**
 *
 * The Triangle shape, a child of the polygon object
 * In this project all the shapes are equidistant from the center
 * This distance is represented by the radius value
 */
public class Triangle extends Polygon {

    /**
     * Constructor of the Triangle shape
     * @param id
     * @param center
     * @param radius
     * @param color 
     */
    public Triangle(Integer id, Coordinate center, Double radius, ColorPalette color) {
        this.id = id;
        this.coordinates = center;
        this.radius = radius;
        this.color = color;
        this.clone = false;
        // Number of sides for the triangle
        this.sideCount = 3;                 
    }
    
    /**
     * The string representation of the triangle
     * @return 
     */
    @Override
    public String toString() {

        return "Triangle Id : " + id + ", Center Coordinates ( "+ coordinates.getX() + " , " + coordinates.getY() + " ), radius " + radius + ", it's color is " + color + " and is " + ((isClone())? ("a clone of the shape " + source.getId()) : "not a clone");
    }
    
}
