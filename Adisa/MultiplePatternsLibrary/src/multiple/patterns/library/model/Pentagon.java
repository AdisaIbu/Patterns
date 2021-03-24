package multiple.patterns.library.model;

/**
 *
 * The Pentagon shape, a child of the polygon object
 * In this project all the shapes are equidistant from the center
 * This distance is represented by the radius value
 */
public class Pentagon extends Polygon {

    /**
     * The constructor of the pentagon shape
     * @param id
     * @param center
     * @param radius
     * @param color 
     */
    public Pentagon(Integer id, Coordinate center, Double radius, ColorPalette color) {
        this.id = id;
        this.coordinates = center;
        this.radius = radius;
        this.color = color;
        this.clone = false;
        // Number of sides of the pentagon
        this.sideCount = 5;
    }
    
    /**
     * The string representation of the pentagon object
     * @return 
     */
    @Override
    public String toString() {

        return "Pentagon Id : " + id + ", Center Coordinates ( "+ coordinates.getX() + " , " + coordinates.getY() + " ), radius " + radius + ", it's color is " + color + " and is " + ((isClone())? ("a clone of the shape " + source.getId()) : "not a clone");
    }

}
