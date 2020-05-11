
/* Title:          Lost Woods
 * This File:      LostWoods.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   LostWoods is the root class and backbone of the Lost Woods program.
 * It manages program state, UI, and simulation by delegating to respective
 * specialty classes.
 */

import model.ProgramVersion;
import simulation.WoodsSimulator;
import ui.WoodsWindow;

import java.awt.*;
import java.util.ArrayList;

public class LostWoods {


    /*--- Variable Declarations ---*/

    // Constants
    private static final ProgramVersion DEFAULT_PROGRAM_VERSION = ProgramVersion.Intermediate;
    private static final int DEFAULT_GRID_WIDTH = 20;
    private static final int DEFAULT_GRID_HEIGHT = 20;
    private static final int DEFAULT_PLAYER_COUNT = 1;

    // Delegation Classes
    WoodsSimulator woodsSimulator;
    WoodsWindow woodsWindow;

    // State Variables
    ProgramVersion programVersion;
    Point gridSize;
    int playerCount;
    ArrayList<Point> playerPositions;


    /*--- Constructor ---*/

    public LostWoods() {

        // Instantiate & Configure Delegation Classes
        woodsSimulator = new WoodsSimulator();
        woodsWindow = new WoodsWindow();
        woodsWindow.setListener(getWoodsWindowListener());

        // Instantiate & Configure State Variables
        programVersion = DEFAULT_PROGRAM_VERSION;
        gridSize = new Point(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
        playerCount = DEFAULT_PLAYER_COUNT;
        playerPositions = generateStartingPositions(gridSize, playerCount);
    }


    /*--- Public Methods ---*/

    public void beginLostWoods() {

        // Show User Interface
        woodsWindow.displayWindow();

        // Populate User Interface
        woodsWindow.setPlayerCount(1);
        woodsWindow.setProgramVersion(programVersion);

        // Populate Simulator
        woodsSimulator.setUpdateListener(positions -> {
            woodsWindow.setPlayerPositions(positions);
        });
        woodsSimulator.setTimeFactor(1);
        woodsSimulator.setGridSize(new Point(20, 20));
        woodsSimulator.setPlayerCount(1);
        ArrayList<Point> playerPositions = new ArrayList<>();
        playerPositions.add(new Point(0, 0));
        playerPositions.add(new Point(19, 19));
        woodsSimulator.setPlayerPositions(playerPositions);
    }


    /*--- Private Utility Methods ---*/

    private WoodsWindow.WoodsWindowListener getWoodsWindowListener() {
        return new WoodsWindow.WoodsWindowListener() {

            @Override
            public void onVersionChange(ProgramVersion version) {
                programVersion = version;
                woodsWindow.setProgramVersion(programVersion);
            }

            @Override
            public void onSpeedChange(int factor) {
                System.out.println("Speed: " + factor);
            }

            @Override
            public void onPlayerCountChange(int count) {
                playerCount = count;
                woodsWindow.setPlayerCount(playerCount);
            }

            @Override
            public void onGridWidthChange(int width) {
                gridSize.x = width;
                woodsSimulator.setGridSize(gridSize);
                woodsWindow.setGridSize(gridSize);
            }

            @Override
            public void onGridHeightChange(int height) {
                gridSize.y = height;
                woodsSimulator.setGridSize(gridSize);
                woodsWindow.setGridSize(gridSize);
            }

            @Override
            public void onSearchMethodChange(int index) {
                System.out.println("Search Method: " + index);
            }

            @Override
            public void onStop() {
                woodsSimulator.endSimulation();
            }

            @Override
            public void onStart() {
                Thread newThread = new Thread(() -> {
                    woodsSimulator.beginSimulation();
                });
                newThread.start();
            }
        };
    }

    private ArrayList<Point> generateStartingPositions(Point gridSize, int players) {
        ArrayList<Point> newPositions = new ArrayList<Point>();
        newPositions.add(new Point(0, 0));
        newPositions.add(new Point(gridSize.x - 1, gridSize.y - 1));
        newPositions.add(new Point(0, gridSize.y - 1));
        newPositions.add(new Point(gridSize.x - 1, 0));
        return new ArrayList<>(newPositions.subList(0, players + 1));
    }
}
