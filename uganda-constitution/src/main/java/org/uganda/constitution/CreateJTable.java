package org.uganda.constitution;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Jonathan
 */
public class CreateJTable {

    private DefaultTableModel _tableModel;
    private JTable table;
    private JCheckBox _checkBox;
    private JFrame parentFrame;
    private List<String> columnNamez;
    private List<List<String>> data;
    int numberOfRows = 0;

    public CreateJTable() {
    }

    /**
     *
     * @param numberOfRows - Number of table rows
     * @param columnNamez  - Table column names
     * @param data         - Table data
     * @param parentFrame  - The parent JFrame containing the table;
     */
    public CreateJTable(List<String> columnNamez, List<List<String>> data, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.columnNamez = columnNamez;
        this.data = data;
    }

    public JTable getCreatedTable() {
        _tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return true;
                }
                return false;
            }
        };
        _tableModel.setDataVector(convertDataToObjectData(data), convertListToObjectHeader(columnNamez));

        CheckBoxRenderer _checkBoxRenderer = new CheckBoxRenderer();
        EachRowRenderer rowRenderer = new EachRowRenderer();

        _checkBox = new JCheckBox();
        _checkBox.setHorizontalAlignment(JLabel.CENTER);

        DefaultCellEditor checkBoxEditor = new DefaultCellEditor(_checkBox);
        table = new JTable(_tableModel);

        EachRowEditor rowEditor = new EachRowEditor(table);

        for(int noOFCheckBox = 0; noOFCheckBox < data.size(); noOFCheckBox++){
            rowRenderer.add(noOFCheckBox, _checkBoxRenderer);
            rowEditor.setEditorAt(noOFCheckBox, checkBoxEditor);
        }

        table.getColumn("").setCellRenderer(rowRenderer);
        table.getColumn("").setCellEditor(rowEditor);
        table.removeColumn(table.getColumnModel().getColumn(1));


        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                int numberOfTableRows = table.getRowCount();

                int _checkedBoxCounter = 0;
                for (int num = 0; num < numberOfTableRows; num++) {
                    boolean isChecked = new Boolean((Boolean) table.getModel().getValueAt(num, 0));
                    if (isChecked) {
                        _checkedBoxCounter++;
                    }
                }

                Object obj = null, checkBoxObj = null;
                obj = table.getModel().getValueAt(i, 1);
                checkBoxObj = table.getModel().getValueAt(i, 0);

                boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

                if (isCheckBoxChecked) {
                    if (_checkedBoxCounter > 1) {
                        MessageBox.showMessage("Select only one checkbox at a time.", parentFrame, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        System.out.println(">> ID Column: " + obj);
                    }
                }
            }
        });

        table.setRowHeight(30);
       table.getColumnModel().getColumn(0).setWidth(5);
        ///table.getColumn("").setWidth(5);
        return table;
    }

    private Object[][] convertDataToObjectData(List<List<String>> listData) {
        numberOfRows = listData.get(0).size();
        Object[][] dataObject = new Object[listData.size()][numberOfRows + 1];
        int contentIncrement = 1;

        for (int k = 0; k < listData.size(); k++) {
            dataObject[k][0] = new Boolean(false);
            List<String> contentData = listData.get(k);
            for (int z = 0; z < contentData.size(); z++) {
                dataObject[k][contentIncrement] = contentData.get(z);
                contentIncrement++;
            }
            contentIncrement = 1;
        }

        return dataObject;
    }

    private Object[] convertListToObjectHeader(List<String> listHeader) {
        Object[] columnHeaders = new Object[listHeader.size() + 1];
        int columnIncrement = 0;
        columnHeaders[columnIncrement] = "";
        columnIncrement = columnIncrement + 1;
        for (int s = 0; s < listHeader.size(); s++) {
            columnHeaders[columnIncrement] = listHeader.get(s);
            columnIncrement++;
        }
        return columnHeaders;
    }

    /**
     * Responsible on how the checkBox is formated.
     */
    private class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

        public CheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }

            setSelected((value != null && ((Boolean) value).booleanValue()));
            return this;
        }
    }

    /**
     * Responsible for how each table row is rendered or displayed.
     */
    private class EachRowRenderer implements TableCellRenderer {

        protected Hashtable renderers;
        protected TableCellRenderer renderer, defaultRenderer;

        public EachRowRenderer() {
            renderers = new Hashtable();
            defaultRenderer = new DefaultTableCellRenderer();
        }

        public void add(int row, TableCellRenderer renderer) {
            renderers.put(new Integer(row), renderer);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            renderer = (TableCellRenderer) renderers.get(new Integer(row));
            if (renderer == null) {
                renderer = defaultRenderer;
            }
            return renderer.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
        }
    }

    /**
     * Responsible for formating each table row.
     */
    private class EachRowEditor implements TableCellEditor {

        protected Hashtable editors;
        protected TableCellEditor editor, defaultEditor;
        JTable table;

        public EachRowEditor(JTable table) {
            this.table = table;
            editors = new Hashtable();
            defaultEditor = new DefaultCellEditor(new JTextField());
        }

        public void setEditorAt(int row, TableCellEditor editor) {
            editors.put(new Integer(row), editor);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            return editor.getTableCellEditorComponent(table, value, isSelected,
                    row, column);
        }

        public Object getCellEditorValue() {
            return editor.getCellEditorValue();
        }

        public boolean stopCellEditing() {
            return editor.stopCellEditing();
        }

        public void cancelCellEditing() {
            editor.cancelCellEditing();
        }

        public boolean isCellEditable(EventObject anEvent) {
            selectEditor((MouseEvent) anEvent);
            return editor.isCellEditable(anEvent);
        }

        public void addCellEditorListener(CellEditorListener l) {
            editor.addCellEditorListener(l);
        }

        public void removeCellEditorListener(CellEditorListener l) {
            editor.removeCellEditorListener(l);
        }

        public boolean shouldSelectCell(EventObject anEvent) {
            selectEditor((MouseEvent) anEvent);
            return editor.shouldSelectCell(anEvent);
        }

        protected void selectEditor(MouseEvent e) {
            int row;
            if (e == null) {
                row = table.getSelectionModel().getAnchorSelectionIndex();
            } else {
                row = table.rowAtPoint(e.getPoint());
            }
            editor = (TableCellEditor) editors.get(new Integer(row));
            if (editor == null) {
                editor = defaultEditor;
            }
        }
    }
}
