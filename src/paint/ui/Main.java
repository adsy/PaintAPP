package paint.ui;

import paint.ui.view.PaintGUI;

/***
 * Main function that invokes the first instance of the GUI with threading.
 */
public class Main {

    /***
     * Main method runs the paintGUI through threading.
     * @param args main method args
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new PaintGUI());
    }


}
