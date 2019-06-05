package paint.shapes;
import java.awt.*;
import java.awt.event.MouseEvent;


/***
 * Abstract class that sets up the methods for the shape classes that will extend this class
 */
public abstract class VectorGraphic {
    protected SetType type;
    protected Color fillColor;
    protected Color stroke;
    protected Stroke strokeType;
    protected boolean drawPoly;

    public VectorGraphic(SetType type, Color fillColor, Color stroke, Stroke strokeType)
    {
        this.type = type;
        this.fillColor = fillColor;
        this.stroke = stroke;
        this.strokeType = strokeType;
    }

    /***
     * Abstract constructor for creating shapes based off a string
     * @param line string parameter which holds a line from an input file
     * @param penColor pen colour for shape
     * @param fillColor fill colour for shape
     * @param width width of screen to properly draw shape based off screen size
     * @param height height of screen to properly draw shape based off screen size
     */
    public VectorGraphic(String line, Color penColor, Color fillColor, Integer width, Integer height) {}

    /***
     * Basic constructor for vector graphics
     */
    protected VectorGraphic() {
    }

    /***
     * abstract draw method for each shape that is called from paintComponent in drawArea
     * @param g graphic that is passed into draw function
     * @param w width of screen
     * @param h height of screen
     */
    public abstract void draw(Graphics2D g, int w, int h);

    /***
     * abstract method for each shape to create a format to save the vector to a file
     * @return returns a string
     */
    public abstract String toString();

    /***
     * getter method for each shape to get fill colour
     * @return colour variable
     */
    public Color getFillColour() {
        return fillColor;
    }

    /***
     * getter method for each shape to get colour
     * @return colour variable
     */
    public Color getColour() {
        return stroke;
    }

    /**
     * Abstract method mouse clicks for each shape to expand upon
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    public abstract void onMousePress(MouseEvent e, Dimension screenSize);

    /**
     * Abstract method mouse clicks for each shape to expand upon
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    public abstract void onMouseDrag(MouseEvent e, Dimension screenSize);

    /**
     * Abstract method mouse clicks for each shape to expand upon
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    public abstract void onMouseRelease(MouseEvent e, Dimension screenSize);

}
