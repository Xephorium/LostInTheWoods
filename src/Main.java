import simulation.LostWoods;
import ui.WoodsWindow;

/* Title:          Lost Woods
 * This File:      Main.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   Main is a simple container class for the Lost Woods program.
 * It instantiates an object of the LostWoods class and then
 * delegates simulation.
 */

public class Main {

    public static void main(String[] args) {

        // Create simulation.LostWoods Class & Run Simulation
        //LostWoods lostWoods = new LostWoods();
        //lostWoods.runSimulation();

        // Create WoodsWindow & Display
        WoodsWindow woodsWindow = new WoodsWindow();
        woodsWindow.displayWindow();
    }
}
