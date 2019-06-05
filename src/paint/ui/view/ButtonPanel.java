package paint.ui.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Class that creates the drawing buttons that run along the left side of the panel. Listens for user input to
 * change shape and colours.
 */

public class ButtonPanel extends JPanel implements ActionListener {

    //BtnTools for drawing
    private JButton plotBtn;
    private JButton lineBtn;
    private JButton rectBtn;
    private JButton ellipseBtn;
    private JButton polyBtn;

    //BtnTools for functionality
    private JButton coloursBtn;
    private JButton fillColoursBtn;


    /***
     * Constructor method to create the ButtonPanel panel to hold the buttons on the left side of the GUI.
     */
    public ButtonPanel() {


        //BtnTools for drawing
        lineBtn  = createButton("src/line.png", "Drawline");
        plotBtn = createButton("src/dot.png", "Plot");
        rectBtn = createButton("src/rectangle.png", "Rectangle");
        ellipseBtn = createButton("src/ellipse.png", "Ellipse");
        polyBtn = createButton("src/polygon.png", "Polygon");
        //BtnTools for functionality
        coloursBtn = createButton("src/pen-colours.png","Colours");
        fillColoursBtn = createButton("src/paint-bucket.png","Fill");
        setLayout(new GridBagLayout());
        //add components to grid
        GridBagConstraints contraints = new GridBagConstraints();
        //defaults
        contraints.fill = GridBagConstraints.NONE;
        contraints.anchor = GridBagConstraints.CENTER;
        contraints.weightx = 0;
        contraints.weighty = 50;

        //Add buttons for drawing
        addToPanel(this, lineBtn, contraints, 0,0,0,1);
        addToPanel(this, plotBtn, contraints, 0,1,0,1);
        addToPanel(this, rectBtn, contraints, 0,2,0,1);
        addToPanel(this, ellipseBtn, contraints, 0,3,0,1);
        addToPanel(this, polyBtn, contraints, 0,4,0,1);
        //Add buttons for functionality
        addToPanel(this, coloursBtn, contraints, 0,5,0,1);
        addToPanel(this, fillColoursBtn, contraints, 0,6,0,1);


    }

    /***
     * Method to be called by paintGUI to add itself as an actionLister for the buttons
     * @param al action listener which is the PaintGUI
     */
    void addActionListener(ActionListener al)
    {
        lineBtn.addActionListener(al);
        plotBtn.addActionListener(al);
        rectBtn.addActionListener(al);
        ellipseBtn.addActionListener(al);
        polyBtn.addActionListener(al);
        coloursBtn.addActionListener(al);
        fillColoursBtn.addActionListener(al);
    }

    /***
     * Method to handle creating buttons for the GUI
     * @param imgPath The name of the button
     * @return Returns the button object
     */
    private JButton createButton(String imgPath, final String name){
        JButton newButton = new JButton();
        ImageIcon icon = new ImageIcon(imgPath);
        if(imgPath != "")
        {
            newButton.setIcon(icon);
        }else{
            newButton = new JButton(name);
        }
        //Use this code when we add images to the buttons
        newButton.setPreferredSize(new Dimension(80,60));

        return newButton;
    }

    /** ** A convenience method to   add    a component to   given grid bag
     * * layout locations. Code due    to   Cay    Horstmann
     * * * @param c the    component to   add
     * * @param constraints the    grid bag    constraints to   use
     * * @param x the    x grid position
     * * @param y the    y grid position
     * * @param w the    grid width of   the    component
     * * @param h the    grid height   of   the    component*/
    private void addToPanel(JPanel jp,Component c,   GridBagConstraints
            constraints,int x,   int    y,   int    w,   int    h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    /***
     * Getter method to get the line button
     * @return line button
     */
    public JButton getLineBtn() {
        return lineBtn;
    }

    /***
     * Getter method to get the plot button
     * @return plot button
     */
    public JButton getPlotBtn() {
        return plotBtn;
    }

    /***
     * Getter method to get the plot button
     * @return plot button
     */
    public JButton getRectBtn() {
        return rectBtn;
    }

    /***
     * Getter method to get the ellipse button
     * @return ellipse button
     */
    public JButton getEllipseBtn() {
        return ellipseBtn;
    }

    /***
     * Getter method to get the Colours button
     * @return plot button
     */
    public JButton getColoursBtn() {
        return coloursBtn;
    }

    /***
     * Getter method to get the Colours button
     * @return plot button
     */
    public JButton getFillBtn() {
        return fillColoursBtn;
    }

    /***
     * Getter method to get the Poly button
     * @return plot button
     */
    public JButton getPolyBtn() {
        return polyBtn;
    }

    /***
     * Method overriden but left blank - not needed.
     * @param e component passed in.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

