package paint.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.Exceptions.ShapeException;
import paint.shapes.Polygon;
import paint.shapes.Rectangle;
import paint.shapes.*;
import paint.ui.view.DrawArea;
import paint.ui.view.PaintGUI;

import java.awt.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaintGUITest {
    private Throwable exceptionCatcher;

    PaintGUI paintGUI;

    FileInputStream fis;
    DataInputStream in;
    BufferedReader br;


    @BeforeEach
    public void Setup(){
      paintGUI = new PaintGUI();
      paintGUI.drawArea = new DrawArea();
      paintGUI.drawArea.setSize(800,800);
      fis = null;
      in = null;
      br = null;
    }


    @Test
    void TestLoadIncCommandFile() throws IOException {
        //newWindow.drawArea = new DrawArea();
        fis = new FileInputStream("src/incCommandFile.vec");
        in = new DataInputStream(fis);
        br = new BufferedReader(new InputStreamReader(in));
        exceptionCatcher = assertThrows(IOException.class,
                () -> paintGUI.load(br));
        assertEquals("VEC FILE CONTAINS ERRORS - commands not recognised.",exceptionCatcher.getMessage());
    }

    @Test
    void TestLoadNoString() throws IOException {
        //newWindow.drawArea = new DrawArea();
        fis = new FileInputStream("src/noStringError.vec");
        in = new DataInputStream(fis);
         br = new BufferedReader(new InputStreamReader(in));
        exceptionCatcher = assertThrows(IOException.class,
                () -> paintGUI.load(br));
        assertEquals("VEC FILE CONTAINS ERRORS - commands not recognised.",exceptionCatcher.getMessage());
    }
    @Test
    void TestLoadIncStringError() throws NumberFormatException, FileNotFoundException {
        //newWindow.drawArea = new DrawArea();
        fis = new FileInputStream("src/incStringError.vec");
        in = new DataInputStream(fis);
        br = new BufferedReader(new InputStreamReader(in));
        assertThrows(NumberFormatException.class,
                () -> paintGUI.load(br));

    }

    @Test
    void TestSaveLine() throws IOException, ShapeException {
        String test = "LINE 0.555555 0.555555 0.555555 0.555555";
        Line line = new Line(test, Color.white,Color.white,800,800);
        File fileToSave = new File("src/testSave.vec");

        FileWriter fileWriter;
        try {
            //Create a new file to write to
            fileWriter = new FileWriter(fileToSave);
            fileWriter.write(line.toString());
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        fis = new FileInputStream("src/testSave.vec");
        in = new DataInputStream(fis);
        br = new BufferedReader(new InputStreamReader(in));
        String strLine = br.readLine();
        assertEquals(test,strLine);
    }

    @Test
    void TestSaveEllipse() throws IOException, ShapeException {
        String test = "ELLIPSE 0.555555 0.555555 0.555555 0.555555";
        Ellipse ellipse = new Ellipse(test, Color.white,Color.white,800,800);
        paintGUI.drawArea.addToGraphics(ellipse);
        File fileToSave = new File("src/testSave.vec");

        FileWriter fileWriter;
        try {
            //Create a new file to write to
            fileWriter = new FileWriter(fileToSave);

            fileWriter.write(ellipse.toString());
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        fis = new FileInputStream("src/testSave.vec");
        in = new DataInputStream(fis);
        br = new BufferedReader(new InputStreamReader(in));
        String strLine = br.readLine();
        assertEquals(test,strLine);
    }

    @Test
    void TestSavePlot() throws IOException, ShapeException {
        String test = "PLOT 0.555555 0.555555";
        Plot plot = new Plot(test, Color.white,Color.white,800,800);
        File fileToSave = new File("src/testSave.vec");

        FileWriter fileWriter;
        try {
            //Create a new file to write to
            fileWriter = new FileWriter(fileToSave);

            fileWriter.write(plot.toString());
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        fis = new FileInputStream("src/testSave.vec");
        in = new DataInputStream(fis);
        br = new BufferedReader(new InputStreamReader(in));
        String strLine = br.readLine();
        assertEquals(test,strLine);
    }

    @Test
    void TestSaveRectangle() throws IOException, ShapeException {
        String test = "RECTANGLE 0.555555 0.555555 0.555555 0.555555";
        Rectangle rectangle = new Rectangle(test, Color.white,Color.white,800,800);
        File fileToSave = new File("src/testSave.vec");

        FileWriter fileWriter;
        try {
            //Create a new file to write to
            fileWriter = new FileWriter(fileToSave);

            fileWriter.write(rectangle.toString());
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        fis = new FileInputStream("src/testSave.vec");
        in = new DataInputStream(fis);
        br = new BufferedReader(new InputStreamReader(in));
        String strLine = br.readLine();
        assertEquals(test,strLine);
    }

    @Test
    void TestSavePolygon() throws IOException, ShapeException {
        String test = "POLYGON 0.11 0.11 0.11 0.555 0.3325 0.3325";
        Polygon poly = new Polygon(test,Color.white,Color.white,800,800);
        File fileToSave = new File("src/testSave.vec");

        FileWriter fileWriter;
        try {
            //Create a new file to write to
            fileWriter = new FileWriter(fileToSave);

            fileWriter.write(poly.toString());
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        fis = new FileInputStream("src/testSave.vec");
        in = new DataInputStream(fis);
        br = new BufferedReader(new InputStreamReader(in));
        String strLine = br.readLine();
        assertEquals(test,strLine);
    }
}