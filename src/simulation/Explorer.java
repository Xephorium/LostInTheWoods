package simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/* Title:          Lost Woods
 * This File:      Explorer.java
 * Other Files:    Main.java (Contains Main)
 *                 LostWoods.java (Contains Simulation Logic)
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   Explorer is a simple model class representing a single explorer
 * to be placed on the grid. It maintains the explorer's position and
 * some metadata about their journey.
 */

public class Explorer {


    /*--- Variable Declarations ---*/

    private Point position;
    private List<Point> exploredLocations;
    private int distanceTravelled = 0;


    /*--- Constructor --*/

    Explorer(int x, int y) {
        position = new Point();
        position.x = x;
        position.y = y;
        exploredLocations = new ArrayList<>();
    }


    /*--- Accessors ---*/

    Point getPosition() {
        return position;
    }

    void setPosition(Point newPosition) {

        // Update Position
        if (!newPosition.equals(position)) {
            position = newPosition;
            distanceTravelled++;
        }

        // Update Travel Record
        if (!exploredLocations.contains(position)) {
            exploredLocations.add(position);
        }
    }

    void setPosition(int x, int y) {
        position = new Point(x, y);
    }

    int getNumberOfCellsExplored() {
        return exploredLocations.size();
    }

    int getDistanceTravelled() {
        return distanceTravelled;
    }

    boolean comparePositions(Explorer explorer) {
        return explorer.getPosition().equals(this.getPosition());
    }
}
