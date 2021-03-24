package multiple.patterns.library.model;

/**
 * An object the defines the coordinates of the center of the shape 
 * and the relative coordinates in the case of a clone
 */
public class Coordinate {
    
    /**
     * The x coordinate of the center of the shape
     */
    private Integer x;
    /**
     * The y coordinate of the center of the shape
     */
    private Integer y;
    /**
     * The relative x coordinate of the center of the shape for clone shapes
     */
    private Integer xR=0;
    /** 
     * The relative y coordinate of the center of the shape for clone shapes
     */
    private Integer yR=0;

    /**
     * Create coordinates by x and y
     * @param x
     * @param y 
     * @param xR 
     * @param yR 
     */
    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of X
     */
    public Integer getX() {
        return x;
    }

    /**
     * Getter of Y
     */
    public Integer getY() {
        return y;
    }

    /**
     * Getter of relative X
     */
    public Integer getXR() {
        return xR;
    }

    /**
     * Getter of relative Y
     */
    public Integer getYR() {
        return yR;
    }

    /**
     * Setter of X
     * @param x 
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Setter of Y
     * @param y 
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * Setter of the relative X
     * @param xR 
     */
    public void setxR(Integer xR) {
        this.xR = xR;
    }

    /**
     * Setter of the relative Y
     * @param yR 
     */
    public void setyR(Integer yR) {
        this.yR = yR;
    }

    /**
     * String representation of the coordinate
     * @return 
     */
    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + ", xR=" + xR + ", yR=" + yR + '}';
    }
    
    /**
     * Inverts the coordinates for the undo of the move action
     * @return 
     */
    public Coordinate invertCoordinates(){
        Coordinate inverted = new Coordinate(-this.x, -this.y);
        inverted.setxR(this.xR);
        inverted.setyR(this.yR);
        return inverted;
    }
    
    
}
