package paint.shapes;

import paint.Exceptions.ShapeException;

import java.awt.*;
import java.text.DecimalFormat;

/***
 * Class that extends the VectorGraphic class to create plot shapes.
 */
public class Plot extends VectorGraphic2Point {

    private static DecimalFormat df6 = new DecimalFormat("#.######");

    /***
     * Constructor created by an input string line for plot
     * @param line string that contains a line of data to create a plot
     * @param penColour plot pen colour
     * @param fillColor plot fill colour
     * @param width screen width to draw plot proportionally
     * @param height screen height to draw plot proportionally
     * @throws ShapeException Shape exceptions to handle poor user input files or null/0 errors.
     */
    public Plot(String line, Color penColour, Color fillColor, Integer width,
                Integer height) throws ShapeException{
        super(line, penColour, fillColor, width, height);
        String[] tokens = line.split(" ");
        this.type = SetType.PLOT;

        //Check if height or width are 0 or null. Throw exception if they are.
        if (width == 0 || width == null || height == 0 || height == null)
        {
            throw new NullPointerException("Error: Screen width or Screen height are 0/null.");
        }

        //Check if the length of plot load command is correct length. Throw exception if not.
        if (tokens.length != 3)
            throw new ShapeException("Plot command has incorrect amount of values. Please correct file before \n" +
                    " loading again.");


        //Assign value and check if it is a correct interpolation value. Throw exception if not.
        this.startX = Double.parseDouble(tokens[1]);
        if (this.startX < 0 || this.startX > 1)
            throw new ShapeException("Plot x-cord is incorrect. Value must be between+\n" + "0 and 1.");


        //Assign value and check if it is a correct interpolation value. Throw exception if not.
        this.startY = Double.parseDouble(tokens[2]);
        if (this.startY < 0 || this.startY > 1)
            throw new ShapeException("Plot y-cord is incorrect. Value must be between+\n" +
                                            "0 and 1.");

        this.stroke = penColour;
        this.fillColor = fillColor;
    }

    /***
     * Constructor to create a plot in the drawArea with mouse clicks
     * @param startX vector interpolation for start x point for drawing shapes
     * @param startY vector interpolation for end y point for drawing shapes
     * @param endX vector interpolation for start x point for drawing shapes
     * @param endY vector interpolation for end x point for drawing shapes
     * @param stroke pen colour for shape
     * @param fillColor fill colour for shape
     */
    public Plot(double startX, double startY, double endX, double endY,
                Color stroke, Color fillColor) {
        this.stroke = stroke;
        this.startX = startX;
        this.startY = startY;
        this.type = SetType.PLOT;
        this.fillColor = fillColor;
    }

    /***
     * Overridden abstract draw method to draw the Plot with class fields
     * @param g graphic that is passed into draw function
     * @param w width of screen
     * @param h height of screen
     */
    @Override
    public void draw(Graphics2D g, int w, int h) {

        //Create the integer values for the shape to be used by the draw methods.
        int pixStartX = (int)(startX * (double)w);
        int pixStartY = (int)(startY * (double)h);
        int pixEndX = (int)(endX * (double)w);
        int pixEndY = (int)(endY * (double)h);

        //Setup plot colour and stroke, then draw.
        this.strokeType = new BasicStroke(4.0F);
        g.setPaint(stroke);
        g.setStroke(strokeType);
        g.drawLine(pixStartX, pixStartY, pixStartX, pixStartY);

    }

    /***
     * Overridden abstract toString method from toString
     * @return string which is formatted to output Plot shape to vector file
     */
    @Override
    public String toString() {
        //Format the Plot shape to a vector file format to be used by save function
        String details = "PLOT " + (df6.format(startX)) + " " +
                (df6.format(startY))+ "\n";;
        return details;
    }


}
