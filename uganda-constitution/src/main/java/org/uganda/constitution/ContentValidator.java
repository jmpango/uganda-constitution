package org.uganda.constitution;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * @author Jonathan
 */
public class ContentValidator {

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validTableCheckBoxHandler(JTable table, JFrame frame) {
        boolean isCheckBoxChecked = true;
        int tableRows = table.getRowCount();
        int numberOfTableRows = table.getRowCount();
        int _checkedBoxCounter = 0;

        if (tableRows <= 0) {
            MessageBox.showMessage("Empty table. No rows", frame, JOptionPane.INFORMATION_MESSAGE);
            isCheckBoxChecked = false;
        } else {
            for (int num = 0; num < numberOfTableRows; num++) {
                boolean isChecked = new Boolean((Boolean) table.getModel().getValueAt(num, 0));
                if (isChecked) {
                    _checkedBoxCounter++;
                }
            }
            if (_checkedBoxCounter > 1) {
                MessageBox.showMessage("Select only one checkbox at a time.", frame, JOptionPane.INFORMATION_MESSAGE);
                isCheckBoxChecked = false;
            }
        }
        return isCheckBoxChecked;
    }

    public static JTable emptyTable(){
        return new JTable();
    }

}
