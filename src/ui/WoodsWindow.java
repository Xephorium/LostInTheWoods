package ui;

import utility.DisplayUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

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

    //private ArmoryWindowListener listener;
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 500;

    private JFrame frame;
    //private InstallDirectoryPanel woodsPanel;


    /*--- Constructor(s) ---*/

    //public WoodsWindow(ArmoryWindowListener listener) {
    public WoodsWindow() {
        //this.listener = listener;

        setGlobalLookAndFeel();
        initializeFrameAttributes();
        //initializeViewClasses();
        //createProfilePanels();

//        frame.add(installDirectoryPanel, BorderLayout.PAGE_START);
//        frame.add(factionConfigurationPanel, BorderLayout.CENTER);
//        frame.add(createProfilePanels(), BorderLayout.EAST);
    }


    /*--- Core ArmoryWindow Functionality ---*/


    // General

    public void displayWindow() {
        frame.setVisible(true);
    }

//    public void updateProfileList(ProfileList profileList) {
//        factionConfigurationPanel.updateProfileList(profileList);
//        profileConfigurationPanel.updateProfileList(profileList);
//    }
//
//    public void displayProblemSavingDialog() {
//        DialogFactory.createProblemSavingDialog(frame);
//    }


    // Install Directory

//    public void displayDirectoryChooser() {
//        directoryChooser.displayChooser();
//    }
//
//    public void displayGameFoundDialog() {
//        DialogFactory.createGameFoundDialog(frame);
//    }
//
//    public void displayUserLocalNotFoundDialog(String user) {
//        DialogFactory.createUserLocalNotFoundDialog(frame, user);
//    }
//
//    public void displayInstallationFallbackDialog() {
//        DialogFactory.createInstallationFallbackDialog(frame);
//    }
//
//    public void displayGameNotFoundDialog() {
//        DialogFactory.createGameNotFoundDialog(frame);
//    }
//
//    public void setValidInstallDirectory(String directory) {
//        installDirectoryPanel.setValidInstallDirectory(directory);
//    }
//
//    public void setInvalidInstallDirectory() {
//        installDirectoryPanel.setInvalidInstallDirectory();
//    }
//
//    public void displayNoInstallationSetDialog() {
//        DialogFactory.createNoInstallationSetDialog(frame);
//    }
//
//
//    // Faction Configuration
//
//    public void updateUNSCPlayerConfiguration(List<Integer> unscPlayerProfiles) {
//        factionConfigurationPanel.updateUNSCPlayerConfiguration(unscPlayerProfiles);
//    }
//
//    public void updateCovenantPlayerConfiguration(List<Integer> covenantPlayerProfiles) {
//        factionConfigurationPanel.updateCovenantPlayerConfiguration(covenantPlayerProfiles);
//    }
//
//    public void displayConfigurationSavedDialog() {
//        DialogFactory.createConfigurationSavedDialog(frame);
//    }
//
//
//    // Profile Configuration
//
//    public void setSelectedProfile(Profile profile) {
//        profileConfigurationPanel.setSelectedProfile(profile);
//        profileConfigurationPanel.setWorkingProfile(profile);
//        profilePreviewPanel.setSelectedProfile(profile);
//    }
//
//    public void selectNewProfile(Profile profile) {
//        profileConfigurationPanel.selectNewProfile(profile);
//        profileConfigurationPanel.setWorkingProfile(profile);
//    }
//
//    public void setWorkingProfile(Profile profile) {
//        profileConfigurationPanel.setWorkingProfile(profile);
//        profilePreviewPanel.setSelectedProfile(profile);
//    }
//
//    public void disableConfigurationEdit() {
//        factionConfigurationPanel.disableConfigurationEdit();
//    }
//
//    public void enableConfigurationEdit() {
//        factionConfigurationPanel.enableConfigurationEdit();
//    }
//
//    public void disableProfileEdit() {
//        profileConfigurationPanel.disableProfileEdit();
//    }
//
//    public void enableProfileEdit() {
//        profileConfigurationPanel.enableProfileEdit();
//    }
//
//    public void displayColorChooserDialog(Color initialColor, ColorChooser.ColorChooserListener listener) {
//        DialogFactory.createColorChooserDialog(initialColor, listener);
//    }
//
//    public void displayProfileMustHaveNameDialog() {
//        DialogFactory.createProfileMustHaveNameDialog(frame);
//    }
//
//    public void displayProfileSavedDialog() {
//        DialogFactory.createProfileSavedDialog(frame);
//    }
//
//    public void displayNoChangesToSaveDialog() {
//        DialogFactory.createNoChangesToSaveDialog(frame);
//    }
//
//    public void displayDeleteProfileDialog(VerifyActionListener listener, String profileName) {
//        DialogFactory.createDeleteProfileDialog(listener, profileName);
//    }


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

//    private void initializeViewClasses() {
//        installDirectoryPanel = new InstallDirectoryPanel(listener);
//        factionConfigurationPanel = new FactionConfigurationPanel(listener);
//        profileConfigurationPanel = new ProfileConfigurationPanel(listener);
//        profilePreviewPanel = new ProfilePreviewPanel();
//        directoryChooser = new DirectoryChooser(listener);
//    }
//
//    private JPanel createProfilePanels() {
//        JPanel eastPanel = new JPanel(new BorderLayout());
//        eastPanel.setBorder(new EmptyBorder(
//                ArmoryDimension.PANEL_PADDING / 2,
//                ArmoryDimension.PANEL_PADDING / 2,
//                ArmoryDimension.WINDOW_PADDING_VERTICAL,
//                ArmoryDimension.WINDOW_PADDING_HORIZONTAL));
//        eastPanel.setPreferredSize(new Dimension(
//                ArmoryDimension.PREVIEW_PANEL_WIDTH
//                        + ArmoryDimension.PANEL_PADDING / 2
//                        + ArmoryDimension.WINDOW_PADDING_HORIZONTAL,
//                0));
//        eastPanel.setBackground(ArmoryColor.WINDOW_BACKGROUND_COLOR);
//
//        eastPanel.add(profileConfigurationPanel, BorderLayout.CENTER);
//        eastPanel.add(profilePreviewPanel, BorderLayout.PAGE_END);
//        return eastPanel;
//    }
}
