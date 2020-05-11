package simulation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/* Title:          Lost Woods
 * This File:      LostWoods.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   LostWoods is the primary driver of the Lost Woods simulation.
 * It handles grid construction, explorer instantiation/management,
 * and simulation of each explorer's journey.
 */

public class LostWoods {


    /*--- Variable Declarations ---*/

    // State Constants
    private static final long UPDATE_INTERVAL = 400;
    private static final int MAX_TIME = 1000000;

    // State Variables
    private LostWoodsUpdateListener listener;
    private Random random;
    private long lastUpdate = 0;
    private long endTime = 0;
    private int timeFactor = 1;
    private int gridWidth;
    private int gridHeight;
    private int playerCount;
    private ArrayList<Explorer> explorers;
    private boolean explorersFound;
    private boolean explorationCancelled = false;


    /*--- Constructor ---*/

    public LostWoods() {

        // Initialize State Variables
        random = new Random();
        gridWidth = 2;
        gridHeight = 2;
        explorers = new ArrayList<>();
        explorersFound = false;
    }


    /*--- Public Methods ---*/

    public void setUpdateListener(LostWoodsUpdateListener listener) {
        this.listener = listener;
    }

    public void setTimeFactor(int factor) {
        this.timeFactor = factor;
    }

    public void setGridSize(Point size) {
        this.gridWidth = size.x;
        this.gridHeight = size.y;
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
        long time = (new Date()).getTime();
        long lastUpdate = time;
        long endTime = time + MAX_TIME;
        while (time < endTime && !explorersFound && !explorationCancelled) {

            // Update Time
            time = (new Date()).getTime();

            if (time - lastUpdate > UPDATE_INTERVAL) {

                lastUpdate = time;

                // Move Explorers
                for (int x = 0; x < playerCount + 1; x++) {
                    explorers.get(x).setPosition(getRandomMove(explorers.get(x).getPosition()));
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
    }

    public void endSimulation() {
        explorationCancelled = true;

    }


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

        // Determine New Coordinates
        switch (move) {

            // North
            case 1:
                nextPosition = new Point(lastPosition.x, lastPosition.y - 1);
                break;

            // NorthWest
            case 2:
                nextPosition = new Point(lastPosition.x + 1, lastPosition.y - 1);
                break;

            // West
            case 3:
                nextPosition = new Point(lastPosition.x + 1, lastPosition.y);
                break;

            // Southwest
            case 4:
                nextPosition = new Point(lastPosition.x + 1, lastPosition.y + 1);
                break;

            // South
            case 5:
                nextPosition = new Point(lastPosition.x, lastPosition.y + 1);
                break;

            // Southeast
            case 6:
                nextPosition = new Point(lastPosition.x - 1, lastPosition.y + 1);
                break;

            // East
            case 7:
                nextPosition = new Point(lastPosition.x - 1, lastPosition.y);
                break;

            // Northeast
            case 8:
                nextPosition = new Point(lastPosition.x - 1, lastPosition.y - 1);
                break;

            // Freak Random Number Generator Accident
            default:
                nextPosition = lastPosition;
        }

        // Perform Bounds Check & Return
        if (nextPosition.x >= 0 && nextPosition.x < gridWidth
                && nextPosition.y >= 0 && nextPosition.y < gridHeight) {
            return nextPosition;
        } else {
            return lastPosition;
        }
    }


    /*--- LostWoodsUpdateListener Interface --- */

    public interface LostWoodsUpdateListener {
        public void onUpdate(ArrayList<Point> positions);
    }
}
