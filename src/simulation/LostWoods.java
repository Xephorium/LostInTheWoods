package simulation;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    private static final int MIN_GRID_SIZE = 2;
    private static final int MAX_GRID_SIZE = 50;
    private static final int MAX_TIME = 1000000;

    // Output Constants
    private static final String WELCOME = "### Welcome to Lost Woods! ###\n\nPat and Chris are lost on a grid, the"
        + " size of which is yours\nto determine. Let's find out whether they can find each other.\n";
    private static final String REQUEST_WIDTH = "Enter grid width (integer of range "
        + MIN_GRID_SIZE + "-" + MAX_GRID_SIZE + "): ";
    private static final String REQUEST_HEIGHT = "Enter grid height (integer of range "
            + MIN_GRID_SIZE + "-" + MAX_GRID_SIZE + "): ";
    private static final String INPUT_EXCEPTION = "Exception reading input.";
    private static final String INPUT_INVALID = "Invalid input. Please try again.";
    private static final String GRID_SIZE = "Grid size set to (%d, %d).";
    private static final String CONCLUSION_SUCCESS = "\nOh happy day! Pat and Chris found each other in cell (%d, %d) "
            + "at time %d.\nThey may now wander these mysterious woods as friends.";
    private static final String CONCLUSION_FAILURE = "\nPat and Chris remain lost after %d cycles of exploration.";
    private static final String METADATA =
              "\nSome Fun Facts:"
            + "\n  Pat"
            + "\n    Total Cells Explored:     %d (%.2f%% of Grid)"
            + "\n    Total Distance Travelled: %d Cells"
            + "\n  Chris"
            + "\n    Total Cells Explored:     %d (%.2f%% of Grid)"
            + "\n    Total Distance Travelled: %d Cells"
            + "\n\nPress enter to exit.";

    // Input Variables
    private BufferedReader bufferedReader;

    // State Variables
    private Random random;
    private int time;
    private int gridWidth;
    private int gridHeight;
    private ArrayList<Explorer> explorers;
    private boolean explorersFound;


    /*--- Constructor ---*/

    public LostWoods() {

        // Initialize Input Variables
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Initialize State Variables
        random = new Random();
        time = 0;
        gridWidth = 2;
        gridHeight = 2;
        explorers = new ArrayList<>();
        explorersFound = false;
    }


    /*--- Public Methods ---*/

    /* Name: runSimulation()
     * Description: Handles the base level program logic for Lost Woods,
     *   including welcoming, prompting for grid dimensions, setting up
     *   the simulation, and printing the results.
     */
    public void runSimulation() {

        // Print Welcome
        System.out.println(WELCOME);

        // Request Grid Dimensions
        gridWidth = requestInputDimension(REQUEST_WIDTH);
        gridHeight = requestInputDimension(REQUEST_HEIGHT);

        // Print Grid Size
        System.out.println(String.format(GRID_SIZE, gridWidth, gridHeight));

        // Initialize Explorers
        initializeExplorers();

        // Begin Exploration Simulation
        simulateExploration();

        // Print Result
        if (explorersFound) {
            System.out.println(String.format(
                    CONCLUSION_SUCCESS,
                    explorers.get(0).getPosition().x,
                    explorers.get(0).getPosition().y,
                    time)
            );
        } else {
            System.out.println(String.format(CONCLUSION_FAILURE, time));
        }

        // Print Metadata
        System.out.println(String.format(
                METADATA,
                explorers.get(0).getNumberOfCellsExplored(),
                ((double) explorers.get(0).getNumberOfCellsExplored() / (gridWidth * gridHeight)) * 100.00,
                explorers.get(0).getDistanceTravelled(),
                explorers.get(1).getNumberOfCellsExplored(),
                ((double) explorers.get(1).getNumberOfCellsExplored() / (gridWidth * gridHeight)) * 100.00,
                explorers.get(1).getDistanceTravelled()
        ));

        // Await User Confirmation
        try {
            bufferedReader.readLine();
        } catch (Exception exception) {
            // Do Nothing
        }
    }


    /*--- Private Methods ---*/

    /* Name: requestInputDimension()
     * Description: Handles the logic for requesting an integer value
     *  from the user, validating that input, and repeating until a
     *  legitimate value is provided.
     */
    private int requestInputDimension(String requestMessage) {

        // Declare Local Variables
        String input = "";
        String errorMessage = "";
        boolean inputInvalid = false;
        int dimension = 2;

        // Begin Input Loop
        do {

            // Reset Input
            if (inputInvalid) {
                System.out.println(errorMessage);
                inputInvalid = false;
            }

            // Request Width
            System.out.print(requestMessage);

            try {

                // Read & Parse Input
                input = bufferedReader.readLine();
                dimension = Integer.parseInt(input.trim());

                // Perform Bounds Check
                if (dimension < MIN_GRID_SIZE || dimension > MAX_GRID_SIZE) {
                    errorMessage = INPUT_INVALID;
                    inputInvalid = true;
                }

            } catch (Exception e) {

                // Handle Exceptions
                if (e instanceof NumberFormatException) {
                    errorMessage = INPUT_INVALID;
                } else {
                    errorMessage = INPUT_EXCEPTION;
                }
                inputInvalid = true;
            }

        } while (inputInvalid);

        return dimension;
    }

    /* Name: initializeExplorers()
     * Description: Creates two explorers with initial positions
     *   on opposite sides of the grid.
     */
    private void initializeExplorers() {

        // Set Pat's Initial Coordinates
        explorers.add(new Explorer(0,0));

        // Set Chris' Initial Coordinates
        explorers.add(new Explorer(gridWidth - 1, gridHeight - 1));
    }

    /* Name: simulateExploration()
     * Description: Handles exploration simulation loop.
     */
    private void simulateExploration() {

        // Begin Exploration Loop
        while (time < MAX_TIME && !explorersFound) {

            // Update Time
            time++;

            // Move Explorers
            for (Explorer explorer : explorers) {
                explorer.setPosition(getRandomMove(explorer.getPosition()));
            }

            // Perform Location Check
            if (explorers.get(0).comparePositions(explorers.get(1))) {
                explorersFound = true;
            }
        }
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
}
