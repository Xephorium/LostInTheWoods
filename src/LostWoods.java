
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
import model.SearchMethod;
import simulation.Explorer;
import simulation.WoodsSimulator;
import ui.WoodsWindow;
import ui.utility.DialogUtility;

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
    public static final SearchMethod DEFAULT_SEARCH_METHOD = SearchMethod.Randomly;

    // Delegation Classes
    WoodsSimulator woodsSimulator;
    WoodsWindow woodsWindow;

    // State Variables
    ProgramVersion programVersion;
    int speedFactor;
    int playerCount;
    Point gridSize;
    SearchMethod searchMethod;
    ArrayList<Explorer> explorers;


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
        searchMethod = DEFAULT_SEARCH_METHOD;
        explorers = generateStartingExplorers(gridSize, playerCount);
    }


    /*--- Public Methods ---*/

    public void beginLostWoods() {

        // Show User Interface
        woodsWindow.displayWindow();

        // Populate User Interface
        woodsWindow.setProgramVersion(programVersion);
        woodsWindow.setGridSize(gridSize);
        woodsWindow.setPlayerCountDisplay(playerCount);
        woodsWindow.setPlayerPositions(getExplorerPositions());

        // Populate Simulator
        woodsSimulator.setTimeFactor(speedFactor);
        woodsSimulator.setGridSize(gridSize);
        woodsSimulator.setSearchMethod(searchMethod);
        woodsSimulator.setPlayerCount(playerCount);
        woodsSimulator.setPlayerPositions(getExplorerPositions());
    }


    /*--- Private Utility Methods ---*/

    private WoodsWindow.WoodsWindowListener getWoodsWindowListener() {
        return new WoodsWindow.WoodsWindowListener() {

            @Override
            public void onVersionChange(ProgramVersion version) {
                onStop();
                programVersion = version;
                if (programVersion == ProgramVersion.Simple) {
                    gridSize = new Point(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
                    playerCount = 1;
                }
                woodsWindow.setProgramVersion(programVersion);
                woodsWindow.setGridSize(gridSize);
                woodsWindow.setPlayerCountText(playerCount);
                woodsWindow.setPlayerCountDisplay(playerCount);
                woodsSimulator.setGridSize(gridSize);
                woodsSimulator.setPlayerCount(playerCount);
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
                woodsWindow.setPlayerCountDisplay(playerCount);
                woodsSimulator.setPlayerCount(playerCount);
            }

            @Override
            public void onGridWidthChange(int width) {
                onStop();
                gridSize.x = width;
                explorers = generateStartingExplorers(gridSize, playerCount);
                woodsSimulator.setGridSize(gridSize);
                woodsWindow.setGridSize(gridSize);
                woodsSimulator.setPlayerPositions(getExplorerPositions());
                woodsWindow.setPlayerPositions(getExplorerPositions());
            }

            @Override
            public void onGridHeightChange(int height) {
                onStop();
                gridSize.y = height;
                explorers = generateStartingExplorers(gridSize, playerCount);
                woodsSimulator.setGridSize(gridSize);
                woodsWindow.setGridSize(gridSize);
                woodsSimulator.setPlayerPositions(getExplorerPositions());
                woodsWindow.setPlayerPositions(getExplorerPositions());
            }

            @Override
            public void onSearchMethodChange(SearchMethod method) {
                onStop();
                searchMethod = method;
                woodsSimulator.setSearchMethod(searchMethod);
            }

            @Override
            public void onStop() {
                woodsSimulator.endSimulation();
            }

            @Override
            public void onStart() {
                resetSimulation();

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
                updateExplorerPositions(positions);
                woodsWindow.setPlayerPositions(getExplorerPositions());
                woodsWindow.setPlayerDistances(getExplorerDistances());
            }

            @Override
            public void onFound(long cycles) {
                woodsWindow.showExplorersFoundDialog(() -> resetSimulation(), cycles);
            }

            @Override
            public void onLost(long cycles) {
                woodsWindow.showExplorersLostDialog(() -> resetSimulation(), cycles);
            }
        };
    }

    private void resetSimulation() {
        woodsSimulator.endSimulation();
        explorers = generateStartingExplorers(gridSize, playerCount);
        woodsSimulator.setPlayerPositions(getExplorerPositions());
        woodsWindow.setPlayerPositions(getExplorerPositions());
        woodsWindow.setPlayerDistances(getResetExplorerDistances());
    }

    private ArrayList<Explorer> generateStartingExplorers(Point gridSize, int players) {
        ArrayList<Explorer> newExplorers = new ArrayList<Explorer>();
        newExplorers.add(new Explorer(0, 0));
        newExplorers.add(new Explorer(gridSize.x - 1, gridSize.y - 1));
        newExplorers.add(new Explorer(0, gridSize.y - 1));
        newExplorers.add(new Explorer(gridSize.x - 1, 0));
        return new ArrayList<>(newExplorers.subList(0, players + 1));
    }

    private ArrayList<Point> getExplorerPositions() {
        ArrayList<Point> positions = new ArrayList<Point>();
        for (int x = 0; x < playerCount + 1; x++) {
            positions.add(explorers.get(x).getPosition());
        }
        return new ArrayList<>(positions.subList(0, playerCount + 1));
    }

    private ArrayList<Integer> getExplorerDistances() {
        ArrayList<Integer> distances = new ArrayList<Integer>();
        for (int x = 0; x < playerCount + 1; x++) {
            distances.add(explorers.get(x).getDistanceTravelled());
        }
        return new ArrayList<>(distances.subList(0, playerCount + 1));
    }

    private ArrayList<Integer> getResetExplorerDistances() {
        ArrayList<Integer> distances = new ArrayList<Integer>();
        for (int x = 0; x < playerCount + 1; x++) {
            distances.add(0);
        }
        return new ArrayList<>(distances.subList(0, playerCount + 1));
    }

    private void updateExplorerPositions(ArrayList<Point> positions) {
        for (int x = 0; x < playerCount + 1; x++) {
            explorers.get(x).setPosition(positions.get(x));
        }
    }
}
