package ui;

import model.ProgramVersion;
import ui.WoodsWindow.WoodsWindowListener;
import ui.res.WoodsColor;
import ui.res.WoodsFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private static final int BORDER_PADDING = 17;
    private static final int PANEL_HEIGHT = 100;

    private WoodsWindowListener listener;

    private JComboBox versionDropdown;
    private JLabel playerCountLabel;
    private JTextField playerCountField;
    private JLabel gridWidthLabel;
    private JTextField gridWidthField;
    private JLabel gridHeightLabel;
    private JTextField gridHeightField;
    private JLabel[] playerDistanceFields = {new JLabel(), new JLabel(), new JLabel(), new JLabel()};

    private boolean updatingPlayerCount = false;


    /*--- Constructor ---*/

    public ConfigurationPanel(WoodsWindowListener listener) {
        super();

        this.listener = listener;
        this.setBackground(WoodsColor.WINDOW_BACKGROUND_COLOR);
        this.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, BORDER_PADDING, BORDER_PADDING - 5, BORDER_PADDING),
                BorderFactory.createMatteBorder(BORDER_WIDTH, 0, BORDER_WIDTH, 0, WoodsColor.WINDOW_BORDER_COLOR)
        ));
        this.setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        this.add(createLeftPanel(), BorderLayout.LINE_START);
        this.add(createSpacerPanel());
        this.add(createRightPanel(), BorderLayout.LINE_END);
    }


    /*--- Public Methods ---*/

    public void setProgramVersion(ProgramVersion programVersion) {
        versionDropdown.setSelectedIndex(programVersion.ordinal());
    }

    public void showPlayerCount() {
        playerCountLabel.setVisible(true);
        playerCountField.setVisible(true);
    }

    public void hidePlayerCount() {
        playerCountLabel.setVisible(false);
        playerCountField.setVisible(false);
    }

    public void setPlayerCount(int count) {
        updatingPlayerCount = true;
        playerCountField.setText("" + (count + 1));
        updatingPlayerCount = false;
    }

    public void showGridSize() {
        gridWidthLabel.setVisible(true);
        gridWidthField.setVisible(true);
        gridHeightLabel.setVisible(true);
        gridHeightField.setVisible(true);
    }

    public void hideGridSize() {
        gridWidthLabel.setVisible(false);
        gridWidthField.setVisible(false);
        gridHeightLabel.setVisible(false);
        gridHeightField.setVisible(false);
    }

    public void setGridSize(Point size) {
        gridWidthField.setText("" + size.x);
        gridHeightField.setText("" + size.y);
    }

    public void setPlayerDistance(int player, int distance) {
        playerDistanceFields[0].setText("" + distance);
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
        String[] versions = { "Simple", "Intermediate" };
        versionDropdown = new JComboBox<>(versions);
        versionDropdown.setSelectedIndex(0);
        versionDropdown.setFont(WoodsFont.TEXT_FONT);
        versionDropdown.setMaximumSize(new Dimension(300, 35));
        versionDropdown.addActionListener(e -> {
            JComboBox dropdown = (JComboBox) e.getSource();
            listener.onVersionChange(ProgramVersion.values()[dropdown.getSelectedIndex()]);
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
        speedField.getDocument().addDocumentListener(getIntegerTextValidator(speedField, 1, 10, integer -> {
            listener.onSpeedChange(integer);
        }));
        speedPanel.add(speedField);

        // Add Speed Panel
        leftPanel.add(speedPanel);

        // Create Players Panel
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.X_AXIS));
        playersPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        // Add Label to Players Panel
        playerCountLabel = new JLabel();
        playerCountLabel.setFont(WoodsFont.TEXT_FONT);
        playerCountLabel.setPreferredSize(new Dimension(200, 35));
        playerCountLabel.setText("Players: ");
        playersPanel.add(playerCountLabel);

        playersPanel.add(Box.createHorizontalGlue());

        // Add Text Field to Version Panel
        playerCountField = new JTextField();
        playerCountField.setFont(WoodsFont.TEXT_FONT);
        playerCountField.setMaximumSize(new Dimension(30, 30));
        playerCountField.setPreferredSize(new Dimension(30, 30));
        playerCountField.setText("2");
        playerCountField.setHorizontalAlignment(JTextField.CENTER);
        playerCountField.getDocument().addDocumentListener(getIntegerTextValidator(playerCountField, 2, 4, integer -> {
            if (!updatingPlayerCount) listener.onPlayerCountChange(integer - 1);
        }));
        playersPanel.add(playerCountField);

        // Add Version Panel
        leftPanel.add(playersPanel);

        // Create Grid Width Panel
        JPanel gridWidthPanel = new JPanel();
        gridWidthPanel.setLayout(new BoxLayout(gridWidthPanel, BoxLayout.X_AXIS));
        gridWidthPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        // Add Label to Grid Width Panel
        gridWidthLabel = new JLabel();
        gridWidthLabel.setFont(WoodsFont.TEXT_FONT);
        gridWidthLabel.setPreferredSize(new Dimension(200, 35));
        gridWidthLabel.setText("Grid Width: ");
        gridWidthPanel.add(gridWidthLabel);

        gridWidthPanel.add(Box.createHorizontalGlue());

        // Add Text Field to Grid Width Panel
        gridWidthField = new JTextField();
        gridWidthField.setFont(WoodsFont.TEXT_FONT);
        gridWidthField.setMaximumSize(new Dimension(40, 30));
        gridWidthField.setPreferredSize(new Dimension(40, 30));
        gridWidthField.setText("20");
        gridWidthField.setHorizontalAlignment(JTextField.CENTER);
        gridWidthField.getDocument().addDocumentListener(getIntegerTextValidator(gridWidthField, 1, 100, integer -> {
            listener.onGridWidthChange(integer);
        }));
        gridWidthPanel.add(gridWidthField);

        // Add Grid Width Panel
        leftPanel.add(gridWidthPanel);

        // Create Grid Height Panel
        JPanel gridHeightPanel = new JPanel();
        gridHeightPanel.setLayout(new BoxLayout(gridHeightPanel, BoxLayout.X_AXIS));
        gridHeightPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        // Add Label to Grid Height Panel
        gridHeightLabel = new JLabel();
        gridHeightLabel.setFont(WoodsFont.TEXT_FONT);
        gridHeightLabel.setPreferredSize(new Dimension(200, 35));
        gridHeightLabel.setText("Grid Height: ");
        gridHeightPanel.add(gridHeightLabel);

        gridHeightPanel.add(Box.createHorizontalGlue());

        // Add Text Field to Grid Height Panel
        gridHeightField = new JTextField();
        gridHeightField.setFont(WoodsFont.TEXT_FONT);
        gridHeightField.setMaximumSize(new Dimension(40, 30));
        gridHeightField.setPreferredSize(new Dimension(40, 30));
        gridHeightField.setText("20");
        gridHeightField.setHorizontalAlignment(JTextField.CENTER);
        gridHeightField.getDocument().addDocumentListener(getIntegerTextValidator(gridHeightField, 1, 100, integer -> {
            listener.onGridHeightChange(integer);
        }));
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
        searchPanel.setMinimumSize(new Dimension(35, 40));
        searchPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
//        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
//
//        // Add Label to Search Panel
//        JLabel searchLabel = new JLabel();
//        searchLabel.setFont(WoodsFont.TEXT_FONT);
//        searchLabel.setText("Search: ");
//        searchPanel.add(searchLabel);
//
//        searchPanel.add(Box.createHorizontalGlue());
//
//        // Add Dropdown to Movement Panel
//        String[] searchMethods = { "Randomly", "New Locations" };
//        JComboBox searchDropdown = new JComboBox<>(searchMethods);
//        searchDropdown.setSelectedIndex(0);
//        searchDropdown.setFont(WoodsFont.TEXT_FONT);
//        searchDropdown.setMaximumSize(new Dimension(300, 35));
//        searchDropdown.addActionListener(e -> {
//            JComboBox dropdown = (JComboBox) e.getSource();
//            listener.onSearchMethodChange(dropdown.getSelectedIndex());
//        });
//        searchPanel.add(searchDropdown);

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
            playerDistanceFields[x].setFont(WoodsFont.TEXT_FONT);
            playerDistanceFields[x].setMaximumSize(new Dimension(30, 30));
            playerDistanceFields[x].setPreferredSize(new Dimension(30, 30));
            playerDistanceFields[x].setText("0");
            playerPanel.add(playerDistanceFields[x]);

            // Add Speed Panel
            rightPanel.add(playerPanel);
        }

        rightPanel.add(Box.createVerticalGlue());

        return rightPanel;
    }


    /*--- Validation ---*/

    private DocumentListener getIntegerTextValidator(JTextField textField, int min, int max, IntegerTextValidationListener listener) {
        return new DocumentListener() {
            private boolean overriding = false;
            public void changedUpdate(DocumentEvent e) { handle(); }
            public void removeUpdate(DocumentEvent e) { handle(); }
            public void insertUpdate(DocumentEvent e) { handle(); }
            private void handle() {
                if (!overriding) try {
                    if (textField.getText().equals("")) return;
                    int integer = Integer.parseInt(textField.getText());
                    if (integer < min) resetField(min);
                    else if (integer > max) resetField(max);
                    else listener.onValid(integer);
                } catch (Exception exception) {
                    resetField(min);
                }
            }
            private void resetField(int value) {
                SwingUtilities.invokeLater(() -> {
                    overriding = true;
                    textField.setText("" + value);
                    listener.onValid(value);
                    overriding = false;
                });
            }
        };
    }

    private interface IntegerTextValidationListener {
        public void onValid(int integer);
    }
}

