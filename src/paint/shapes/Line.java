package paint.shapes;

import paint.Exceptions.ShapeException;

import java.awt.*;
import java.text.DecimalFormat;


/***
 * Class that extends the VectorGraphic class to create line shapes.
 */
public class Line extends VectorGraphic2Point {

    private static DecimalFormat df6 = new DecimalFormat("#.######");
    /***
     * Constructor created by an input string line for Line
     * @param line string that contains a line of data to create a Line
     * @param penColour Line pen colour
     * @param fillColor Line fill colour
     * @param width screen width to draw Line proportionally
     * @param height screen height to draw Line proportionally
     * @throws ShapeException Shape exceptions to handle poor user input files or null/0 errors.
     */
    public Line(String line, Color penColour, Color fillColor, Integer width, Integer height) throws ShapeException {
        String[] tokens = line.split(" ");
        this.type = SetType.LINE;

        //Check if height or width are 0 or null. Throw exception if they are.
        if (width == 0 || width == null || height == 0 || height == null)
        {
            throw new NullPointerException("Error: Screen width or Screen height are 0/null.");
        }

            //Check if the length of line load command is correct length. Throw exception if not.
            if (tokens.length != 5)
                throw new ShapeException("Line command has incorrect amount of values. Please correct file before \n" +
                        " loading again.");

            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i] == "") {
                    throw new ShapeException("Invalid data");
                }
            }

            if (tokens[0] != "LINE") {

            }
            //Assign value and check if it is a correct interpolation value. Throw exception if not.
            this.startX = Double.parseDouble(tokens[1]);
            if (this.startX < 0 || this.startX > 1)
                throw new ShapeException("Line start x-cord is incorrect. Value must be between +\n" +
                        "0 and 1.");


            //Assign value and check if it is a correct interpolation value. Throw exception if not.
            this.startY = Double.parseDouble(tokens[2]);
            if (this.startY < 0 || this.startY > 1)
                throw new ShapeException("Line start y-cord is incorrect. Value must be between +\n" +
                        "0 and 1.");


            //Assign value and check if it is a correct interpolation value. Throw exception if not.
            this.endX = Double.parseDouble(tokens[3]);
            if (this.endX < 0 || this.endX > 1)
                throw new ShapeException("Line end x-cord is incorrect. Value must be between +\n" +
                        "0 and 1.");


            //Assign value and check if it is a correct interpolation value. Throw exception if not.
            this.endY = Double.parseDouble(tokens[4]);
            if (this.endY < 0 || this.endY > 1)
                throw new ShapeException("Line end y-cord is incorrect. Value must be between +\n" +
                        "0 and 1.");


            this.stroke = penColour;
            this.fillColor = fillColor;

    }

    /***
     * Constructor to create a Line in the drawArea with mouse clicks
     * @param startX vector interpolation for start x point for drawing shapes
     * @param startY vector interpolation for end y point for drawing shapes
     * @param endX vector interpolation for start x point for drawing shapes
     * @param endY vector interpolation for end x point for drawing shapes
     * @param stroke pen colour for shape
     * @param fillColor fill colour for shape
     */
    public Line(double startX, double startY, double endX, double endY, Color stroke, Color fillColor) {

        this.stroke = stroke;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.type = SetType.LINE;
        this.fillColor = fillColor;
    }

//    @Override
//    public void onMousePress(MouseEvent e, Dimension screenSize){
//
//    }

                             /***
     * Overridden abstract draw method to draw the Line with class fields
     * @param g graphic that is passed into draw function
     * @param w width of screen
     * @param h height of screen
     */
                             @Override
    public void draw(Graphics2D g, int w, int h) {

        //Create the integer values for the shape to be used by the draw methods.
        int pixStartX = (int) (startX * (double) w);
        int pixStartY = (int) (startY * (double) h);
        int pixEndX = (int) (endX * (double) w);
        int pixEndY = (int) (endY * (double) h);

        //Setup line colour and stroke, then draw.
        this.strokeType = new BasicStroke(4.0F);
        g.setPaint(stroke);
        g.setStroke(strokeType);
        g.drawLine(pixStartX, pixStartY, pixEndX, pixEndY);

    }

    /***
     * Overridden abstract toString method from toString
     * @return string which is formatted to output Line shape to vector file
     */
    @Override
    public String toString() {
        //Format the Line shape to a vector file format to be used by save function
        String details = "LINE " + (df6.format(startX)) + " " +
                (df6.format(startY))  + " " +
                (df6.format(endX))  + " " +
                (df6.format(endY)) + "\n";
        return details;
    }


}
