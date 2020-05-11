package ui.utility;

import ui.res.WoodsFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/* Title:          Lost Woods
 * This File:      DialogUtility.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   DialogUtility is a simple utility class that encapsulates the behavior of creating
 * and displaying dialogs.
 */

public class DialogUtility {


    /*--- Variables ---*/

    private static final String TITLE_LEFT_PADDING = " ";


    /*--- Dialogs ---*/

    public static void createExplorersFoundDialog(DialogEventListener listener, long cycles) {
        JLabel label = new JLabel("The explorers found each other after " + cycles + " cycles!");
        label.setFont(WoodsFont.DIALOG_FONT);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        JOptionPane.showMessageDialog(null, label, " Success!", JOptionPane.DEFAULT_OPTION);

        listener.onOkay();
    }

    public static void createExplorersLostDialog(DialogEventListener listener, long cycles) {
        JLabel label = new JLabel("After " + cycles + " cycles, the explorers remain lost.");
        label.setFont(WoodsFont.DIALOG_FONT);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        JOptionPane.showMessageDialog(null, label, " Explorers Lost", JOptionPane.DEFAULT_OPTION);

        listener.onOkay();
    }


    /*--- Dialog Event Listener ---*/

    public interface DialogEventListener {
        public void onOkay();
    }
}
