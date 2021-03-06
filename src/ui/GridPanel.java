package ui;

import ui.res.WoodsColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

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
    public static final int DEFAULT_PLAYER_COUNT = 2; // Computer Science Indices *taps temple*
    public static final int MAX_PLAYER_COUNT = 4;
    public static final Point[] DEFAULT_PLAYER_POSITIONS = {
            new Point(0, 0),
            new Point(DEFAULT_WIDTH - 1, DEFAULT_HEIGHT - 1),
            new Point(0, DEFAULT_HEIGHT - 1),
            new Point(DEFAULT_WIDTH - 1, 0)
    };

    private int width;
    private int height;
    private int playerCount;
    private Point[] playerPositions;

    /*--- Constructor ---*/

    public GridPanel() {
        super();

        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.playerCount = DEFAULT_PLAYER_COUNT;
        this.playerPositions = DEFAULT_PLAYER_POSITIONS;
    }


    /*--- Public Methods ---*/

    public void setGridSize(Point size) {
        this.width = size.x;
        this.height = size.y;
        this.repaint();
    }

    public void setPlayerCount(int count) {
        this.playerCount = count + 1;
        if (this.playerCount > MAX_PLAYER_COUNT) this.playerCount = MAX_PLAYER_COUNT;
        this.repaint();
    }

    public void setPlayerPositions(ArrayList<Point> positions) {
        for (int x = 0; x < playerCount; x++) {
            playerPositions[x] = positions.get(x);
        }
        repaint();
    }


    /*--- Draw Method ---*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Setup 2D Graphics
        Graphics2D graphics = (Graphics2D) g;

        // Determine Grid Constants
        int max = width;
        if (height > max) max = height;
        int drawWidth = (this.getSize().width / max) * max;
        int drawHeight = (this.getSize().height / max) * max;
        int squareWidth = drawWidth / max;
        int squareHeight = drawHeight / max;
        int startX = (drawWidth - (width * squareWidth)) / 2;
        int endX = startX + (width * squareWidth);
        int startY = (drawHeight - (height * squareHeight)) / 2;
        int endY = startY + (height * squareHeight);

        // Draw Background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(startX, startY, squareWidth * width, squareHeight * height);

        // Draw Players
        for (int x = 0; x < playerCount; x++) {
            graphics.setColor(WoodsColor.PLAYER_COLORS[x]);
            graphics.fillRect(
                    startX +  playerPositions[x].x * squareWidth,
                    startY + playerPositions[x].y * squareHeight,
                    squareWidth,
                    squareHeight
            );
        }

        // Draw Grid
        int lineWidth = 1;
        graphics.setColor(WoodsColor.GRID_BORDER_COLOR);
        graphics.setStroke(new BasicStroke(lineWidth));
        int positionX = startX + lineWidth / 2;
        for(int x = 0; x < width + 1; x++) {
            Line2D line = new Line2D.Float(positionX, startY, positionX, endY);
            graphics.draw(line);
            positionX += squareWidth;
        }
        int positionY = startY + lineWidth / 2;
        for(int y = 0; y < height + 1; y++) {
            Line2D line = new Line2D.Float(startX, positionY, endX, positionY);
            graphics.draw(line);
            positionY += squareHeight;
        }
    }
}
