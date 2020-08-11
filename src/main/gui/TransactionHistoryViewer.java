package gui;

import model.Transaction;
import model.TransactionHistory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.List;

/**
 *  Creates the full transaction history window to view all the transactions conducted with various contacts.
 */

public class TransactionHistoryViewer {
    private static JDialog transactionHistoryDialog;
    private static JFrame mainWindow;
    private DecimalFormat df = new DecimalFormat("#.00");

    // EFFECTS: constructor to initialize new window
    public TransactionHistoryViewer(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

    // EFFECTS: creates a window that displays all of the users contacts
    // NOTE: dialog.modalitytype.document_modal means that you can't click the main menu while the pop up menu is open!!
    public void createTransactionHistoryWindow(TransactionHistory transactions) {
        if (transactionHistoryDialog == null || !transactionHistoryDialog.isVisible()) {
            initTransactionHistoryDialog();

            JPanel panel = initJPanel();

            List<Transaction> transactionHistory = transactions.getTransactions();
            JLabel transaction;
            if (transactionHistory.size() == 0) {
                transaction = new JLabel("No transactions to show.");
                panel.add(transaction);
            } else {
                for (Transaction t : transactionHistory) {
                    transaction = new JLabel("\nTransaction ID: " + t.getTransactionID()
                            + ", Contact: " + t.getContact().getName() + ", Amount: $" + df.format(t.getAmount())
                            + ", Date: " + t.getDateOfTransaction());
                    panel.add(transaction);
                }
            }

            panel.setVisible(true);
            transactionHistoryDialog.add(panel);
            transactionHistoryDialog.setVisible(true);
        } else {
            transactionHistoryDialog.requestFocus();
        }
        transactionHistoryDialog.dispatchEvent(new WindowEvent(transactionHistoryDialog, WindowEvent.WINDOW_CLOSING));
    }

    // EFFECTS: initializes transaction history dialog
    private void initTransactionHistoryDialog() {
        transactionHistoryDialog = new JDialog(mainWindow, "View Full Transaction History",
                                    Dialog.ModalityType.DOCUMENT_MODAL);
        transactionHistoryDialog.setSize(800, 400);
        transactionHistoryDialog.setLocationRelativeTo(null);
    }

    // EFFECTS: initializes jpanel to display contact names
    public JPanel initJPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(new Insets(20, 45, 50, 45)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }
}
