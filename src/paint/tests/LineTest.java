package paint.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.Exceptions.ShapeException;
import paint.shapes.Line;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
// Check constructor behavior with:
//      - null arguments
//      - invalid width and height (negative or 0)
// This is important because the line test code is separate from the GUI. API's *especially* need complete error handling.

class LineTest {

    Line line, line2;
    private Throwable exceptionCatcher;

    @BeforeEach
    public void Setup() throws ShapeException {
        line = new Line(0.0,0.0,0.0,0.0,Color.white, Color.white);
        line2 = new Line(1.0,0.0,0.0,0.0,Color.white, Color.white);
    }

    //Test 2 line constructors
    @Test
    public void Test2LineConstructors()
    {
        assertNotEquals(line.toString(),line2.toString());
    }

    //Test line toString
    @Test
    void TestToString() {
        line.setStartX(0.555555);
        line.setStartY(0.555555);
        line.setEndX(0.555555);
        line.setEndY(0.555555);
        String test = "LINE 0.555555 0.555555 0.555555 0.555555"+"\n";
        assertEquals(test,line.toString());
    }


    //Test incorrect amount of values in load file Exception
    @Test
    void TestIncValAmountExc() throws ShapeException {
        String strLine = "LINE 0.140278 0.292328 0.441799";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncValAmountText() throws  ShapeException {
        String strLine = "LINE 0.140278 0.292328 0.441799";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
        assertEquals("Line command has incorrect amount of values. Please correct file before \n" +
                " loading again.",exceptionCatcher.getMessage());
    }




    //Test incorrect values parsed in x < 0 || 1 < x for startX Exception
    @Test
    void TestIncStartXExc1() throws ShapeException {
        String strLine = "LINE 1.000001 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartXExc2() throws ShapeException {
        String strLine = "LINE -0.000001 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartXText() throws ShapeException {
        String strLine = "LINE 1.000001 0.0 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
        assertEquals("Line start x-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }




    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncStartYExc1() throws ShapeException {
        String strLine = "LINE 0.0 1.000001 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartYExc2() throws ShapeException {
        String strLine = "LINE 0.0 -0.000001 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartYText() throws ShapeException {
        String strLine = "LINE 0.0 1.000001 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
        assertEquals("Line start y-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }





    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncEndXExc1() throws ShapeException {
        String strLine = "LINE 0.0 0.0 1.000001 0.0";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndXxc2() throws ShapeException {
        String strLine = "LINE 0.0 0.0 -0.000001 0.0";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndXText() throws ShapeException {
        String strLine = "LINE 0.0 0.0 1.000001 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
        assertEquals("Line end x-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }





    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncEndYExc1() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 1.000001";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndYExc4() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 -0.000001";
        assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndYText() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 1.000001";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,800));
        assertEquals("Line end y-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }

    //Test edge case value of 1.0
    @Test
    void TestEdgeCaseValuesStartX() throws ShapeException {
        String strLine = "LINE 1.0 0.0 0.0 0.0";
        assertDoesNotThrow(()->line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesStartY() throws ShapeException {
        String strLine = "LINE 0.0 1.0 0.0 0.0";
        assertDoesNotThrow(()->line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesEndX() throws ShapeException {
        String strLine = "LINE 0.0 0.0 1.0 0.0";
        assertDoesNotThrow(()->line = new Line(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesEndY() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 1.0";
        assertDoesNotThrow(()->line = new Line(strLine,Color.white,Color.white,800,800));
    }

    @Test
    void TestNullWidth() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> line = new Line(strLine,Color.white,Color.white,null,800));
    }

    @Test
    void TestNullHeight() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,null));
    }

    @Test
    void TestZeroWidth() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> line = new Line(strLine,Color.white,Color.white,0,800));
    }

    @Test
    void TestZeroHeight() throws ShapeException {
        String strLine = "LINE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> line = new Line(strLine,Color.white,Color.white,800,0));
    }


}