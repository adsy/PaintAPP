package paint.ui.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Class that creates the menu buttons that run along the top of the GUI. Used for user interaction with GUI for
 * open, save, new file, exit, redo and undo.
 */

public class MenuBar extends JMenuBar implements ActionListener {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu edit;
    private JMenuItem newFile, save, open, exit, undo, redo;
    /***
     * Constructor for class, calls JMenuBar which returns a JMenuBar.
     */
    public MenuBar() {
        CreateJMenuBar();
    }

    /***
     * Method that creates and returns a JMenuBar item to be used by the GUI.
     * @return JMenuBar initalised
     */
    public JMenuBar CreateJMenuBar() {
        menuBar = new JMenuBar();
        // file menu
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        newFile = new JMenuItem("New");
        save = new JMenuItem("Save");
        open = new JMenuItem("Open");
        exit = new JMenuItem("Exit");
        fileMenu.add(newFile);
        fileMenu.add(save);
        fileMenu.add(open);
        fileMenu.add(exit);

        // edit menu
        edit = new JMenu("Edit");
        redo = new JMenuItem("Redo");
        undo = new JMenuItem("Undo");
        edit.add(redo);
        edit.add(undo);
        menuBar.add(edit);
        return menuBar;
    }

    /***
     * method adds an action listener to the menu items
     * @param al is a frame that is passed in and each menu item adds an actionListener onto it.
     */
    void addActionListener(ActionListener al)
    {
        newFile.addActionListener(al);
        save.addActionListener(al);
        open.addActionListener(al);
        exit.addActionListener(al);
        undo.addActionListener(al);
        redo.addActionListener(al);

    }

    /***
     * Getter method for New File
     * @return return a JMenuItem which will be used to check equivalence for button is pressed on the GUI
     */
    public JMenuItem getNewFile() { return newFile; }

    /***
     * Getter method for Save
     * @return return a JMenuItem which will be used to check equivalence for button is pressed on the GUI
     */
    public JMenuItem getSave() { return save; }

    /***
     * Getter method for Open file
     * @return return a JMenuItem which will be used to check equivalence for button is pressed on the GUI
     */
    public JMenuItem getOpen() { return open ; }

    /***
     * Getter method for Exit
     * @return return a JMenuItem which will be used to check equivalence for button is pressed on the GUI
     */
    public JMenuItem getExit() { return exit ; }

    /***
     * Getter method for Redo
     * @return return a JMenuItem which will be used to check equivalence for button is pressed on the GUI
     */
    public JMenuItem getRedo() { return redo; }

    /***
     * Getter method for Undo
     * @return return a JMenuItem which will be used to check equivalence for button is pressed on the GUI
     */
    public JMenuItem getUndo() { return undo;}

    /***
     * Method overriden but left blank - not needed.
     * @param e component passed in.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
