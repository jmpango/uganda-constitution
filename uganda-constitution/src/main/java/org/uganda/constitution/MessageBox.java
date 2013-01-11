package org.uganda.constitution;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Jonathan
 */
public class MessageBox {
    public static void MessageBox(String message, JFrame frame, int messageType){
        JOptionPane.showMessageDialog(frame, message, StringConstants.MESSAGE_DIALOG, messageType);
    }

}
