import model.ProgramVersion;
import simulation.LostWoods;
import ui.WoodsWindow;
import ui.WoodsWindow.WoodsWindowListener;

import java.awt.*;

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

        // Create Woods Window Listener
        WoodsWindow woodsWindow = new WoodsWindow();
        WoodsWindowListener listener = new WoodsWindowListener() {
            int gridWidth = 20;
            int gridHeight = 20;

            @Override
            public void onVersionChange(ProgramVersion version) {
                woodsWindow.setProgramVersion(version);
            }

            @Override
            public void onSpeedChange(int factor) {
                System.out.println("Speed: " + factor);
            }

            @Override
            public void onPlayerCountChange(int count) {
                woodsWindow.setPlayerCount(count);
            }

            @Override
            public void onGridWidthChange(int width) {
                gridWidth = width;
                woodsWindow.setGridSize(new Point(gridWidth, gridHeight));
            }

            @Override
            public void onGridHeightChange(int height) {
                gridHeight = height;
                woodsWindow.setGridSize(new Point(gridWidth, gridHeight));
            }

            @Override
            public void onSearchMethodChange(int index) {
                System.out.println("Search Method: " + index);
            }

            @Override
            public void onStop() {
                System.out.println("Stop");
            }

            @Override
            public void onStart() {
                System.out.println("Start");
            }
        };
        woodsWindow.setListener(listener);
        woodsWindow.displayWindow();
        woodsWindow.setProgramVersion(ProgramVersion.Simple);
    }
}
