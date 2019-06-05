package paint.ui.view;

import paint.Exceptions.ShapeException;
import paint.shapes.Polygon;
import paint.shapes.Rectangle;
import paint.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static javax.swing.SwingUtilities.invokeLater;



/***
 * Class that is the parent container for all of the rest of the classes in the PaintGUI program
 */

public class PaintGUI extends JFrame implements ActionListener, Runnable, Serializable {

    private int WIDTH = 800;
    private int HEIGHT = 800;
    public DrawArea drawArea;
    private ButtonPanel btnTbl;
    private MenuBar menuBar;
    private Component aComponent;


    /***
     * Constructor method for GUI. Initializes the btnTbl, drawArea and menuBar classes to populate the GUI.
     */
    public void PaintGUI(){

        System.out.println("creating PaintGUI");

        //create initial paint gui and set constraints.
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Initialize object to add to object pane.
        this.btnTbl = new ButtonPanel();
        this.menuBar = new MenuBar();
        this.drawArea = new DrawArea();

        //Add the items to the GUI
        getContentPane().add(btnTbl, BorderLayout.WEST);
        getContentPane().add(drawArea, BorderLayout.CENTER);
        setJMenuBar(menuBar.CreateJMenuBar());
        // Add action listeners to buttons and menu bar objects + key listener for save/undo/redo
        btnTbl.addActionListener(this);
        menuBar.addActionListener(this);

        //Sets the focus on the GUI itself so the key listener works.
        setFocusable(true);
        requestFocusInWindow();
        //Closes one instance of the GUI instead of all instances.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //Code for Ctrl+Z and Ctrl+Y functionality
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.isControlDown()) {
                    switch(e.getKeyCode()) {
                        //If control is down and Z is pressed, call the undo function
                        case KeyEvent.VK_Z: {
                            drawArea.undo();
                            repaint();
                        } break;
                        //If control is down and Y is pressed, call the redo function
                        case KeyEvent.VK_Y: {
                            drawArea.redo();
                            repaint();
                        } break;
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
        });

        //Repaint the GUI and set it as visible
        repaint();
        setVisible(true);
    }


    /***
     * Method that listens for user interaction with buttons
     * @param e the actionEvent that is passed and used as the
     *          source for checking what button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Component source = (Component) e.getSource();
        //*** Code from setting the shape Type drawn ***//
        if (source == btnTbl.getLineBtn()) {
            drawArea.setType(SetType.LINE);
        } else if (source == btnTbl.getPlotBtn()) {
            drawArea.setType(SetType.PLOT);
        } else if (source == btnTbl.getRectBtn()) {
            drawArea.setType(SetType.RECTANGLE);
        } else if (source == btnTbl.getEllipseBtn()) {
            drawArea.setType(SetType.ELLIPSE);
        } else if (source == btnTbl.getPolyBtn()) {

            //User instructions for Polygon
            System.out.println("Left-click to add vertices, Right-click to close Polygon. " +
                    "\n Polygon must have a minimum of 3 vertices.");
            drawArea.setType(SetType.POLYGON);

            //Clear any previous attempts of drawing a polygon that were not completed
            drawArea.setPolyVertCount(0);
            //drawArea.clearPolyCords();

        }
        //*** Code to change the colour of the shapes drawn ***//
        else if (source == btnTbl.getColoursBtn()) {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
            drawArea.setStrokeColor(newColor);
        }
        else if (source == btnTbl.getFillBtn()) {
            Color fillColour = JColorChooser.showDialog(null, "Choose a color", null);
            drawArea.setColour(fillColour); 

        }
        //*** Code for the undo and redo buttons ***//
        else if (source == menuBar.getRedo()) {
            drawArea.redo();
            repaint();
        } else if (source == menuBar.getUndo()) {
            drawArea.undo();
            repaint();
        }
        //*** Code to create a new tabbed pane and new drawArea to draw in ***//
        else if (source == menuBar.getNewFile()) {
            invokeLater(new PaintGUI());
        }
        //*** Code to open a new file ***//
        else if (source == menuBar.getOpen()) {
                openFile();


        }
        //*** Code to save the drawingArea ***//
        else if (source == menuBar.getSave()) {
            saveFile();
        }
        //*** Code to close the drawingArea ***//
        else if (source == menuBar.getExit()) {
            closeFile();
        }
        setFocusable(true);
        requestFocusInWindow();
    }

    /***
     * Method that closes the GUI window.
     */
    private void closeFile()
    {
        //Exit the window
        System.exit(0);
    }

    /***
     * Method that opens a file based off used input from the file-choose window.
     */
    private void openFile() {
        //Create new instance of paintGUI and initalize new drawArea
        PaintGUI newWindow = new PaintGUI();
        newWindow.drawArea = new DrawArea();
        newWindow.setFocusable(true);
        newWindow.requestFocusInWindow();
        this.setFocusable(false);

        //Run new instance
        invokeLater(newWindow);

        final JFileChooser fc = new JFileChooser();
        //In response to a button click:
        int returnVal = fc.showOpenDialog(aComponent);
        //If approve is clicked.
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            //Create a new file object from user selected choice
            File file = fc.getSelectedFile();
            System.out.print("...created. Loading now... ");
            //invokeLater(newWindow);
            try {
                //create a stream that passes each line from the file into 'load' method
                FileInputStream os = new FileInputStream(file);
                DataInputStream in = new DataInputStream(os);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                System.out.println("Load call initiated.");
                System.out.println("Creating new PaintGUI...");
                newWindow.load(br);


            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();

            }
            catch(ShapeException se){
                se.printStackTrace();
            }
            catch(NumberFormatException ne){
                ne.printStackTrace();
            }

        }
//

    }

    /***
     * Method that is called from the open method to load strings from a bufferedReader and create new vector graphics
     * based off string contents
     * @param br is a buffered reader that as a DataInputStream from a FileInputStream
     * @throws ShapeException Shape exceptions to handle poor user input files or null/0 errors.
     * @throws IOException If command lines are incorrect, this exception is thrown
     * @throws NumberFormatException Handles incorrect input - empty lines and spaces
     */
    public void load(BufferedReader br) throws ShapeException, IOException, NumberFormatException {
        String strLine;

        Color penColour = Color.BLACK;
        Color fillColour = null;
        int width = getWidth();
        int height = getHeight();



            //While strLine is not null, check the contents of the string
            while ((strLine = br.readLine()) != null) {

                //Split up string into an array of strings
                String[] arr = strLine.split(" ");


                //Check the first element against PEN, if it matches set the colour of the pen for the next object
                if (arr[0].contains("PEN")) {
                    penColour = Color.decode(arr[1]);
                    drawArea.setStrokeColor(penColour);
                }

                //Check the first element against FILL
                else if (arr[0].contains("FILL")) {
                    //if it matches OFF, turn the colour off
                    if (arr[1].contains("OFF")) {
                        fillColour = null;
                        drawArea.setColour(fillColour);
                    }
                    //if it doesn't match OFF, set the colour to the colour in the element
                    else {
                        fillColour = Color.decode(arr[1]);
                        drawArea.setColour(fillColour);
                    }
                }
                //If it isnt a colour changing line of input, it must be a shape. Check the first element for
                //the setType of the shape to be created, create a new object with parameters and add to
                //Vector Graphic stack
                else if (arr[0].contains("PLOT") || arr[0].contains("LINE") || arr[0].contains("RECTANGLE")
                        || arr[0].contains("ELLIPSE") || arr[0].contains("POLYGON")) {
                    if (arr[0].contains("PLOT")) {
                        Plot plot = new Plot(strLine, penColour, fillColour, drawArea.getWidth(), drawArea.getHeight());
                        drawArea.addToGraphics(plot);
                    } else if (arr[0].contains("LINE")) {
                        Line line = new Line(strLine, penColour, fillColour, drawArea.getWidth(), drawArea.getHeight());
                        drawArea.addToGraphics(line);
                    } else if (arr[0].contains("RECTANGLE")) {
                        Rectangle rectangle = new Rectangle(strLine, penColour, fillColour, width, height);
                        drawArea.addToGraphics(rectangle);
                    } else if (arr[0].contains("ELLIPSE")) {
                        Ellipse ellipse = new Ellipse(strLine, penColour, fillColour, drawArea.getWidth(), drawArea.getHeight());
                        drawArea.addToGraphics(ellipse);
                    } else if (arr[0].contains("POLYGON")) {
                        Polygon polygon = new Polygon(strLine, penColour, fillColour, drawArea.getWidth(), drawArea.getHeight());
                        drawArea.addToGraphics(polygon);
                    }

                    //Repaint the GUI window
                    repaint();
                } else {
                    throw new IOException("VEC FILE CONTAINS ERRORS - commands not recognised.");
                }
            }

    }

    /***
     * Method that saves the drawArea to a vector file.
     */
    private void saveFile()
    {
        //holders for colour variables
        Color previousColour = Color.BLACK;
        Color previousFill = null;

        //Open a new file choose and show save dialogue
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(aComponent);

        //If user clicks approve, create a file object from the select file
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            FileWriter fileWriter = null;
            try {
                //Create a new file to write to
                fileWriter = new FileWriter(fileToSave.getAbsolutePath()+".vec");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            //For each vector shape in the vector stack, first check the colour of pen and fill to record any
            // changes and then add it to the new vector file
            for (VectorGraphic graphics : drawArea.getVectorGraphics()) {
                try {
                    //If the pen colour is the same as the previous shape
                    if (graphics.getColour() == previousColour)
                        //If fillColour is the same as the previous shape, just record shape
                        if (graphics.getFillColour() == previousFill){
                            fileWriter.write(graphics.toString());
                            previousColour = graphics.getColour();
                            previousFill = graphics.getFillColour();
                        }
                        //Record fill colour and shape
                        else {
                            String fillHex = "#"+Integer.toHexString(graphics.getFillColour().getRGB()).substring(2);
                            fileWriter.write("FILL "+ fillHex.toUpperCase() +"\n");
                            fileWriter.write(graphics.toString());
                            previousColour = graphics.getColour();
                            previousFill = graphics.getFillColour();
                        }

                        //If the pen colour is not the same as the previous shape
                    else
                    {
                        //If the fill colour is the same as the previous shape, record  pen colour and shape
                        if (graphics.getFillColour() == previousFill){
                            String penHex = "#"+Integer.toHexString(graphics.getColour().getRGB()).substring(2);
                            fileWriter.write("PEN "+ penHex.toUpperCase() +"\n");
                            fileWriter.write(graphics.toString());
                            previousColour = graphics.getColour();
                            previousFill = graphics.getFillColour();
                        }
                        //If the fill colour is set to null, record fill is off, pen colour and shape
                        else if (graphics.getFillColour() == null) {
                            fileWriter.write("FILL OFF"+ "\n");
                            String penHex = "#"+Integer.toHexString(graphics.getColour().getRGB()).substring(2);
                            fileWriter.write("PEN "+ penHex.toUpperCase() +"\n");
                            fileWriter.write(graphics.toString());
                            previousColour = graphics.getColour();
                            previousFill = graphics.getFillColour();
                        }
                        else {
                            //if both pen and fill colour is different, record pen and fill colour and shape
                            String penHex = "#"+Integer.toHexString(graphics.getColour().getRGB()).substring(2);
                            fileWriter.write("PEN "+ penHex.toUpperCase() +"\n");
                            String fillHex = "#"+Integer.toHexString(graphics.getFillColour().getRGB()).substring(2);
                            fileWriter.write("FILL "+ fillHex.toUpperCase() +"\n");
                            fileWriter.write(graphics.toString());
                            previousColour = graphics.getColour();
                            previousFill = graphics.getFillColour();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    /***
     * Method that constantly calls constructor
     */
    @Override
    public void run() {
        PaintGUI();

    }
}

