package paint.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.Exceptions.ShapeException;
import paint.shapes.Plot;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlotTest {

    Plot plot, plot2;
    private Throwable exceptionCatcher;

    @BeforeEach
    public void Setup() throws ShapeException {
        plot = new Plot(0.0, 0.0, 0.0, 0.0, Color.white, Color.white);
        plot2 = new Plot(1.0, 0.0, 0.0, 0.0, Color.white, Color.white);
    }

    //Test 2 plot constructors
    @Test
    public void Test2PlotConstructors() {
        assertNotEquals(plot.toString(), plot2.toString());
    }

    //Test plot toString
    @Test
    void TestToString() {
        plot.setStartX(0.555555);
        plot.setStartY(0.555555);
        String test = "PLOT 0.555555 0.555555" + "\n";
        assertEquals(test, plot.toString());
    }

    //Test incorrect amount of values in load file Exception
    @Test
    void TestIncValAmountExc() throws ShapeException {
        String strLine = "PLOT 0.140278";
        assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
    }

    @Test
    void TestIncValAmountText() throws ShapeException {
        String strLine = "PLOT 0.140278";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
        assertEquals("Plot command has incorrect amount of values. Please correct file before \n" +
                " loading again.", exceptionCatcher.getMessage());
    }


    //Test incorrect values parsed in x < 0 || 1 < x for startX Exception
    @Test
    void TestIncStartXExc1() throws ShapeException {
        String strLine = "PLOT 1.000001 0.0";
        assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
    }

    @Test
    void TestIncStartXExc2() throws ShapeException {
        String strLine = "PLOT -0.000001 0.0";
        assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
    }

    @Test
    void TestIncStartXText() throws ShapeException {
        String strLine = "PLOT 1.000001 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
        assertEquals("Plot x-cord is incorrect. Value must be between+\n" +
                "0 and 1.", exceptionCatcher.getMessage());
    }


    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncStartYExc1() throws ShapeException {
        String strLine = "PLOT 0.0 1.000001";
        assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
    }

    @Test
    void TestIncStartYExc2() throws ShapeException {
        String strLine = "PLOT 0.0 -0.000001";
        assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
    }

    @Test
    void TestIncStartYText() throws ShapeException {
        String strLine = "PLOT 0.0 1.000001";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> plot = new Plot(strLine, Color.white, Color.white, 800, 800));
        assertEquals("Plot y-cord is incorrect. Value must be between+\n" +
                "0 and 1.", exceptionCatcher.getMessage());
    }

    //Test edge case value of 1.0
    @Test
    void TestEdgeCaseValuesStartX() throws ShapeException {
        String strLine = "PLOT 1.0 0.0";
        assertDoesNotThrow(()->plot = new Plot (strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesStartY() throws ShapeException {
        String strLine = "PLOT 0.0 1.0";
        assertDoesNotThrow(()->plot = new Plot(strLine,Color.white,Color.white,800,800));
    }

    @Test
    void TestNullWidth() throws ShapeException {
        String strLine = "PLOT 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> plot = new Plot(strLine,Color.white,Color.white,null,800));
    }

    @Test
    void TestNullHeight() throws ShapeException {
        String strLine = "PLOT 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> plot = new Plot(strLine,Color.white,Color.white,800,null));
    }

    @Test
    void TestZeroWidth() throws ShapeException {
        String strLine = "PLOT 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> plot = new Plot(strLine,Color.white,Color.white,0,800));
    }

    @Test
    void TestZeroHeight() throws ShapeException {
        String strLine = "PLOT 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> plot = new Plot(strLine,Color.white,Color.white,800,0));
    }

}