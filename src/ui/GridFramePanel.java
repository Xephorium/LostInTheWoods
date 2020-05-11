package ui;

import ui.res.WoodsColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/* Title:          Lost Woods
 * This File:      GridFramePanel.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   GridFramePanel is the uppermost section of the WoodsWindow interface. It holds and frames a GridPanel, which
 * draws a visual representation of the exploration grid.
 */

public class GridFramePanel extends JPanel {


    /*--- Variable Declarations ---*/

    private static final int BORDER_WIDTH = 1;
    private static final int BORDER_PADDING = 10;
    private static final int PANEL_HEIGHT = 450;

    private GridPanel gridPanel;


    /*--- Constructor ---*/

    public GridFramePanel() {
        super();

        this.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        this.setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        this.setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        this.add(createGridPanel(), BorderLayout.CENTER);
    }


    /*--- Public Methods ---*/

    public void setGridSize(Point size) {
        gridPanel.setGridSize(size);
    }

    public void setPlayerCount(int count) {
        gridPanel.setPlayerCount(count);
    }


    /*--- Private Setup Methods ---*/

    private GridPanel createGridPanel() {
        gridPanel = new GridPanel();
        gridPanel.setPreferredSize(new Dimension(410,410));
        return gridPanel;
    }
}

