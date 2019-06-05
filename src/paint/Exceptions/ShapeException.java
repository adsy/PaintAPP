package paint.Exceptions;

/***
 * Exceptions for Polygon shape
 */
public class ShapeException extends Exception {

    /***
     * Polygon exception constructor
     * @param message that is returned to user describing error
     */
    public ShapeException(String message) {
        super(message);
    }

}
