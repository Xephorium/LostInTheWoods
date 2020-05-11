package ui;

import model.ProgramVersion;
import ui.utility.DisplayUtility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    }


    /*--- Core WoodsWindow Functionality---*/

    public void setListener(WoodsWindowListener listener) {
        this.listener = listener;
    }

    public void displayWindow() {
        initializeViewClasses();
        addViewClasses();
        frame.setVisible(true);
    }

    public void setProgramVersion(ProgramVersion version) {
        if (version == ProgramVersion.Simple) {

            // Set Simple UI State
            gridFramePanel.setPlayerCount(1);
            configurationPanel.setProgramVersion(ProgramVersion.Simple);
            configurationPanel.setPlayerCount(1);
            configurationPanel.hidePlayerCount();
            configurationPanel.setGridSize(new Point(20, 20));
            configurationPanel.hideGridSize();

        } else if (version == ProgramVersion.Intermediate) {

            // Set Intermediate UI State
            gridFramePanel.setPlayerCount(1);
            configurationPanel.setProgramVersion(ProgramVersion.Intermediate);
            configurationPanel.setPlayerCount(1);
            configurationPanel.showPlayerCount();
            configurationPanel.showGridSize();

        }
    }

    public void setGridSize(Point size) {
        gridFramePanel.setGridSize(size);
    }

    public void setPlayerCount(int count) {
        gridFramePanel.setPlayerCount(count);
        configurationPanel.setPlayerCount(count);
    }

    public void setPlayerPositions(ArrayList<Point> positions) {
        gridFramePanel.setPlayerPositions(positions);
    }

    public void setPlayerDistances(ArrayList<Integer> playerDistances) {
        for (int x = 0; x < playerDistances.size(); x++) {
            configurationPanel.setPlayerDistance(x, playerDistances.get(x));
        }
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
    }

    private void initializeViewClasses() {
        gridFramePanel = new GridFramePanel();
        configurationPanel = new ConfigurationPanel(listener);
        actionPanel = new ActionPanel(listener);
    }

    private void addViewClasses() {
        frame.add(gridFramePanel, BorderLayout.PAGE_START);
        frame.add(configurationPanel, BorderLayout.CENTER);
        frame.add(actionPanel, BorderLayout.PAGE_END);
    }


    /*--- Woods Window Listener ---*/

    public interface WoodsWindowListener {
        public void onVersionChange(ProgramVersion version);
        public void onSpeedChange(int factor);
        public void onPlayerCountChange(int count);
        public void onGridWidthChange(int width);
        public void onGridHeightChange(int height);
        public void onSearchMethodChange(int index);
        public void onStop();
        public void onStart();
    }
}
