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

    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;

    private WoodsWindowListener listener;
    private JFrame frame;
    private GridFramePanel gridFramePanel;
    private ConfigurationPanel configurationPanel;
    private ActionPanel actionPanel;


    /*--- Constructor ---*/

    public WoodsWindow() {

        setGlobalLookAndFeel();
        initializeFrameAttributes();
        initializeViewClasses();

        frame.add(gridFramePanel, BorderLayout.PAGE_START);
        frame.add(configurationPanel, BorderLayout.CENTER);
        frame.add(actionPanel, BorderLayout.PAGE_END);
    }


    /*--- Core WoodsWindow Functionality---*/

    public void setListener(WoodsWindowListener listener) {
        this.listener = listener;
    }

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
        gridFramePanel = new GridFramePanel();
        configurationPanel = new ConfigurationPanel(listener);
        actionPanel = new ActionPanel(listener);
    }


    /*--- Woods Window Listener ---*/

    public interface WoodsWindowListener {
        public void onVersionChange(int index);
        public void onSpeedChange(int factor);
        public void onPlayerCountChange(int count);
        public void onGridWidthChange(int width);
        public void onGridHeightChange(int height);
        public void onSearchMethodChange(int index);
        public void onStop();
        public void onStart();
    }
}
