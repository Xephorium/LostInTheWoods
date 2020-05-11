
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
    private static final int DEFAULT_SPEED_FACTOR = 1;
    private static final int DEFAULT_PLAYER_COUNT = 1;
    private static final int DEFAULT_GRID_WIDTH = 20;
    private static final int DEFAULT_GRID_HEIGHT = 20;

    // Delegation Classes
    WoodsSimulator woodsSimulator;
    WoodsWindow woodsWindow;

    // State Variables
    ProgramVersion programVersion;
    int speedFactor;
    int playerCount;
    Point gridSize;
    ArrayList<Point> playerPositions;


    /*--- Constructor ---*/

    public LostWoods() {

        // Instantiate & Configure Delegation Classes
        woodsSimulator = new WoodsSimulator();
        woodsSimulator.setUpdateListener(getWoodsSimulatorListener());
        woodsWindow = new WoodsWindow();
        woodsWindow.setListener(getWoodsWindowListener());

        // Instantiate & Configure State Variables
        programVersion = DEFAULT_PROGRAM_VERSION;
        speedFactor = DEFAULT_SPEED_FACTOR;
        playerCount = DEFAULT_PLAYER_COUNT;
        gridSize = new Point(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
        playerPositions = generateStartingPositions(gridSize, playerCount);
    }


    /*--- Public Methods ---*/

    public void beginLostWoods() {

        // Show User Interface
        woodsWindow.displayWindow();

        // Populate User Interface
        woodsWindow.setProgramVersion(programVersion);
        woodsWindow.setGridSize(gridSize);
        woodsWindow.setPlayerCount(playerCount);
        woodsWindow.setPlayerPositions(playerPositions);

        // Populate Simulator
        woodsSimulator.setTimeFactor(speedFactor);
        woodsSimulator.setGridSize(gridSize);
        woodsSimulator.setPlayerCount(playerCount);
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
                speedFactor = factor;
                woodsSimulator.setTimeFactor(speedFactor);
            }

            @Override
            public void onPlayerCountChange(int count) {
                onStop();
                playerCount = count;
                woodsWindow.setPlayerCount(playerCount);
            }

            @Override
            public void onGridWidthChange(int width) {
                onStop();
                gridSize.x = width;
                playerPositions = generateStartingPositions(gridSize, playerCount);
                woodsSimulator.setGridSize(gridSize);
                woodsWindow.setGridSize(gridSize);
                woodsSimulator.setPlayerPositions(playerPositions);
                woodsWindow.setPlayerPositions(playerPositions);
            }

            @Override
            public void onGridHeightChange(int height) {
                onStop();
                gridSize.y = height;
                playerPositions = generateStartingPositions(gridSize, playerCount);
                woodsSimulator.setGridSize(gridSize);
                woodsWindow.setGridSize(gridSize);
                woodsSimulator.setPlayerPositions(playerPositions);
                woodsWindow.setPlayerPositions(playerPositions);
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

                // Reset Player Positions
                playerPositions = generateStartingPositions(gridSize, playerCount);
                woodsSimulator.setPlayerPositions(playerPositions);
                woodsSimulator.setPlayerPositions(playerPositions);

                Thread newThread = new Thread(() -> {
                    woodsSimulator.beginSimulation();
                });
                newThread.start();
            }
        };
    }

    private WoodsSimulator.WoodsSimulatorListener getWoodsSimulatorListener() {
        return new WoodsSimulator.WoodsSimulatorListener() {
            @Override
            public void onUpdate(ArrayList<Point> positions) {
                woodsWindow.setPlayerPositions(positions);
            }

            @Override
            public void onFound() {

            }

            @Override
            public void onLost() {

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
