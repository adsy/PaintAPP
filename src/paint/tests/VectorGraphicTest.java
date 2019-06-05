package paint.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.shapes.Line;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Tests for checking each setter method for Line, Rectangle, Plot (only the first 2 setters apply to plot)
 * and Ellipse. No need to check individual as these methods are inherited through extension of the abstract class
 * which is VectorGraphic
 */

class VectorGraphicTest {

    Line line;

    @BeforeEach
    void setup() {
        line = new Line(0.0,0.0,0.0,0.0, Color.white,Color.white);
    }

    @Test
    void setStartX() {
        line.setStartX(0.5);
        assertEquals(0.5,line.getStartX());
    }

    @Test
    void setEndX() {
        line.setEndX(0.5);
        assertEquals(0.5,line.getEndX());
    }

    @Test
    void setStartY() {
        line.setStartY(0.5);
        assertEquals(0.5,line.getStartY());
    }

    @Test
    void setEndY() {
        line.setEndY(0.5);
        assertEquals(0.5,line.getEndY());
    }
}