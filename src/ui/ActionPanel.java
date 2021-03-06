package ui;

import ui.WoodsWindow.WoodsWindowListener;
import ui.res.WoodsColor;
import ui.res.WoodsFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/* Title:          Lost Woods
 * This File:      ActionPanel.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   ActionPanel is the bottom section of the WoodsWindow interface and contains
 * the user's primary simulation actions.
 */

public class ActionPanel extends JPanel {

/*--- Variable Declarations ---*/

    private static final int BORDER_PADDING_HORIZONTAL = 10;
    private static final int PANEL_HEIGHT = 80;

    private WoodsWindowListener listener;
    private JButton stopButton;
    private JButton runButton;


    /*--- Constructor ---*/

    public ActionPanel(WoodsWindowListener listener) {
        super(new FlowLayout(FlowLayout.RIGHT));

        this.listener = listener;
        this.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        this.setBorder(new EmptyBorder(
                0,
                BORDER_PADDING_HORIZONTAL,
                0,
                BORDER_PADDING_HORIZONTAL
        ));
        this.setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        this.add(createStopButton());
        this.add(createRunButton());
    }


    /*--- Private Setup Methods ---*/

    private JPanel createBlankPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        emptyPanel.setPreferredSize(new Dimension(1000, 10)); // Note: max width to force buttons right
        return emptyPanel;
    }

    private JButton createStopButton() {
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(200, 60));
        stopButton.setFont(WoodsFont.BUTTON_FONT);
        stopButton.setForeground(WoodsColor.BUTTON_STOP);
        stopButton.addActionListener(actionEvent -> listener.onStop());
        return stopButton;
    }

    private JButton createRunButton() {
        runButton = new JButton("Run");
        runButton.setPreferredSize(new Dimension(200, 60));
        runButton.setFont(WoodsFont.BUTTON_FONT);
        runButton.setForeground(WoodsColor.BUTTON_RUN);
        runButton.addActionListener(actionEvent -> listener.onStart());
        return runButton;
    }
}

