
    /*
    Number of tokens when splitting (ie test for when the user has an incorrect number or ' ' between value
    TODO: WITHIN THIS CLASS: Catch a number format exception when parsing, then throw a shape format exception from the catch block
     */


package paint.shapes;

        import paint.Exceptions.ShapeException;
        import java.awt.*;
        import java.awt.event.MouseEvent;
        import java.text.DecimalFormat;
        import java.util.ArrayList;

/***
 * Class that extends the VectorGraphic class to create polygon shapes with 3 or more vertices.
 */
public class Polygon extends VectorGraphic{

    private static DecimalFormat df6 = new DecimalFormat("#.######");
    private ArrayList<Integer> xCoords,yCoords;
    //Used for testing purposes of toString.
    private int screenWidth, screenHeight;

    protected int[] polyXCoords,polyYCoords;
    protected int polyVertCount;
    /***
     * Constructor to create Polygon shapes in the drawArea with mouseclicks
     * @param intXCoords int array of x coords of each vertex in the polygon
     * @param intYCoords int array of y coords of each vertex in the polygon
     * @param numPoints integer of the amount of vertexs in polygon
     * @param stroke polygon pen colour
     * @param fillColor polygon fill colour
     */
    public Polygon(int[] intXCoords,int[] intYCoords,int numPoints,Color stroke,Color fillColor){
        
        this.type = SetType.POLYGON;
        this.stroke = stroke;
        this.fillColor = fillColor;
        this.polyXCoords = intXCoords;
        this.polyYCoords = intYCoords;
        this.polyVertCount = numPoints;

    }


    /***
     * Constructor created by an input string line for polygon
     * @param strLine string that contains a line of data to create a polygon
     * @param penColour polygon pen colour
     * @param fillColour polygon fill colour
     * @param width screen width to draw polygon proportionally
     * @param height screen height to draw polygon proportionally
     * @throws ShapeException for coords outside of x,y less than 0 or x,y greater than 1
     */
    public Polygon(String strLine, Color penColour, Color fillColour, Integer width,Integer height)
            throws ShapeException {
        super(strLine, penColour, fillColour, width, height);
        //Split the input into an array of strings
        String[] tokens = strLine.split(" ");

        //Check if height or width are 0 or null. Throw exception if they are.
        if (width == 0 || width == null || height == 0 || height == null)
        {
            throw new NullPointerException("Error: Screen width or Screen height are 0/null.");
        }

        //Set up then polygon sharp and x+y coords
        this.type = SetType.POLYGON;
        this.stroke = penColour;
        this.fillColor = fillColour;
        this.xCoords = new ArrayList<>();
        this.yCoords = new ArrayList<>();
        this.screenHeight = height;
        this.screenWidth = width;




        //From index 1 onwards, if i mod 2 != 0 it must be a y coord, parse value in and add to arrayList
        try {
            if(tokens != null){
                for (int i = 0; i < tokens.length; i++) {
                    if (i % 2 == 0 && i != 0) {
                        double yValue = Double.parseDouble(tokens[i]);
                        if (yValue < 0 || 1 < yValue)
                            throw new ShapeException("poly y-cord is incorrect. Value must be between \n" +
                                    "0 and 1.");
                        int intYValue = (int) (yValue * (double) height);
                        this.yCoords.add(intYValue);
                    }
                    //if i mod 2 = 0 it must be a x coord, parse value in and add to arrayList
                    else if (i % 2 != 0) {
                        double xValue = Double.parseDouble(tokens[i]);
                        if (xValue < 0 || 1 < xValue)
                            throw new ShapeException("poly x-cord is incorrect. Value must be between \n" +
                                    "0 and 1.");
                        int intXValue = (int) (xValue * (double) width);
                        this.xCoords.add(intXValue);
                    }
                }
            }
            else{
                throw new NumberFormatException("No Tokens found");
            }


        }catch(NumberFormatException ne){
                ne.printStackTrace();
        }


        if (this.xCoords.size() < 3 || this.yCoords.size() < 3)
        {
            throw new ShapeException("Polygon that was passed in has less than 3 vertices.");
        }


        //Throw exception if there was x-y co
        if (this.xCoords.size() != this.yCoords.size() )
            throw new ShapeException("Polygon that was passed in does not have an \n" +
                    "equal amount of x-cords and y-cords");


        //Initialise integer arrays with size of arraylist
        this.polyXCoords = new int[xCoords.size()];
        this.polyYCoords = new int[yCoords.size()];
        //Fill integer array for x+y coords with data from arrayLists
        for(int i = 0 ;i < xCoords.size();i++) {
            polyXCoords[i] = this.xCoords.get(i);
            polyYCoords[i] = this.yCoords.get(i);
        }
        //Set the polyVertCount to the length of the coords
        this.polyVertCount = polyXCoords.length;
    }
    /***
     * Overridden abstract draw method to draw the polygon with class fields
     * @param g graphic that is passed into draw function
     * @param w width of screen
     * @param h height of screen
     */
    @Override
    public void draw(Graphics2D g,int w,int h){
        this.strokeType=new BasicStroke(4.0F);
        g.setPaint(stroke);
        g.setStroke(strokeType);
        g.drawPolygon(polyXCoords,polyYCoords,polyVertCount);
        g.setColor(fillColor);
        //If fill colour is not null, draw Polygon with fill
        if(fillColor!=null)
            g.fillPolygon(polyXCoords,polyYCoords,polyVertCount);
    }

    /***
     * Overridden abstract toString method from toString
     * @return string which is formatted to output Polygon shape to vector file
     */
    @Override
    public String toString(){
        //Initalize string with set type indicator
        String details="POLYGON";
        //Add x and y vector interpolations to the string for each entry in either x or y coords int array
        for (int i = 0; i < polyXCoords.length; i++)
            details = details.concat(" " + (df6.format((double) polyXCoords[i] / this.screenWidth)) + " " + (df6.format((double) polyYCoords[i] / this.screenHeight)));

        //Add a new line character and return it
        details = details.concat("\n");
        return details;
    }
    /**
     * Abstract method mouse clicks for each shape to expand upon
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    @Override
    public void onMousePress(MouseEvent e, Dimension screenSize) {

    }
    /**
     * Abstract method mouse clicks for each shape to expand upon
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    @Override
    public void onMouseDrag(MouseEvent e, Dimension screenSize) {

    }
    /**
     * Abstract method mouse clicks for each shape to expand upon
     * @param e mouseEvent which can be used to manipulate coords
     * @param screenSize screenSize to build shapes
     */
    @Override
    public void onMouseRelease(MouseEvent e, Dimension screenSize) {

    }

    /***
     * Method used when loading a polygon via an input file
     * @param w width of the screen
     * @param h height of the screen
     */
    public void setScreenWidthHeight (int w, int h) {
        this.screenWidth = w;
        this.screenHeight = h;

    }
}

