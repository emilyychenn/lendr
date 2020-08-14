package gui;

import java.awt.*;
import javax.swing.*;

/**
 *  Creates the main window of the LoanApp GUI.
 */

public class LoanAppGUI extends JFrame {
    protected static final int WINDOW_WIDTH = 400;
    protected static final int WINDOW_HEIGHT = 620;
    protected static final int BUTTON_X_POS = 100;
    protected static final int BUTTON1_Y_POS = 165;
    protected static final int DISTANCE_BETWEEN_BUTTONS = 70;
    private static JFrame frame;
    private static JButton loadDataButton;
    private static JButton newContactButton;
    private static JButton addTransactionButton;
    private static JButton viewTransactionsButton;
    private static JButton viewContactsButton;
    private static JButton saveQuitButton;


    // EFFECTS: creates and sets up the GUI window, then displays the window
    public static void createWindow() {
        frame = new JFrame("Money Lending Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        Color backgroundGreen = new Color(0, 168, 107);
        frame.getContentPane().setBackground(backgroundGreen);

        createLogo();
        createLoadDataButton();
        createNewContactButton();
        createAddTransactionButton();
        createViewTransactionsButton();
        createViewContactsButton();
        createSaveQuitButton();

        // display the window:
        frame.setLocationRelativeTo(null);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // EFFECTS: processes and adds logo image to the top of the window
    private static void createLogo() {
        ImageIcon logo = new ImageIcon("./images/logo.png");
        Image logoImg = logo.getImage();
        Image resizedLogo = logoImg.getScaledInstance(206, 139, Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        JLabel picLabel = new JLabel(logo);
        picLabel.setBounds(BUTTON_X_POS, 0,206,139);
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(picLabel);
    }

    // EFFECTS: creates load data button
    public static void createLoadDataButton() {
        ImageIcon loadDataImg = new ImageIcon("./images/loadDataButton.png");
        Image img0 = loadDataImg.getImage();
        Image resizedLoadData = img0.getScaledInstance(206, 42, Image.SCALE_SMOOTH);
        loadDataImg = new ImageIcon(resizedLoadData);
        loadDataButton = new JButton(loadDataImg);
        loadDataButton.setBounds(BUTTON_X_POS, BUTTON1_Y_POS, 206, 42);
        loadDataButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(loadDataButton);
    }

    // EFFECTS: creates new contact button
    public static void createNewContactButton() {
        ImageIcon newContactImg = new ImageIcon("./images/newContactButton.png");
        Image img = newContactImg.getImage();
        Image resizedNewContact = img.getScaledInstance(206, 42, Image.SCALE_SMOOTH);
        newContactImg = new ImageIcon(resizedNewContact);
        newContactButton = new JButton(newContactImg);
        newContactButton.setBounds(BUTTON_X_POS,BUTTON1_Y_POS + DISTANCE_BETWEEN_BUTTONS, 206, 42);
        newContactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(newContactButton);
    }

    // EFFECTS: creates add transaction button
    private static void createAddTransactionButton() {
        ImageIcon addTransactionImg = new ImageIcon("./images/addTransactionButton.png");
        Image img2 = addTransactionImg.getImage();
        Image resizedAddTransaction = img2.getScaledInstance(206, 42, Image.SCALE_SMOOTH);
        addTransactionImg = new ImageIcon(resizedAddTransaction);
        addTransactionButton = new JButton(addTransactionImg);
        addTransactionButton.setBounds(BUTTON_X_POS,BUTTON1_Y_POS + (2 * DISTANCE_BETWEEN_BUTTONS), 206, 42);
        addTransactionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(addTransactionButton);
    }

    // EFFECTS: creates edit transaction button
    private static void createViewTransactionsButton() {
        ImageIcon viewTransactionsImg = new ImageIcon("./images/viewTransactionsButton.png");
        Image img3 = viewTransactionsImg.getImage();
        Image resizedViewTransactions = img3.getScaledInstance(206, 42, Image.SCALE_SMOOTH);
        viewTransactionsImg = new ImageIcon(resizedViewTransactions);
        viewTransactionsButton = new JButton(viewTransactionsImg);
        viewTransactionsButton.setBounds(BUTTON_X_POS,BUTTON1_Y_POS + (3 * DISTANCE_BETWEEN_BUTTONS), 206, 42);
        viewTransactionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(viewTransactionsButton);
    }

    // EFFECTS: creates view contact list button
    private static void createViewContactsButton() {
        ImageIcon viewContactsImg = new ImageIcon("./images/viewContactsButton.png");
        Image img4 = viewContactsImg.getImage();
        Image resizedViewContacts = img4.getScaledInstance(206, 42, Image.SCALE_SMOOTH);
        viewContactsImg = new ImageIcon(resizedViewContacts);
        viewContactsButton = new JButton(viewContactsImg);
        viewContactsButton.setBounds(BUTTON_X_POS,BUTTON1_Y_POS + (4 * DISTANCE_BETWEEN_BUTTONS), 206, 42);
        viewContactsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(viewContactsButton);
    }

    // EFFECTS: creates save and quit button
    private static void createSaveQuitButton() {
        ImageIcon saveQuitImg = new ImageIcon("./images/saveAndQuitButton.png");
        Image img5 = saveQuitImg.getImage();
        Image resizedSaveQuit = img5.getScaledInstance(206, 42, Image.SCALE_SMOOTH);
        saveQuitImg = new ImageIcon(resizedSaveQuit);
        saveQuitButton = new JButton(saveQuitImg);
        saveQuitButton.setBounds(BUTTON_X_POS,BUTTON1_Y_POS + (5 * DISTANCE_BETWEEN_BUTTONS), 206, 42);
        saveQuitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(saveQuitButton);
    }

    // EFFECTS: returns load data button
    public static JButton getLoadDataButton() {
        return loadDataButton;
    }

    // EFFECTS: returns new contact button
    public static JButton getNewContactButton() {
        return newContactButton;
    }

    // EFFECTS: returns add transaction button
    public static JButton getAddTransactionButton() {
        return addTransactionButton;
    }

    // EFFECTS: returns edit transaction button
    public static JButton getViewTransactionsButton() {
        return viewTransactionsButton;
    }

    // EFFECTS: returns view contacts button
    public static JButton getViewContactsButton() {
        return viewContactsButton;
    }

    // EFFECTS: returns save and quit button
    public static JButton getSaveQuitButton() {
        return saveQuitButton;
    }

    // EFFECTS: returns current JFrame
    public static JFrame getFrame() {
        return frame;
    }
}
