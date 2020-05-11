import model.ProgramVersion;
import simulation.LostWoods;
import ui.WoodsWindow;
import ui.WoodsWindow.WoodsWindowListener;

import java.awt.*;
import java.util.ArrayList;

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

        // Create Simulation
        LostWoods lostWoods = new LostWoods();

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
                lostWoods.setGridSize(new Point(gridWidth, gridHeight));
                woodsWindow.setGridSize(new Point(gridWidth, gridHeight));
            }

            @Override
            public void onGridHeightChange(int height) {
                gridHeight = height;
                lostWoods.setGridSize(new Point(gridWidth, gridHeight));
                woodsWindow.setGridSize(new Point(gridWidth, gridHeight));
            }

            @Override
            public void onSearchMethodChange(int index) {
                System.out.println("Search Method: " + index);
            }

            @Override
            public void onStop() {
                lostWoods.endSimulation();
            }

            @Override
            public void onStart() {
                Thread newThread = new Thread(() -> {
                    lostWoods.beginSimulation();
                });
                newThread.start();
            }
        };
        woodsWindow.setListener(listener);
        woodsWindow.displayWindow();
        woodsWindow.setPlayerCount(1);
        woodsWindow.setProgramVersion(ProgramVersion.Simple);

        // Configure Simulation
        lostWoods.setUpdateListener(positions -> {
            woodsWindow.setPlayerPositions(positions);
        });
        lostWoods.setTimeFactor(1);
        lostWoods.setGridSize(new Point(20, 20));
        lostWoods.setPlayerCount(1);
        ArrayList<Point> playerPositions = new ArrayList<>();
        playerPositions.add(new Point(0, 0));
        playerPositions.add(new Point(19, 19));
        lostWoods.setPlayerPositions(playerPositions);
    }
}
