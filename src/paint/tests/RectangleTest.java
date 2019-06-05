package paint.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.Exceptions.ShapeException;
import paint.shapes.Rectangle;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    Rectangle rectangle, rectangle2;
    private Throwable exceptionCatcher;

    @BeforeEach
    public void Setup() throws ShapeException {
        rectangle = new Rectangle(0.0,0.0,0.0,0.0, Color.white, Color.white);
        rectangle2 = new Rectangle(1.0,0.0,0.0,0.0,Color.white, Color.white);
    }

    //Test 2 rectangle constructors
    @Test
    public void Test2RectangleConstructors()
    {
        assertNotEquals(rectangle.toString(),rectangle2.toString());
    }

    //Test rectangle toString
    @Test
    void TestToString() {
        rectangle.setStartX(0.555555);
        rectangle.setStartY(0.555555);
        rectangle.setEndX(0.555555);
        rectangle.setEndY(0.555555);
        String test = "RECTANGLE 0.555555 0.555555 0.555555 0.555555"+"\n";
        assertEquals(test,rectangle.toString());
    }

    //Test incorrect amount of values in load file Exception
    @Test
    void TestIncValAmountExc() throws ShapeException {
        String strRectangle = "RECTANGLE 0.140278 0.292328 0.441799";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncValAmountText() throws  ShapeException {
        String strRectangle = "RECTANGLE 0.140278 0.292328 0.441799";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
        assertEquals("Rectangle command has incorrect amount of values. Please correct file before \n" +
                " loading again.",exceptionCatcher.getMessage());
    }



    //Test incorrect values parsed in x < 0 || 1 < x for startX Exception
    @Test
    void TestIncStartXExc1() throws ShapeException {
        String strRectangle = "RECTANGLE 1.000001 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartXExc2() throws ShapeException {
        String strRectangle = "RECTANGLE -0.000001 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartXText() throws ShapeException {
        String strRectangle = "RECTANGLE 1.000001 0.0 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
        assertEquals("Rectangle start x-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }




    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncStartYExc1() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 1.000001 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartYExc2() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 -0.000001 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartYText() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 1.000001 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
        assertEquals("Rectangle start y-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }





    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncEndXExc1() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 0.0 1.000001 0.0";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndXxc2() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 0.0 -0.000001 0.0";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndXText() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 0.0 1.000001 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
        assertEquals("Rectangle end x-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }





    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncEndYExc1() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 0.0 0.0 1.000001";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndYExc4() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 0.0 0.0 -0.000001";
        assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndYText() throws ShapeException {
        String strRectangle = "RECTANGLE 0.0 0.0 0.0 1.000001";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> rectangle = new Rectangle(strRectangle,Color.white,Color.white,800,800));
        assertEquals("Rectangle end y-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }

    //Test edge case value of 1.0
    @Test
    void TestEdgeCaseValuesStartX() throws ShapeException {
        String strLine = "RECTANGLE 1.0 0.0 0.0 0.0";
        assertDoesNotThrow(()->rectangle= new Rectangle (strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesStartY() throws ShapeException {
        String strLine = "RECTANGLE 0.0 1.0 0.0 0.0";
        assertDoesNotThrow(()->rectangle = new Rectangle(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesEndX() throws ShapeException {
        String strLine = "RECTANGLE 0.0 0.0 1.0 0.0";
        assertDoesNotThrow(()->rectangle = new Rectangle(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesEndY() throws ShapeException {
        String strLine = "RECTANGLE 0.0 0.0 0.0 1.0";
        assertDoesNotThrow(()->rectangle = new Rectangle(strLine,Color.white,Color.white,800,800));
    }

    @Test
    void TestNullWidth() throws ShapeException {
        String strLine = "RECTANGLE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> rectangle = new Rectangle(strLine,Color.white,Color.white,null,800));
    }

    @Test
    void TestNullHeight() throws ShapeException {
        String strLine = "RECTANGLE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> rectangle = new Rectangle(strLine,Color.white,Color.white,800,null));
    }

    @Test
    void TestZeroWidth() throws ShapeException {
        String strLine = "RECTANGLE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> rectangle = new Rectangle(strLine,Color.white,Color.white,0,800));
    }

    @Test
    void TestZeroHeight() throws ShapeException {
        String strLine = "RECTANGLE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> rectangle = new Rectangle(strLine,Color.white,Color.white,800,0));
    }

}