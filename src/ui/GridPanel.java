package ui;

import ui.res.WoodsColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/* Title:          Lost Woods
 * This File:      GridPanel.java
 * Team Name:      Aerosol Strike Force
 * Developers:     Chris Cruzen, James Brown, Alec Grizzell, Jacob Brown
 * Dev Emails:     cac6g3@mail.umsl.edu, alecgrizzell@gmail.com
 * Course:         CS4500-02
 * Date:           05.10.2020
 * Description:
 *
 *   GridPanel is the visual core of the Lost Woods simulation. It draws a real-time grid
 * representing the woods and each player in their current position.
 */

public class GridPanel extends JPanel {


    /*--- Variable Declarations ---*/

    public static final int DEFAULT_WIDTH = 20;
    public static final int DEFAULT_HEIGHT = 20;

    private int width;
    private int height;

    /*--- Constructor ---*/

    public GridPanel() {
        super();

        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }


    /*--- Draw Method ---*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Setup 2D Graphics
        Graphics2D graphics = (Graphics2D) g;

        // Draw Background
        int actualWidth = (this.getSize().width / width) * width;
        int actualHeight = (this.getSize().height / height) * height;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, actualWidth, actualHeight);

        // Draw Grid
        int lineWidth = 1;
        graphics.setColor(WoodsColor.WINDOW_BORDER_COLOR);
        graphics.setStroke(new BasicStroke(lineWidth));
        int positionX = lineWidth / 2;
        for(int x = 0; x < width + 1; x++) {
            Line2D line = new Line2D.Float(positionX, 0, positionX, actualHeight);
            graphics.draw(line);
            positionX += actualWidth / width;
        }
        int positionY = lineWidth / 2;
        for(int y = 0; y < height + 1; y++) {
            Line2D line = new Line2D.Float(0, positionY, actualWidth, positionY);
            graphics.draw(line);
            positionY += actualHeight / height;
        }


    }
}
