package paint.tests;

import org.junit.jupiter.api.*;
import paint.Exceptions.ShapeException;
import paint.shapes.Ellipse;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EllipseTest {

    Ellipse ellipse, ellipse2;
    private Throwable exceptionCatcher;

    @BeforeEach
    public void Setup() throws ShapeException {
        ellipse = new Ellipse(0.0,0.0,0.0,0.0,Color.white, Color.white);
        ellipse2 = new Ellipse(1.0,0.0,0.0,0.0,Color.white, Color.white);
    }

    //Test 2 ellipse constructors
    @Test
    public void Test2EllipseConstructors()
    {
        assertNotEquals(ellipse.toString(),ellipse2.toString());
    }

    //Test ellipse toString
    @Test
    void TestToString() {
        ellipse.setStartX(0.555555);
        ellipse.setStartY(0.555555);
        ellipse.setEndX(0.555555);
        ellipse.setEndY(0.555555);
        String test = "ELLIPSE 0.555555 0.555555 0.555555 0.555555"+"\n";
        assertEquals(test,ellipse.toString());
    }

    //Test incorrect amount of values in load file Exception
    @Test
    void TestIncValAmountExc() throws ShapeException {
        String strLine = "ELLIPSE 0.140278 0.292328 0.441799";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncValAmountText() throws  ShapeException {
        String strLine = "ELLIPSE 0.140278 0.292328 0.441799";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
        assertEquals("Ellipse command has incorrect amount of values. Please correct file before \n" +
                " loading again.",exceptionCatcher.getMessage());
    }



    //Test incorrect values parsed in x < 0 || 1 < x for startX Exception
    @Test
    void TestIncStartXExc1() throws ShapeException {
        String strLine = "ELLIPSE 1.000001 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartXExc2() throws ShapeException {
        String strLine = "ELLIPSE -0.000001 0.0 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartXText() throws ShapeException {
        String strLine = "ELLIPSE 1.000001 0.0 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
        assertEquals("Ellipse start x-cord is incorrect. Value must be between +\n" +
                                            "0 and 1.",exceptionCatcher.getMessage());
    }




    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncStartYExc1() throws ShapeException {
        String strLine = "ELLIPSE 0.0 1.000001 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartYExc2() throws ShapeException {
        String strLine = "ELLIPSE 0.0 -0.000001 0.0 0.0";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncStartYText() throws ShapeException {
        String strLine = "ELLIPSE 0.0 1.000001 0.0 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
        assertEquals("Ellipse start y-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }





    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncEndXExc1() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 1.000001 0.0";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndXxc2() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 -0.000001 0.0";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndXText() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 1.000001 0.0";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
        assertEquals("Ellipse end x-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }





    //Test incorrect values parsed in x < 0 || 1 < x for startY Exception
    @Test
    void TestIncEndYExc1() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 1.000001";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndYExc4() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 -0.000001";
        assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestIncEndYText() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 1.000001";
        exceptionCatcher = assertThrows(ShapeException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
        assertEquals("Ellipse end y-cord is incorrect. Value must be between +\n" +
                "0 and 1.",exceptionCatcher.getMessage());
    }

    //Test edge case value of 1.0
    @Test
    void TestEdgeCaseValuesStartX() throws ShapeException {
        String strLine = "ELLIPSE 1.0 0.0 0.0 0.0";
        assertDoesNotThrow(()->ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesStartY() throws ShapeException {
        String strLine = "ELLIPSE 0.0 1.0 0.0 0.0";
        assertDoesNotThrow(()->ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesEndX() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 1.0 0.0";
        assertDoesNotThrow(()->ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }
    @Test
    void TestEdgeCaseValuesEndY() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 1.0";
        assertDoesNotThrow(()->ellipse = new Ellipse(strLine,Color.white,Color.white,800,800));
    }


    @Test
    void TestNullWidth() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,null,800));
    }

    @Test
    void TestNullHeight() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,null));
    }

    @Test
    void TestZeroWidth() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,0,800));
    }

    @Test
    void TestZeroHeight() throws ShapeException {
        String strLine = "ELLIPSE 0.0 0.0 0.0 1.0";
        assertThrows(NullPointerException.class,
                () -> ellipse = new Ellipse(strLine,Color.white,Color.white,800,0));
    }
}