package simulation;

import model.SearchMethod;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/* Title:          Lost Woods
 * This File:      WoodsSimulator.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   WoodsSimulator is the primary driver of the Lost Woods simulation.
 * It handles grid construction, explorer instantiation/management,
 * and simulation of each explorer's journey.
 */

public class WoodsSimulator {


    /*--- Variable Declarations ---*/

    // State Constants
    private static final long UPDATE_INTERVAL = 100;
    private static final int MAX_TIME = 60000;

    // State Variables
    private WoodsSimulatorListener listener;
    private Random random;
    private long lastUpdate = 0;
    private long endTime = 0;
    private int timeFactor = 1;
    private int gridWidth;
    private int gridHeight;
    private int playerCount;
    private SearchMethod searchMethod;
    private ArrayList<Explorer> explorers;
    private boolean explorersFound;
    private boolean explorationCancelled = false;


    /*--- Constructor ---*/

    public WoodsSimulator() {

        // Initialize State Variables
        random = new Random();
        gridWidth = 2;
        gridHeight = 2;
        searchMethod = SearchMethod.Randomly;
        explorers = new ArrayList<>();
        explorersFound = false;
    }


    /*--- Public Methods ---*/

    public void setUpdateListener(WoodsSimulatorListener listener) {
        this.listener = listener;
    }

    public void setTimeFactor(int factor) {
        this.timeFactor = factor;
    }

    public void setGridSize(Point size) {
        this.gridWidth = size.x;
        this.gridHeight = size.y;
    }

    public void setSearchMethod(SearchMethod method) {
        this.searchMethod = method;
    }

    public void setPlayerCount(int count) {
        this.playerCount = count;
    }

    public void setPlayerPositions(ArrayList<Point> positions) {
        explorers = new ArrayList<>();
        for (int x = 0; x < playerCount + 1; x++) {
            explorers.add(new Explorer(positions.get(x).x, positions.get(x).y));
        }
    }

    public void beginSimulation() {
        explorationCancelled = false;

        // Simulate Exploration, Passing Updates to Listener Each Step
        long startTime = (new Date()).getTime();
        long time = startTime;
        long lastUpdate = startTime;
        long endTime = startTime + MAX_TIME;
        while (time < endTime && !explorersFound && !explorationCancelled) {

            // Update Time
            time = (new Date()).getTime();

            if (time - lastUpdate > UPDATE_INTERVAL / timeFactor) {

                lastUpdate = time;

                // Move Explorers
                for (int x = 0; x < playerCount + 1; x++) {
                    if (searchMethod == SearchMethod.Randomly) {
                        explorers.get(x).setPosition(getRandomMove(explorers.get(x).getPosition()));
                    } else {
                        explorers.get(x).setPosition(getRandomMove(explorers.get(x).getPosition()));
                    }
                }

                // Perform Location Check
                if (explorers.get(0).comparePositions(explorers.get(1))) {
                    explorersFound = true;
                }

                // Update UI
                ArrayList<Point> positions = new ArrayList<Point>();
                for (int x = 0; x < playerCount + 1; x++) {
                    positions.add(explorers.get(x).getPosition());
                }
                listener.onUpdate(positions);
            }
        }

        // Calculate Cycles
        long cycles = (time - startTime) / UPDATE_INTERVAL;

        if (explorersFound) {
            listener.onFound(cycles);
        } else {
            listener.onLost(cycles);
        }
    }

    public void endSimulation() {
        explorationCancelled = true;
        explorersFound = false;

    }


//    private Point getNewMove(Explorer explorer) {
//
//        // Declare Local Variables
//        Point move;
//        Point lastPosition = explorer.getPosition();
//        Point nextPosition = getRandomMove(lastPosition);
//
//
//    }

    /* Name: getRandomMove()
     * Description: Generates a valid random move given a current
     *   position or returns the current position.
     */
    private Point getRandomMove(Point lastPosition) {

        // Declare Local Variables
        int move;
        Point nextPosition;

        // Get Random Integer (1-8) Representing Move
        move = random.nextInt(8) + 1;
        nextPosition = getUpdatedPosition(lastPosition, move);

        // Perform Bounds Check & Return
        if (nextPosition.x >= 0 && nextPosition.x < gridWidth
                && nextPosition.y >= 0 && nextPosition.y < gridHeight) {
            return nextPosition;
        } else {
            return lastPosition;
        }
    }

    private Point getUpdatedPosition(Point lastPosition, int move) {
        switch (move) {

            // North
            case 1: return new Point(lastPosition.x, lastPosition.y - 1);

            // NorthWest
            case 2: return new Point(lastPosition.x + 1, lastPosition.y - 1);

            // West
            case 3: return new Point(lastPosition.x + 1, lastPosition.y);

            // Southwest
            case 4: return new Point(lastPosition.x + 1, lastPosition.y + 1);

            // South
            case 5: return new Point(lastPosition.x, lastPosition.y + 1);

            // Southeast
            case 6: return new Point(lastPosition.x - 1, lastPosition.y + 1);

            // East
            case 7: return new Point(lastPosition.x - 1, lastPosition.y);

            // Northeast
            case 8: return new Point(lastPosition.x - 1, lastPosition.y - 1);
        }

        return null;
    }


    /*--- WoodsSimulatorListener Interface --- */

    public interface WoodsSimulatorListener {
        public void onUpdate(ArrayList<Point> positions);
        public void onFound(long cycles);
        public void onLost(long cycles);
    }
}
