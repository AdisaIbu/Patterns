package multiple.patterns.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The polygon Shape
 * This object implements the Cloneable interface useful for the cloning process
 * It extends the observable class and implements the Observer interface to transmit the actions
 * between the clones and their source shape
 * 
 */
public abstract class Polygon extends Observable implements Shape, Observer, Cloneable {

    /**
     * shape identifier
     */
    protected Integer id;

    /**
     * The coordinates of the shape
     */
    protected Coordinate coordinates;

    /**
     * Color of the shape
     */
    protected ColorPalette color;

    /**
     * The length of the radius of the shape
     */
    protected Double radius;

    /**
     * Is this shape a clone
     */
    protected Boolean clone;

    /**
     * Number of sides
     */
    protected Integer sideCount;

    /**
     * The source shape for a clone
     */
    protected Polygon source;

    /**
     * List of the clones of the current shape
     */
    protected List<Polygon> clones = new ArrayList<Polygon>();

    /**
     * Getter of the shape id
     *
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Moves the shape with the delta values for the center
     * @param delta 
     */
    @Override
    public void move(Coordinate delta) {
        // If the current shape is a clone, the action is called for the source shape
        if (this.isClone()) {
            Polygon cloneSource = this.getSource();
            if (cloneSource != null) {
                cloneSource.move(delta);
            } else {
                System.err.println("Move : Source is null for the shape " + this.getId());
            }
        } else {
            // If the current shape is not a clone the action is executed
            System.out.println("b4 move " + this.coordinates + " delta " + delta);
            this.coordinates = new Coordinate(this.getCoordinates().getX() + delta.getX(), this.getCoordinates().getY() + delta.getY());
            System.out.println("after move " + this.coordinates);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Scales the shape depending on the factor input
     * @param factor 
     */
    @Override
    public void scale(Double factor) {
        // If the current shape is a clone, the action is called for the source shape
        if (this.isClone()) {
            Polygon cloneSource = this.getSource();
            if (cloneSource != null) {
                cloneSource.scale(factor);
            } else {
                System.err.println("Scale : Source is null for the shape " + this.getId());
            }
        } else {
            // If the current shape is not a clone the action is executed
            this.radius = this.radius * factor;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Changes the color of the shape with the new color
     * @param newColor 
     */
    @Override
    public void changeColor(ColorPalette newColor) {
        // If the current shape is a clone, the action is called for the source shape
        if (this.isClone()) {
            Polygon cloneSource = this.getSource();
            if (cloneSource != null) {
                cloneSource.changeColor(newColor);
            } else {
                System.err.println("Change color : Source is null for the shape " + this.getId());
            }
        } else {
            // If the current shape is not a clone the action is executed
            this.color = newColor;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Implementing the Cloneable interface (Prototype DP)
     * @return the clone object
     * @throws CloneNotSupportedException 
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Polygon newClone = (Polygon) super.clone();
        newClone.clones = new ArrayList<Polygon>();
        newClone.clone = true;
        return newClone;
    }

    /**
     * Clones the shape with a relative coordinate
     * @param relativeCoordinate
     * @param ordinalId
     * @return 
     */
    @Override
    public Shape clone(Coordinate relativeCoordinate, Integer ordinalId) {
        try {
            // Cloning the source object
            Polygon newClone = (Polygon) this.clone();
            // Setting the ordianlId
            newClone.id = ordinalId;
            // Setting the source object
            if (this.isClone()) {
                newClone.source = this.getSource();
            } else {
                newClone.source = this;
            }

            // Adding the delta distance to the clones object
            Coordinate newCoordinate = new Coordinate(relativeCoordinate.getX() + this.getCoordinates().getX(),
                    relativeCoordinate.getY() + this.getCoordinates().getY());
            newCoordinate.setxR(relativeCoordinate.getX() + this.getCoordinates().getXR());
            newCoordinate.setyR(relativeCoordinate.getY() + this.getCoordinates().getYR());
            newClone.coordinates = newCoordinate;
            // Adding the clone to the observers and to the clones list
            newClone.source.addObserver(newClone);
            newClone.source.clones.add(newClone);
            return newClone;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Polygon.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * The update method that is called by the observer
     * to populate the changes happening with the source shape
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        Polygon cloneSource = (Polygon) o;
        this.color = cloneSource.getColor();
        this.radius = cloneSource.getRadius();
        this.coordinates.setX(cloneSource.getCoordinates().getX() + this.getCoordinates().getXR());
        this.coordinates.setY(cloneSource.getCoordinates().getY() + this.getCoordinates().getYR());
    }

    /**
     * Getter of the coordinates
     * @return 
     */
    public Coordinate getCoordinates() {
        return coordinates;
    }

    /**
     * Getter of the color
     * @return 
     */
    public ColorPalette getColor() {
        return color;
    }

    /**
     * Getter of the radius
     * @return 
     */
    public Double getRadius() {
        return radius;
    }

    /**
     * Getter of the clone boolean
     * @return 
     */
    public Boolean isClone() {
        return clone;
    }

    /**
     * Getter of the source object
     * @return 
     */
    public Polygon getSource() {
        return source;
    }

    /**
     * Getter of the clones list of this object
     * @return 
     */
    public List<Polygon> getClones() {
        return clones;
    }

    /**
     * Removes this clone from source shape
     */
    @Override
    public void removeCloneFromSource() {
        if (this.getSource() != null && this.getSource().getClones() != null) {
            this.getSource().getClones().remove(this);
        }
    }

    /**
     * Getting the clones Ids for a source shape
     *
     * @return
     */
    @Override
    public List<Integer> getClonesIds() {
        List<Integer> clonesIds = new ArrayList<Integer>();
        this.getClones().forEach((shape) -> {
            clonesIds.add(shape.getId());
        });
        return clonesIds;
    }

    /**
     * Adds a clone to the current shape
     * @param clone 
     */
    @Override
    public void addClone(Shape clone) {
        if (this.isClone()) {
            Polygon source = this.getSource();
            source.addObserver((Observer) clone);
            source.clones.add((Polygon) clone);
        } else {
            this.addObserver((Observer) clone);
            this.clones.add((Polygon) clone);
        }
    }

    /**
     * The string representation of the polygon
     * @return 
     */
    @Override
    public String toString() {

        return "###################### \n"
                + "Polygon{" + "\n"
                + "id=" + id + "\n"
                + ", coordinates=" + coordinates + "\n"
                + ", color=" + color + "\n"
                + ", radius=" + radius + "\n"
                + ", clone=" + clone + "\n"
                + ", sideCount=" + sideCount + "\n"
                + ", source=" + ((source == null) ? null : source.getId()) + "\n"
                + ", clones=" + ((clones == null) ? null : (clones.stream()
                                .map(clone -> String.valueOf(clone.getId()))
                                .collect(Collectors.joining(",", "{", "}")))) + "\n"
                + ", Obervers=" + ((this == null) ? null : (clones.stream()
                                .map(clone -> String.valueOf(clone.getId()))
                                .collect(Collectors.joining(",", "{", "}")))) + "\n"
                + '}';
    }

    /**
     * Getter of the number of sides
     * @return 
     */
    @Override
    public Integer getSides() {
        return sideCount;
    }
    
    

}
