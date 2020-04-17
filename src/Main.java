
/* Title:          Lost Woods
 * This File:      Main.java
 * Other Files:    LostWoods.java (Contains Simulation Logic)
 *                 Explorer.java (Contains Explorer Model)
 * Developer:      Chris Cruzen
 * Dev Email:      cac6g3@mail.umsl.edu
 * Course:         CS4500-02
 * Date:           02.03.2020
 * Description:
 *
 *   Main is a simple container class for the Lost Woods program.
 * It instantiates an object of the LostWoods class and then
 * delegates simulation.
 */

public class Main {

    public static void main(String[] args) {

        // Create LostWoods Class & Run Simulation
        LostWoods lostWoods = new LostWoods();
        lostWoods.runSimulation();
    }
}
