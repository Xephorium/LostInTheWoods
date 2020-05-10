package utility;

import java.awt.*;

/* Title:          Lost Woods
 * This File:      DisplayUtility.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   DisplayUtility is a simple utility class that encapsulates the retrieval
 * of common display information.
 */

public class DisplayUtility {

    public static int getWindowStartX(int windowWidth) {
        return (getScreenWidth()/2) - (windowWidth /2);
    }

    public static int getWindowStartY(int windowHeight) {
        return (getScreenHeight()/2) - (windowHeight /2);
    }

    public static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
}
