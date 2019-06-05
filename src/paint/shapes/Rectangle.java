package paint.shapes;

import paint.Exceptions.ShapeException;

import java.awt.*;
import java.text.DecimalFormat;


/***
 * Class that extends the VectorGraphic class to create Rectangle shapes.
 */
public class Rectangle extends VectorGraphic2Point {

    private static DecimalFormat df6 = new DecimalFormat("#.######");
    /***
     * Constructor created by an input string line for Rectangle
     * @param line string that contains a line of data to create a Rectangle
     * @param penColour Rectangle pen colour
     * @param fillColor Rectangle fill colour
     * @param width screen width to draw Rectangle proportionally
     * @param height screen height to draw Rectangle proportionally
     * @throws ShapeException Shape exceptions to handle poor user input files or null/0 errors.
     */
    public Rectangle(String line, Color penColour, Color fillColor, Integer width, Integer height) throws ShapeException {
        super(line, penColour, fillColor, width, height);
        String[] tokens = line.split(" ");
        this.type = SetType.RECTANGLE;

        //Check if height or width are 0 or null. Throw exception if they are.
        if (width == 0 || width == null || height == 0 || height == null)
        {
            throw new NullPointerException("Error: Screen width or Screen height are 0/null.");
        }

        //Check if the length of rectangle load command is correct length. Throw exception if not.
        if (tokens.length != 5)
            throw new ShapeException("Rectangle command has incorrect amount of values. Please correct file before \n" +
                    " loading again.");


        //Assign value and check if it is a correct interpolation value. Throw exception if not.
        this.startX = Double.parseDouble(tokens[1]);
        if (this.startX < 0 || this.startX > 1)
            throw new ShapeException("Rectangle start x-cord is incorrect. Value must be between +\n" +
                                            "0 and 1.");


        //Assign value and check if it is a correct interpolation value. Throw exception if not.
        this.startY = Double.parseDouble(tokens[2]);
        if (this.startY < 0 || this.startY > 1)
            throw new ShapeException("Rectangle start y-cord is incorrect. Value must be between +\n" +
                                            "0 and 1.");


        //Assign value and check if it is a correct interpolation value. Throw exception if not.
        this.endX = Double.parseDouble(tokens[3]);
        if (this.endX < 0 || this.endX > 1)
            throw new ShapeException("Rectangle end x-cord is incorrect. Value must be between +\n" +
                                            "0 and 1.");


        //Assign value and check if it is a correct interpolation value. Throw exception if not.
        this.endY = Double.parseDouble(tokens[4]);
        if (this.endY < 0 || this.endY > 1)
            throw new ShapeException("Rectangle end y-cord is incorrect. Value must be between +\n" +
                                            "0 and 1.");


        this.stroke = penColour;
        this.fillColor = fillColor;
    }

    /***
     * Constructor to create a Rectangle in the drawArea with mouse clicks
     * @param startX vector interpolation for start x point for drawing shapes
     * @param startY vector interpolation for end y point for drawing shapes
     * @param endX vector interpolation for start x point for drawing shapes
     * @param endY vector interpolation for end x point for drawing shapes
     * @param stroke pen colour for shape
     * @param fillColor fill colour for shape
     */

    //Color stroke, Color fillColor
    public Rectangle(double startX, double startY, double endX, double endY, Color stroke, Color fillColor) {
        super(startX, startY, endX, endY);
        this.stroke = stroke;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.type = SetType.RECTANGLE;
        this.fillColor = fillColor;
    }

    /***
     * Overridden abstract draw method to draw the Rectangle with class fields
     * @param g graphic that is passed into draw function
     * @param w width of screen
     * @param h height of screen
     */
    @Override
    public void draw(Graphics2D g, int w, int h) {

        //Setup rectangle colour and stroke
        this.strokeType = new BasicStroke(4.0F);
        g.setPaint(stroke);
        g.setStroke(strokeType);

        //Create the integer values for the shape to be used by the draw methods.
        int pixStartX = (int) (startX * (double) w);
        int pixStartY = (int) (startY * (double) h);
        int pixEndX = (int) (endX * (double) w);
        int pixEndY = (int) (endY * (double) h);
        int rectW = Math.abs(pixStartX - pixEndX);
        int rectH = Math.abs(pixStartY - pixEndY);

        //Set the starting x and y values depending on which direction the shape is drawn
        if (pixStartX > pixEndX) {
            pixStartX = pixEndX;
        }

        if (pixStartY > pixEndY) {
            pixStartY = pixEndY;
        }

        //Draw the outline, and if the fillColour is not null draw the shape filled.
        g.drawRect(pixStartX, pixStartY, rectW, rectH);
        if (fillColor != null) {
            g.setColor(fillColor);
            g.fillRect(pixStartX, pixStartY, rectW, rectH);
        }

    }

    /***
     * Overridden abstract toString method from toString
     * @return string which is formatted to output Rectangle shape to vector file
     */
    @Override
    public String toString() {
        //Format the Rectangle shape to a vector file format to be used by save function
        String details = "RECTANGLE " + (df6.format(startX)) + " " +
                (df6.format(startY)) + " " +
                (df6.format(endX)) + " " +
                (df6.format(endY)) + "\n";
        return details;
    }
}