package gui;

import javax.swing.*;

/**
 *  Runs the GUI-based program (with some console messages).
 */

public class MainGUI {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            // EFFECTS: runs the GUI and prompts user for their name before starting
            public void run() {
                ButtonActionHandler newGUI = new ButtonActionHandler();

                String[] options = {"OK"};
                String name = "";
                JPanel panel = new JPanel();
                JLabel lbl = new JLabel("Enter your name: ");
                JTextField txt = new JTextField(10);
                panel.add(lbl);
                panel.add(txt);
                int selectedOption = JOptionPane.showOptionDialog(null, panel, "Welcome",
                        JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (selectedOption == 0) {
                    name = txt.getText();
                }

                JOptionPane.showMessageDialog(newGUI.getContentPane(),"Hello, " + name + ".");
            }
        });
    }
}
