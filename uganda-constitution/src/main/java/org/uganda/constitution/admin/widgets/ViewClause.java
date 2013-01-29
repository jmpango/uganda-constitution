package org.uganda.constitution.admin.widgets;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.uganda.constitution.ContentValidator;
import org.uganda.constitution.CreateJTable;
import org.uganda.constitution.MessageBox;
import org.uganda.constitution.StringConstants;
import org.uganda.constitution.api.model.Clause;
import org.uganda.constitution.api.model.Objective;
import org.uganda.constitution.api.model.ObjectiveGroup;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ClauseService;
import org.uganda.constitution.api.service.ConstitutionService;
import org.uganda.constitution.api.service.ObjectiveGroupService;
import org.uganda.constitution.api.service.ObjectiveService;
import org.uganda.constitution.api.springbeans.ApplicationSpringBeans;

/**
 * Displays the objectiveGroup, Chapter and Schedule for the constitution.
 *
 * @author Jonathan
 */
public class ViewClause extends javax.swing.JFrame {

    private CreateJTable createJTable;
    private ObjectiveService objectiveService = null;
    private ConstitutionService constitutionService = null;
    private ObjectiveGroupService objGroupService = null;
    private Objective objective;
    private ClauseService clauseService = null;

    public ViewClause(String parentId, ObjectiveService objectiveService, ConstitutionService constitutionService, ObjectiveGroupService objGroupService) {
        this.constitutionService = constitutionService;
        this.objectiveService = objectiveService;
        this.objGroupService = objGroupService;
        this.objective = objectiveService.getObjective(parentId);
        this.clauseService = ApplicationSpringBeans.getClauseService();

        this.clauseTable = initializeTableProperty(clauseTable, StringConstants.CLAUSE_COLUMN_NAMES(), getClauseData());
        initComponents();

        constitutionNameLabel.setText(objective.getObjectiveGroup().getConstitution().getName());
        objectiveGroupLabel.setText(objective.getObjectiveGroup().getName());
        objectiveLabel.setText(objective.getObjectiveNumber());
        
         clauseTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (clauseTable.getRowCount() > 0) {
                    if (e.getClickCount() == 1) {
                        Object id = null;
                        int selectedRow = clauseTable.getSelectedRow();
                        id = clauseTable.getModel().getValueAt(selectedRow, 1);

                        Clause selectedClause = clauseService.getClauseById((String) id);
                        clauseTextArea.setText(selectedClause.getTextContent());
                    }else if(e.getClickCount() == 2){
//                     Object id = null;
//                    int i = objectiveGroupTable.getSelectedRow();
//                    id = objectiveGroupTable.getModel().getValueAt(i, 1);
//
//                     ObjectiveGroup DoubleSelectedObjectiveGroup = objGroupService.getObjectiveGroup((String) id);
//                     ViewObjective viewObjective = new ViewObjective(DoubleSelectedObjectiveGroup.getId(), objGroupService, constitutionService);
//                     addCOCSchedule.dispose();
//                     viewObjective.setVisible(true);
                    }
                }
            }
        });
    }

    private void refreshTableData(JTable table, List<String> columnNames, List<List<String>> dataObjects, JScrollPane scrollPane) {
        table = initializeTableProperty(table, columnNames, dataObjects);
        scrollPane.setViewportView(table);
    }

    private JTable initializeTableProperty(JTable table, List<String> columnNames, List<List<String>> tableContents) {
        if (tableContents.size() == 0) {
            table = ContentValidator.emptyTable();
        } else {
            createJTable = new CreateJTable(columnNames, tableContents, this);
            table = createJTable.getCreatedTable();
        }
        return table;

    }

    private List<List<String>> getClauseData() {
        List<List<String>> tableContents = new ArrayList<List<String>>();
        List<Clause> clauses = objectiveService.getObjective(objective.getId()).getClauses();

        if (clauses.size() > 0) {
            for (Clause clause : clauses) {
                List<String> tableContent = new ArrayList<String>();
                tableContent.add(clause.getId());
                tableContent.add(clause.getClauseNumber() + "");
                tableContents.add(tableContent);
            }
        }
        return tableContents;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        clauseScrollPane = new javax.swing.JScrollPane();
        clauseTable = clauseTable;
        clauseTextArea = new javax.swing.JTextArea();
        addClauseBtn = new javax.swing.JButton();
        editClauseBtn = new javax.swing.JButton();
        deleteClauseBtn = new javax.swing.JButton();
        constitutionNameLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        objectiveGroupLabel = new javax.swing.JLabel();
        objectiveLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/court_of_arm.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Constantia", 0, 24));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("The Laws of Uganda");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Constantia", 0, 14));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("[ Admin ] ");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(480, 480, 480))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(429, 429, 429)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(553, 553, 553))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(573, 573, 573))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setName("jPanel4"); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jButton1.setForeground(new java.awt.Color(44, 44, 130));
        jButton1.setText("Back");
        jButton1.setToolTipText("Navigate Back");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setName("jPanel6"); // NOI18N

        clauseScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        clauseScrollPane.setName("clauseScrollPane"); // NOI18N

        clauseTable.setToolTipText("");
        clauseTable.setName("clauseTable"); // NOI18N
        clauseScrollPane.setViewportView(clauseTable);

        clauseTextArea.setColumns(20);
        clauseTextArea.setRows(5);
        clauseTextArea.setText("Select an CLAUSE to view its text content.");
        clauseTextArea.setName("clauseTextArea"); // NOI18N

        addClauseBtn.setBackground(new java.awt.Color(255, 255, 255));
        addClauseBtn.setForeground(new java.awt.Color(44, 44, 130));
        addClauseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/add.png"))); // NOI18N
        addClauseBtn.setToolTipText("add");
        addClauseBtn.setName("addClauseBtn"); // NOI18N
        addClauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClauseBtnActionPerformed(evt);
            }
        });

        editClauseBtn.setBackground(new java.awt.Color(255, 255, 255));
        editClauseBtn.setForeground(new java.awt.Color(44, 44, 130));
        editClauseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/edit.png"))); // NOI18N
        editClauseBtn.setToolTipText("edit"); // NOI18N
        editClauseBtn.setName("editClauseBtn"); // NOI18N
        editClauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editClauseBtnActionPerformed(evt);
            }
        });

        deleteClauseBtn.setBackground(new java.awt.Color(255, 255, 255));
        deleteClauseBtn.setForeground(new java.awt.Color(44, 44, 130));
        deleteClauseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/delete.png"))); // NOI18N
        deleteClauseBtn.setToolTipText("delete"); // NOI18N
        deleteClauseBtn.setName("deleteClauseBtn"); // NOI18N
        deleteClauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteClauseBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(clauseScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clauseTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addClauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editClauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteClauseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(962, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addClauseBtn)
                    .addComponent(editClauseBtn)
                    .addComponent(deleteClauseBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clauseTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(clauseScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)))
        );

        constitutionNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        constitutionNameLabel.setText(".");
        constitutionNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        constitutionNameLabel.setName("constitutionNameLabel"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Constantia", 2, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("View Clause");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel10.setName("jLabel10"); // NOI18N

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setName("jSeparator2"); // NOI18N

        objectiveGroupLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        objectiveGroupLabel.setText(".");
        objectiveGroupLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        objectiveGroupLabel.setName("objectiveGroupLabel"); // NOI18N

        objectiveLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        objectiveLabel.setText(".");
        objectiveLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        objectiveLabel.setName("objectiveLabel"); // NOI18N

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setName("jSeparator3"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(986, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(constitutionNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(objectiveGroupLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(objectiveLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(735, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(constitutionNameLabel)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(objectiveGroupLabel)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(objectiveLabel))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getPreviousScreen();
}//GEN-LAST:event_jButton1ActionPerformed

    private void addClauseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClauseBtnActionPerformed
        Clause addClause = new Clause();
        addClause.setObjective(objective);
        AddOrEditClause addOrEditClause = new AddOrEditClause(addClause, objGroupService, constitutionService, objectiveService, clauseService);
        this.dispose();
        addOrEditClause.setVisible(true);
}//GEN-LAST:event_addClauseBtnActionPerformed

    private void editClauseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editClauseBtnActionPerformed

        boolean noError = ContentValidator.validTableCheckBoxHandler(clauseTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = clauseTable.getSelectedRow();
            id = clauseTable.getModel().getValueAt(i, 1);
            checkBoxObj = clauseTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                Clause editClause = clauseService.getClauseById((String) id);
                AddOrEditClause addOrEditClause = new AddOrEditClause(editClause, objGroupService, constitutionService, objectiveService, clauseService);
                addOrEditClause.setVisible(true);
                this.dispose();
            }
        }
}//GEN-LAST:event_editClauseBtnActionPerformed

    private void deleteClauseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteClauseBtnActionPerformed

        boolean noError = ContentValidator.validTableCheckBoxHandler(clauseTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = clauseTable.getSelectedRow();
            id = clauseTable.getModel().getValueAt(i, 1);
            checkBoxObj = clauseTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                int option = MessageBox.showConfirmationMessage("Your sure you want to delete this Clause ?", this, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        Clause deleteClause = clauseService.getClauseById((String) id);
                        Objective objectivez = objectiveService.getObjective(deleteClause.getObjective().getId());
                        for (Clause clauz : objectivez.getClauses()) {
                            if (clauz.getId().equals(deleteClause.getId())) {
                                objectivez.removeClause(clauz);
                                objectiveService.save(objectivez);
                                break;
                            }
                        }

                        refreshTableData(clauseTable, StringConstants.CLAUSE_COLUMN_NAMES(), getClauseData(), clauseScrollPane);
                        MessageBox.showMessage("Clause Deleted Sucessfully", this, JOptionPane.INFORMATION_MESSAGE);
                    } catch (ValidationException ex) {
                        MessageBox.showMessage(ex.getMessage(), this, JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
}//GEN-LAST:event_deleteClauseBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClauseBtn;
    private javax.swing.JScrollPane clauseScrollPane;
    private javax.swing.JTable clauseTable;
    private javax.swing.JTextArea clauseTextArea;
    private javax.swing.JLabel constitutionNameLabel;
    private javax.swing.JButton deleteClauseBtn;
    private javax.swing.JButton editClauseBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel objectiveGroupLabel;
    private javax.swing.JLabel objectiveLabel;
    // End of variables declaration//GEN-END:variables

    private void getPreviousScreen() {
        this.dispose();
        ViewObjective viewObjective = new ViewObjective(objective.getObjectiveGroup().getId(), objGroupService, constitutionService);
        viewObjective.setVisible(true);
    }
}
