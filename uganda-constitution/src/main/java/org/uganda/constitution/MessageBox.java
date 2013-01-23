package org.uganda.constitution;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Jonathan
 */
public class MessageBox {
    public static void showMessage(String message, JFrame frame, int messageType){
        JOptionPane.showMessageDialog(frame, message, StringConstants.MESSAGE_DIALOG, messageType);
    }

    public static int showConfirmationMessage(String message, JFrame frame, int messageType){
        return JOptionPane.showConfirmDialog(frame, message, StringConstants.MESSAGE_DIALOG, JOptionPane.YES_NO_OPTION, messageType);
    }

}
