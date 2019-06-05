package paint.shapes;


/***
 * ENUM class that creates set types to determine which shape to draw.
 */

public enum SetType {

    PLOT,
    ELLIPSE,
    LINE,
    POLYGON,
    RECTANGLE;

    /***
     * Overridden toString that returns the enum as a string.
     * @return string value of type
     */
    @Override
    public String toString()
    {
        String type = new String();
        switch(this)
        {
            case LINE:
                type = "Line";
                break;
            case PLOT:
                type = "Plot";
                break;
            case ELLIPSE:
                type ="Ellipse";
                break;
            case POLYGON:
                type = "Polygon";
                break;
            case RECTANGLE:
                type ="Rectangle";
                break;
        }
        return type;
    }
}
