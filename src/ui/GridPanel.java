package ui;

import ui.res.WoodsColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/* Title:          Lost Woods
 * This File:      GridPanel.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   GridPanel is the uppermost section of the WoodsWindow interface and contains
 * a visual representation of the exploration grid.
 */

public class GridPanel extends JPanel {


    /*--- Variable Declarations ---*/

    private static final int BORDER_WIDTH = 1;
    private static final int BORDER_PADDING = 10;
    private static final int PANEL_HEIGHT = 450;


    /*--- Constructor ---*/

    public GridPanel() {
        super();

        this.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        this.setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        this.setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        this.add(createBlankPanel(), BorderLayout.CENTER);
    }


    /*--- Private Setup Methods ---*/

    private JPanel createBlankPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.WHITE);
        emptyPanel.setPreferredSize(new Dimension(415,415));
        return emptyPanel;
    }
}

