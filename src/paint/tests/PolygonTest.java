package paint.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.Exceptions.ShapeException;
import paint.shapes.Polygon;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTest {

    Polygon poly, poly2;
    private Throwable exceptionCatcher;
    private int[] xCords, yCords, yCords2;
    private double[] xVec, yVec;
    private int screenW, screenH;

    @BeforeEach
    public void Setup() throws ShapeException {

        xCords = new int[3];
        yCords = new int[3];
        yCords2 = new int[3];
        xVec = new double[3];
        yVec = new double[3];
        screenW = 800;
        screenH = 800;

        xCords[0]=100;
        xCords[1]=100;
        xCords[2]=300;

        yCords[0]=500;
        yCords[1]=100;
        yCords[2]=300;

        yCords[0]=300;
        yCords[1]=200;
        yCords[2]=400;

        poly = new Polygon(xCords,yCords,3,Color.white,Color.white);
        poly2 = new Polygon(xCords,yCords2,3,Color.white,Color.white);
        poly.setScreenWidthHeight(800,800);
        poly2.setScreenWidthHeight(800,800);
    }

    //Test 2 line constructors
    @Test
    public void Test2PolygonConstructors()
    {
        assertNotEquals(poly.toString(),poly2.toString());
    }

    //Test line toString
    @Test
    void TestToString() {
        //use the screenwidth to divide the integer values in the constructor
        for (int i = 0; i < xVec.length; i++)
        {
            xVec[i] = (double) xCords[i]/screenW;
            yVec[i] = (double) yCords[i]/screenH;
        }

        String test = "POLYGON "+xVec[0] + " " +yVec[0] +" "
                +xVec[1] + " " +yVec[1] + " "
                +xVec[2] + " " +yVec[2] + "\n";
        assertEquals(test,poly.toString());

        // TODO: Test again after changing the size of the screen. This tests to see if the same object has different output when the screen changes. It must be the same
    }

    @Test
    void TestIncValAmountExc() throws ShapeException {
        String strLine = "POLYGON 0.140278 0.292328 0.441799 0.441799";
        assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
    }

    @Test
    void TestIncValAmountExc2() throws ShapeException {
        String strLine = "POLYGON 0.140278 0.292328 0.441799 0.292328 0.441799 0.292328 0.441799";
        assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
    }

    @Test
    void TestIncValAmountText() throws ShapeException {
        String strLine = "POLYGON 0.5 0.5 0.5 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
        assertEquals("Polygon that was passed in has less than 3 vertices.", exceptionCatcher.getMessage());
    }

    @Test
    void TestIncValAmountText2() throws ShapeException {
        String strLine = "POLYGON 0.5 0.5 0.5 0.0 0.0 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
        assertEquals("Polygon that was passed in does not have an \n" +
                "equal amount of x-cords and y-cords", exceptionCatcher.getMessage());
    }


    //Test for incorrect values in the x-cord locations
    @Test
    void TestIncXExc1() throws ShapeException {
        String strLine = "POLYGON 1.000001 0.0 0.0 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncXExc2() throws ShapeException {
        String strLine = "POLYGON -0.000001 0.0 0.0 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncXText() throws ShapeException {
        String strLine = "POLYGON 1.000001 0.0 0.0 0.0 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
        assertEquals("poly x-cord is incorrect. Value must be between \n" +
                        "0 and 1.",exceptionCatcher.getMessage());
    }

    //Test for incorrect values in the y-cord locations
    @Test
    void TestIncYExc1() throws ShapeException {
        String strLine = "POLYGON 0.0 1.000001 0.0 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncYExc2() throws ShapeException {
        String strLine = "POLYGON 0.0 -0.000001 0.0 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncYText() throws ShapeException {
        String strLine = "POLYGON 0.0 1.000001 0.0 0.0 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,800));
        assertEquals("poly y-cord is incorrect. Value must be between \n" +
                            "0 and 1.",exceptionCatcher.getMessage());
    }

    @Test
    void TestNullWidth() throws ShapeException {
        String strLine = "POLYGON 0.0 0.0 0.0 1.0 0.0 0.0";
        assertThrows(NullPointerException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,null,800));
    }

    @Test
    void TestNullHeight() throws ShapeException {
        String strLine = "POLYGON 0.0 0.0 0.0 1.0 0.0 0.0";
        assertThrows(NullPointerException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,null));
    }

    @Test
    void TestZeroWidth() throws ShapeException {
        String strLine = "POLYGON 0.0 0.0 0.0 1.0 0.0 0.0";
        assertThrows(NullPointerException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,0,800));
    }

    @Test
    void TestZeroHeight() throws ShapeException {
        String strLine = "POLYGON 0.0 0.0 0.0 1.0 0.0 0.0";
        assertThrows(NullPointerException.class,
                () -> poly = new Polygon(strLine,Color.white,Color.white,800,0));
    }
}
