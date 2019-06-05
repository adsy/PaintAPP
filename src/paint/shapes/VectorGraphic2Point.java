package paint.shapes;

import java.awt.*;
import java.awt.event.MouseEvent;

/***
 * Abstract class that sets up the methods for the shape classes that will extend this class
 */
public abstract class VectorGraphic2Point extends VectorGraphic {

    protected double startX, startY, endX, endY;

    /***
     *
     * Abstract constructor for creating all shapes except polygon
     * @param startX vector interpolation for start x point for drawing shapes
     * @param startY vector interpolation for end y point for drawing shapes
     * @param endX vector interpolation for start x point for drawing shapes
     * @param endY vector interpolation for end x point for drawing shapes
     */
    public VectorGraphic2Point(double startX, double startY, double endX, double endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

    }

    /***
     * Abstract constructor for creating shapes based off a string
     * @param line string parameter which holds a line from an input file
     * @param penColor pen colour for shape
     * @param fillColor fill colour for shape
     * @param width width of screen to properly draw shape based off screen size
     * @param height height of screen to properly draw shape based off screen size
     */
    public VectorGraphic2Point(String line, Color penColor, Color fillColor, Integer width, Integer height) {}

    /***
     * Basic constructor for 2 point vector graphics
     */
    protected VectorGraphic2Point() {
    }

    /***
     * setter method for each shape to set start x vector interpolation
     * @param x vector interpolation value
     */
    public void setStartX(double x) {
        this.startX = x;
    }

    /***
     * setter method for each shape to set end x vector interpolation
     * @param x vector interpolation value
     */
    public void setEndX(double x) {
        this.endX = x;
    }

    /***
     * setter method for each shape to set start y vector interpolation
     * @param y vector interpolation value
     */
    public void setStartY(double y) {
        this.startY = y;
    }


    /***
     * setter method for each shape to set end y vector interpolation
     * @param y vector interpolation value
     */
    public void setEndY(double y) {
        this.endY = y;
    }



    /***
     * getter method to return start x value - Testing
     * @return start x vector interpolation
     */
    public double getStartX() {return startX; }

    /***
     * getter method to return end x value - Testing
     * @return end x vector interpolation
     */
    public double getEndX() {return endX; }

    /***
     * getter method to return start y value - Testing
     * @return start y vector interpolation
     */
    public double getStartY() {return startY; }

    /***
     * getter method to return end y value - Testing
     * @return end y vector interpolation
     */
    public double getEndY() {return endY; }

    /***
     * Mouse press method that listens for a click and sets the start and end x,y vector points.
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    public void onMousePress(MouseEvent e, Dimension screenSize)
    {
        setStartX((double)e.getPoint().x / (double)screenSize.width);
        setStartY((double)e.getPoint().y /(double)screenSize.height);
        setEndX((double)e.getPoint().x / (double)screenSize.width);
        setEndY((double)e.getPoint().y / (double)screenSize.height);
    }

    /***
     * Mouse drag method that listens for a drag and sets the start and end x,y vector points.
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    public void onMouseDrag(MouseEvent e, Dimension screenSize){
        setEndX((double)e.getPoint().x / (double)screenSize.width);
        setEndY((double)e.getPoint().y / (double)screenSize.height);
    }

    /***
     * Mouse release method that listens for a release and sets the start and end x,y vector points.
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    public void onMouseRelease(MouseEvent e, Dimension screenSize){
        setEndX((double)e.getPoint().x / (double)screenSize.width);
        setEndY((double)e.getPoint().y / (double)screenSize.height);

    }
}
