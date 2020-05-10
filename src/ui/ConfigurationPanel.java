package ui;

import ui.res.WoodsColor;
import ui.res.WoodsFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private static final int BORDER_PADDING = 17;
    private static final int PANEL_HEIGHT = 100;


    /*--- Constructor ---*/

    public ConfigurationPanel() {
        super();

        this.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, BORDER_PADDING, BORDER_PADDING - 5, BORDER_PADDING),
                BorderFactory.createMatteBorder(1, 0, 1, 0, WoodsColor.WINDOW_BORDER_COLOR)
        ));
        this.setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        this.add(createLeftPanel(), BorderLayout.LINE_START);
        this.add(createSpacerPanel());
        this.add(createRightPanel(), BorderLayout.LINE_END);
    }


    /*--- Private Setup Methods ---*/

    private JPanel createLeftPanel() {

        // Create Base Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(400, 200));

        // Create Version Panel
        JPanel versionPanel = new JPanel();
        versionPanel.setLayout(new BoxLayout(versionPanel, BoxLayout.X_AXIS));
        versionPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Add Label to Version Panel
        JLabel versionLabel = new JLabel();
        versionLabel.setFont(WoodsFont.TEXT_FONT);
        versionLabel.setText("Version: ");
        versionPanel.add(versionLabel);

        versionPanel.add(Box.createHorizontalGlue());

        // Add Dropdown to Version Panel
        String[] versions = { "Simple", "Intermediate", "Advanced" };
        JComboBox versionDropdown = new JComboBox(versions);
        versionDropdown.setSelectedIndex(0);
        versionDropdown.setFont(WoodsFont.TEXT_FONT);
        versionDropdown.setMaximumSize(new Dimension(300, 35));
        versionDropdown.addActionListener(e -> {
            JComboBox dropdown = (JComboBox) e.getSource();
            // TODO - Handle Select Event
        });
        versionPanel.add(versionDropdown);

        // Add Version Panel
        leftPanel.add(versionPanel);

        // Create Speed Panel
        JPanel speedPanel = new JPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.X_AXIS));
        speedPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Add Label to Speed Panel
        JLabel speedLabel = new JLabel();
        speedLabel.setFont(WoodsFont.TEXT_FONT);
        speedLabel.setPreferredSize(new Dimension(200, 35));
        speedLabel.setText("Speed: ");
        speedPanel.add(speedLabel);

        speedPanel.add(Box.createHorizontalGlue());

        // Add Text Field to Speed Panel
        JTextField speedField = new JTextField();
        speedField.setFont(WoodsFont.TEXT_FONT);
        speedField.setMaximumSize(new Dimension(30, 30));
        speedField.setPreferredSize(new Dimension(30, 30));
        speedField.setText("1");
        speedField.setHorizontalAlignment(JTextField.CENTER);
        speedField.addActionListener(e -> {
            JTextField textField = (JTextField) e.getSource();
            // TODO - Handle
        });
        speedPanel.add(speedField);

        // Add Speed Panel
        leftPanel.add(speedPanel);

        // Create Players Panel
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.X_AXIS));
        playersPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        // Add Label to Players Panel
        JLabel playersLabel = new JLabel();
        playersLabel.setFont(WoodsFont.TEXT_FONT);
        playersLabel.setPreferredSize(new Dimension(200, 35));
        playersLabel.setText("Players: ");
        playersPanel.add(playersLabel);

        playersPanel.add(Box.createHorizontalGlue());

        // Add Text Field to Version Panel
        JTextField playerCountField = new JTextField();
        playerCountField.setFont(WoodsFont.TEXT_FONT);
        playerCountField.setMaximumSize(new Dimension(30, 30));
        playerCountField.setPreferredSize(new Dimension(30, 30));
        playerCountField.setText("1");
        playerCountField.setHorizontalAlignment(JTextField.CENTER);
        playerCountField.addActionListener(e -> {
            JTextField textField = (JTextField) e.getSource();
            // TODO - Handle
        });
        playersPanel.add(playerCountField);

        // Add Version Panel
        leftPanel.add(playersPanel);

        // Create Grid Width Panel
        JPanel gridWidthPanel = new JPanel();
        gridWidthPanel.setLayout(new BoxLayout(gridWidthPanel, BoxLayout.X_AXIS));
        gridWidthPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        // Add Label to Grid Width Panel
        JLabel gridWidthLabel = new JLabel();
        gridWidthLabel.setFont(WoodsFont.TEXT_FONT);
        gridWidthLabel.setPreferredSize(new Dimension(200, 35));
        gridWidthLabel.setText("Grid Width: ");
        gridWidthPanel.add(gridWidthLabel);

        gridWidthPanel.add(Box.createHorizontalGlue());

        // Add Text Field to Grid Width Panel
        JTextField gridWidthField = new JTextField();
        gridWidthField.setFont(WoodsFont.TEXT_FONT);
        gridWidthField.setMaximumSize(new Dimension(30, 30));
        gridWidthField.setPreferredSize(new Dimension(30, 30));
        gridWidthField.setText("20");
        gridWidthField.setHorizontalAlignment(JTextField.CENTER);
        gridWidthField.addActionListener(e -> {
            JTextField textField = (JTextField) e.getSource();
            // TODO - Handle
        });
        gridWidthPanel.add(gridWidthField);

        // Add Grid Width Panel
        leftPanel.add(gridWidthPanel);

        // Create Grid Height Panel
        JPanel gridHeightPanel = new JPanel();
        gridHeightPanel.setLayout(new BoxLayout(gridHeightPanel, BoxLayout.X_AXIS));
        gridHeightPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        // Add Label to Grid Height Panel
        JLabel gridHeightLabel = new JLabel();
        gridHeightLabel.setFont(WoodsFont.TEXT_FONT);
        gridHeightLabel.setPreferredSize(new Dimension(200, 35));
        gridHeightLabel.setText("Grid Height: ");
        gridHeightPanel.add(gridHeightLabel);

        gridHeightPanel.add(Box.createHorizontalGlue());

        // Add Text Field to Grid Height Panel
        JTextField gridHeightField = new JTextField();
        gridHeightField.setFont(WoodsFont.TEXT_FONT);
        gridHeightField.setMaximumSize(new Dimension(30, 30));
        gridHeightField.setPreferredSize(new Dimension(30, 30));
        gridHeightField.setText("20");
        gridHeightField.setHorizontalAlignment(JTextField.CENTER);
        gridHeightField.addActionListener(e -> {
            JTextField textField = (JTextField) e.getSource();
            // TODO - Handle
        });
        gridHeightPanel.add(gridHeightField);

        // Add Grid Height Panel
        leftPanel.add(gridHeightPanel);

        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }

    private JPanel createSpacerPanel() {
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(70, 10));
        return spacerPanel;
    }

    private JPanel createRightPanel() {

        // Create Base Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(400, 200));

        // Create Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Add Label to Search Panel
        JLabel searchLabel = new JLabel();
        searchLabel.setFont(WoodsFont.TEXT_FONT);
        searchLabel.setText("Search: ");
        searchPanel.add(searchLabel);

        searchPanel.add(Box.createHorizontalGlue());

        // Add Dropdown to Movement Panel
        String[] searchMethods = { "Randomly", "New Locations" };
        JComboBox searchDropdown = new JComboBox(searchMethods);
        searchDropdown.setSelectedIndex(0);
        searchDropdown.setFont(WoodsFont.TEXT_FONT);
        searchDropdown.setMaximumSize(new Dimension(300, 35));
        searchDropdown.addActionListener(e -> {
            JComboBox dropdown = (JComboBox) e.getSource();
            // TODO - Handle Select Event
        });
        searchPanel.add(searchDropdown);

        // Add Movement Panel
        rightPanel.add(searchPanel);

        // Create Player Distance Panels
        for (int x = 0; x < 4; x++) {

            // Create Player Distance Panel
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
            playerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

            // Add Label to Player Distance Panel
            JLabel playerDistanceLabel = new JLabel();
            playerDistanceLabel.setFont(WoodsFont.TEXT_FONT);
            playerDistanceLabel.setPreferredSize(new Dimension(400, 35));
            playerDistanceLabel.setText("Player " + (x + 1) + " Distance:");
            playerPanel.add(playerDistanceLabel);

            playerPanel.add(Box.createHorizontalGlue());

            // Add Label to Player Distance Panel
            JLabel playerDistanceField = new JLabel();
            playerDistanceField.setFont(WoodsFont.TEXT_FONT);
            playerDistanceField.setMaximumSize(new Dimension(30, 30));
            playerDistanceField.setPreferredSize(new Dimension(30, 30));
            playerDistanceField.setText("10");
            playerPanel.add(playerDistanceField);

            // Add Speed Panel
            rightPanel.add(playerPanel);
        }

        rightPanel.add(Box.createVerticalGlue());

        return rightPanel;
    }
}
