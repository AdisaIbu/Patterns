package multiple.patterns.library.model;

import java.util.List;
/**
 * The interface that defines the behavior of a shape
 */
public interface Shape {
    /**
     * Signature for moving a shape
     * @param center
     */ 
    public void move(Coordinate center);
    /**
     * Signature for scaling a shape
     * @param asc
     * @param facotor 
     */
    public void scale(Double facotor);
    /**
     * Signature for changing the color of a shape
     * @param newColor 
     */
    public void changeColor(ColorPalette newColor); 
    /**
     * Signature for cloning a shape
     * @param relativeCoordinate
     * @param ordinalId
     * @return 
     */
    public Shape clone(Coordinate relativeCoordinate, Integer ordinalId);
    
    /**
     * Signature for adding an existing clone
     * @param clone 
     */
    public void addClone(Shape clone);

    
    /**
     * Signature for getting ordinalId of the Shape
     * @return ordinalId
     */
    public Integer getId();
    
    /**
     * Signature for getting the type of the shape
     * @return 
     */
    public Boolean isClone();
    
    /**
     * Signature for removing clones from source shape
     */
    public void removeCloneFromSource();
    
    /**
     * Signature for getting the clones Ids
     * @return 
     */
    public List<Integer> getClonesIds();
    
    /**
     * Signature for getting the shape's color
     * @return 
     */
    public ColorPalette getColor();
    
    /**
     * Signature for getting the number of sides
     * @return 
     */
    public Integer getSides();
    
    public Double getRadius();
    
    public Coordinate getCoordinates();
}
