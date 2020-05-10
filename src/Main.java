import simulation.LostWoods;
import ui.WoodsWindow;
import ui.WoodsWindow.WoodsWindowListener;

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
        WoodsWindow woodsWindow = new WoodsWindow(new WoodsWindowListener() {
            @Override
            public void onVersionChange(int index) {
                System.out.println(index);
            }

            @Override
            public void onSpeedChange(int factor) {
                System.out.println(factor);
            }

            @Override
            public void onPlayerCountChange(int count) {
                System.out.println(count);
            }

            @Override
            public void onGridWidthChange(int width) {
                System.out.println(width);
            }

            @Override
            public void onGridHeightChange(int height) {
                System.out.println(height);
            }

            @Override
            public void onSearchMethodChange(int index) {
                System.out.println(index);
            }

            @Override
            public void onStop() {
                System.out.println("Stop");
            }

            @Override
            public void onStart() {
                System.out.println("Start");
            }
        });
        woodsWindow.displayWindow();
    }
}
