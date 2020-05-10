package ui;

import ui.utility.DisplayUtility;

import javax.swing.*;
import java.awt.*;

/* Title:          Lost Woods
 * This File:      WoodsWindow.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   WoodsWindow is the root UI class of Lost Woods. Built from the Java SWING library,
 * it displays a JFrame that contains JPanels for each section of the interface.
 */

public class WoodsWindow {


    /*--- Variables ---*/

    //private ArmoryWindowListener listener;
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;

    private JFrame frame;
    private GridPanel gridPanel;
    private ConfigurationPanel configurationPanel;
    private ActionPanel actionPanel;


    /*--- Constructor ---*/

    public WoodsWindow() {

        setGlobalLookAndFeel();
        initializeFrameAttributes();
        initializeViewClasses();

        frame.add(gridPanel, BorderLayout.PAGE_START);
        frame.add(configurationPanel, BorderLayout.CENTER);
        frame.add(actionPanel, BorderLayout.PAGE_END);
    }


    /*--- Core WoodsWindow Functionality---*/

    public void displayWindow() {
        frame.setVisible(true);
    }


    /*--- Private Methods --*/

    private void setGlobalLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // A Whole Lot of Nothin'
        }
    }

    private void initializeFrameAttributes() {
        frame = new JFrame("Lost Woods");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(
                DisplayUtility.getWindowStartX(WINDOW_WIDTH),
                DisplayUtility.getWindowStartY(WINDOW_HEIGHT)
        );
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setIconImages((new ArmoryImage()).ICON_APPLICATION_MAIN_LIST);
    }

    private void initializeViewClasses() {
        gridPanel = new GridPanel();
        configurationPanel = new ConfigurationPanel();
        actionPanel = new ActionPanel();
    }
}