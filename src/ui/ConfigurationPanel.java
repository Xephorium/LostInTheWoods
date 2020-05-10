package ui;

import ui.res.WoodsColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/* Title:          Lost Woods
 * This File:      ConfigurationPanel.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   ConfigurationPanel is the middle section of the WoodsWindow interface
 * and contains setup fields for customizing the simulation.
 */

public class ConfigurationPanel extends JPanel {


    /*--- Variable Declarations ---*/

    private static final int BORDER_WIDTH = 1;
    private static final int BORDER_PADDING = 10;
    private static final int PANEL_HEIGHT = 100;


    /*--- Constructor ---*/

    public ConfigurationPanel() {
        super();

        this.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING),
                BorderFactory.createMatteBorder(1, 0, 1, 0, WoodsColor.WINDOW_BORDER_COLOR)
        ));
        this.setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        this.add(createBlankPanel(), BorderLayout.CENTER);
    }


    /*--- Private Setup Methods ---*/

    private JPanel createBlankPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        return emptyPanel;
    }
}

