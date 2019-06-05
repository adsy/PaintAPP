package paint.ui.view;


import paint.shapes.Polygon;
import paint.shapes.Rectangle;
import paint.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;




/***
 * Class that is the container for the drawing area which listens for input from other classes and outputs drawings
 * on the panel.
 *
 * TODO: Test undo / redo
 */

public class DrawArea extends JPanel {

    private int width;
    private int height;
    private Color colour;
    private Color strokeColor;
    private SetType currentType;
    private Stack<VectorGraphic> vecGraphics;
    private Stack<VectorGraphic> redoStack = new Stack<>();
    private VectorGraphic currentGraphic = null;
    private int polyVertCount;
    private int[] xCoords, yCoords;
    private ArrayList<Integer> intXCoords, intYCoords;

    /**
     * Create DrawArea and initalise all the class fields. Add mouse adapter to listen for clicks and create shapes to
     * add to the vectorGraphic stack to be drawn.
     */

    public DrawArea() {
        //This is place holder code, it sets the values for the draw area as they are
        //currently null. We have to figure out why the values are being set as null
        //and make it set the values to the appropriate size.
        setVisible(true);
        this.colour = null;
        this.strokeColor = Color.BLACK;
        this.setBackground(Color.WHITE);
        this.currentType = SetType.LINE;
        this.setSize(width,height);
        this.setLayout(new GridLayout());
        vecGraphics = new Stack<>();
        intXCoords = new ArrayList<>();
        intYCoords = new ArrayList<>();

        //Code to print out the x and y cords to terminal, used for testing purposes.
        MouseAdapter adapter = new MouseAdapter() {
            /***
             * MousePressed event listens for what currentType is set, and creates a vector with the starting values
             * @param e
             */
            public void mousePressed(MouseEvent e) {
                //Based off the current type, run switch statement checking which one user selects.
                switch (currentType) {
                    //If type is Line, create new Line constructor
                    case LINE:
                            currentGraphic = new Line(0.0, 0.0, 0.0, 0.0, strokeColor, colour);
                        // Convert the event's point from integer pixel coords to vector interpolation
                        break;
                    //If type is Rectangle, create new Rectangle constructor
                    case RECTANGLE:
                        currentGraphic = new Rectangle(0.0, 0.0, 0.0, 0.0, strokeColor, colour);
                        break;
                    //If type is Plot, create new Plot constructor
                    case PLOT:
                        currentGraphic = new Plot(0.0, 0.0, 0.0, 0.0, strokeColor, colour);
                        break;
                    //If type is Ellipse, create new Ellipse constructor
                    case ELLIPSE:
                        currentGraphic = new Ellipse(0.0, 0.0, 0.0, 0.0, strokeColor, colour);
                        break;
                    //If type is Polygon AND:
                    case POLYGON:
                        //if left mouse button is clicked, add x + y coords to arraylists and increment value
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            intXCoords.add(e.getX());
                            intYCoords.add(e.getY());
                            polyVertCount++;
                        }
                        //if right mouse button is clicked, convert values to integer array and pass to constructor
                        //with vertCount and colour
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            xCoords = new int[intXCoords.size()];
                            yCoords = new int[intYCoords.size()];

                            for(int i=0;i<intXCoords.size();i++) {
                                xCoords[i] = intXCoords.get(i);
                                yCoords[i] = intYCoords.get(i);
                            }

                            Polygon poly = new Polygon(xCoords, yCoords, polyVertCount, strokeColor, colour);
                            poly.setScreenWidthHeight(getWidth(), getHeight());
                            vecGraphics.add(poly);

                            System.out.print(poly);
                            currentGraphic = null;
                            polyVertCount = 0;
                            intXCoords.clear();
                            intYCoords.clear();
                            repaint();
                        }
                        break;
//                        currentGraphic = new Polygon(xCoords, yCoords, polyVertCount, strokeColor, colour);
//                        break;
                }
                if(currentGraphic != null) {
                    currentGraphic.onMousePress(e, getSize());
                    vecGraphics.add(currentGraphic);
                    repaint();
                }

            }

            /***
             * MouseDragged method that listens for when the mouse is dragged on the screen and sets the end values
             * in the vectorGraphic to the x and y points.
             * @param e mouse event that is used to et x and y points
             */
            public void mouseDragged(MouseEvent e) {
                if (currentType != SetType.POLYGON) {
                    currentGraphic.onMouseDrag(e, getSize());
                    repaint();
                }
            }

            /***
             * MouseReleased method that listens for when mouse is released and sets the end values
             * in the vectorGraphic to the x and y points.
             * @param e mouse event that is used to et x and y points
             */
            public void mouseReleased(MouseEvent e) {
                if (currentType != SetType.POLYGON) {
                    currentGraphic.onMouseRelease(e, getSize());
                    vecGraphics.pop();
                    vecGraphics.add(currentGraphic);
                    currentGraphic = null;
                    redoStack.clear();
                    repaint();
                }
            }
        };

        //Add adapter to MouseListener and MouseMotionListener
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        setVisible(true);
    }

    /***
     * Method that removes the last vector graphic from vecGraphics stack and adds to the redoStack
     */
    public void undo() {
        if(!vecGraphics.empty()) {
            redoStack.push(vecGraphics.pop());
        }
    }

    /***
     * Method that removes the last vector graphic from redostack and adds it back to the redoStack
     */
    public void redo() {
        if(!redoStack.empty()) {
            vecGraphics.push(redoStack.pop());
        }
    }

    /***
     * Setter method that sets the shape type of the drawArea
     * @param sType parameter that sets the shape type.
     */
    public void setType(SetType sType){
        this.currentType = sType;
    }

    /***
     * Setter method that sets the shape fill colour of the drawArea
     * @param colour parameter that sets the fill colour.
     */
    public void setColour(Color colour) { this.colour = colour; }

    /***
     * Setter method that sets the shape stroke colour of the drawArea
     * @param colour parameter that sets the pen colour
     */
    public void setStrokeColor(Color colour) { this.strokeColor = colour; }

    /***
     * Method that accesses the private stack and adds a vector to it.
     * @param vector parameter that is added to the VectorGraphic stack
     */
    public void addToGraphics(VectorGraphic vector) { this.vecGraphics.add(vector); }

    /**
     * Getter method returns the DrawArea vecGraphics stack
     * @return Vector stack containing graphics is returned.
     */
    public Stack<VectorGraphic> getVectorGraphics() { return vecGraphics; }

    /***
     * setter method to set the amount of vertices in the polygon.
     * @param count integer amount of vertices
     */
    public void setPolyVertCount(int count) {this.polyVertCount = count;};

    /***
     * Override paintComponent method that draws each vector in the vecGraphics function
     * @param g graphics component that is passed in from run
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        Dimension size = getSize();
        for(VectorGraphic vecGraphic : vecGraphics) {
            vecGraphic.draw(g2d, size.width, size.height);
        }
    }

}
