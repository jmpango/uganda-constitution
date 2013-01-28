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
import org.uganda.constitution.api.model.Chapter;
import org.uganda.constitution.api.model.Constitution;
import org.uganda.constitution.api.model.ObjectiveGroup;
import org.uganda.constitution.api.model.Schedule;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ChapterService;
import org.uganda.constitution.api.service.ConstitutionService;
import org.uganda.constitution.api.service.ObjectiveGroupService;
import org.uganda.constitution.api.service.ScheduleService;
import org.uganda.constitution.api.springbeans.ApplicationSpringBeans;

/**
 * Displays the objectiveGroup, Chapter and Schedule for the constitution.
 *
 * @author Jonathan
 */
public class ViewObjGroupChapterSchedule extends javax.swing.JFrame {

    private CreateJTable createJTable;
    private ViewObjGroupChapterSchedule addCOCSchedule;
    private UgandaConsititution prvScreen;
    private ObjectiveGroupService objGroupService = null;
    private ConstitutionService constitutionService = null;
    private ScheduleService scheduleService = null;
    private ChapterService chapterService = null;
    private Constitution constitution;
    private int tabbedPaneActiveTab = 0;

    public ViewObjGroupChapterSchedule(String constitutionId, final ConstitutionService constitutionService, int tabbedPaneActiveTab) {
        this.objGroupService = ApplicationSpringBeans.getObjectiveGroupService();
        this.chapterService = ApplicationSpringBeans.getChapterService();
        this.scheduleService = ApplicationSpringBeans.getScheduleService();
        this.constitutionService = constitutionService;
        this.constitution = constitutionService.getConstitution(constitutionId);
        this.tabbedPaneActiveTab = tabbedPaneActiveTab;

        //populate objectiveGroup data
        this.objectiveGroupTable = initializeTableProperty(objectiveGroupTable, StringConstants.OBJ_GROUP_COLUMN_NAMES(), getObjGroupData());
        this.chapterTable = initializeTableProperty(chapterTable, StringConstants.CHAPTER_COLUMN_NAMES(), getChapterData());
        this.scheduleTable = initializeTableProperty(scheduleTable, StringConstants.SCHEDULE_COLUMN_NAMES(), getScheduleData());
        this.addCOCSchedule = this;

        initComponents();

        this.tabbedPane1.setSelectedIndex(tabbedPaneActiveTab);
        this.setExtendedState(MAXIMIZED_BOTH);
        addCOCSchedule = this;
        constitutionNameLabel.setText(constitution.getName());

        objectiveGroupTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (objectiveGroupTable.getRowCount() > 0) {
                    if (e.getClickCount() == 1) {
                        Object id = null;
                        int selectedRow = objectiveGroupTable.getSelectedRow();
                        id = objectiveGroupTable.getModel().getValueAt(selectedRow, 1);

                        ObjectiveGroup selectedObjectiveGroup = objGroupService.getObjectiveGroup((String) id);
                        objGroupTextArea.setText(selectedObjectiveGroup.getTextContent());
                    }else if(e.getClickCount() == 2){
                     Object id = null;
                    int i = objectiveGroupTable.getSelectedRow();
                    id = objectiveGroupTable.getModel().getValueAt(i, 1);

                     ObjectiveGroup DoubleSelectedObjectiveGroup = objGroupService.getObjectiveGroup((String) id);
                     ViewObjective viewObjective = new ViewObjective(DoubleSelectedObjectiveGroup.getId(), objGroupService, constitutionService);
                     addCOCSchedule.dispose();
                     viewObjective.setVisible(true);
                    }
                }
            }
        });

        scheduleTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (scheduleTable.getRowCount() > 0) {
                    if (e.getClickCount() == 1) {
                        Object id = null;
                        int selectedRow = scheduleTable.getSelectedRow();
                        id = scheduleTable.getModel().getValueAt(selectedRow, 1);

                        Schedule selectedSchedule = scheduleService.getSchedule((String) id);
                        scheduleTextArea.setText(selectedSchedule.getTextContent());
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

    private List<List<String>> getObjGroupData() {
        List<List<String>> tableContents = new ArrayList<List<String>>();
        List<ObjectiveGroup> objGroups = constitutionService.getConstitution(constitution.getId()).getObjectiveGroups();
        if (objGroups.size() > 0) {
            for (ObjectiveGroup objGroup : objGroups) {
                List<String> tableContent = new ArrayList<String>();
                tableContent.add(objGroup.getId());
                tableContent.add(objGroup.getObjGroupNumber() + "");
                tableContent.add(objGroup.getName());
                tableContents.add(tableContent);
            }
        }
        return tableContents;
    }

    private List<List<String>> getChapterData() {
        List<List<String>> tableContents = new ArrayList<List<String>>();
        List<Chapter> chapters = constitution.getChapters();
        if (chapters.size() > 0) {
            for (Chapter chapter : chapters) {
                List<String> tableContent = new ArrayList<String>();
                tableContent.add(chapter.getId());
                tableContent.add(chapter.getChapterNumber() + "");
                tableContent.add(chapter.getChapterTheme());
                tableContents.add(tableContent);
            }
        }
        return tableContents;
    }

    private List<List<String>> getScheduleData() {
        List<List<String>> tableContents = new ArrayList<List<String>>();
        List<Schedule> schedules = constitution.getSchedules();
        if (schedules.size() > 0) {
            for (Schedule schedule : schedules) {
                List<String> tableContent = new ArrayList<String>();
                tableContent.add(schedule.getId());
                tableContent.add(schedule.getSchedule_number() + "");
                tableContent.add(schedule.getSchedule_title());
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
        tabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        objGroupScrollPane = new javax.swing.JScrollPane();
        objectiveGroupTable = objectiveGroupTable;
        jScrollPane2 = new javax.swing.JScrollPane();
        objGroupTextArea = new javax.swing.JTextArea();
        addObjGroupBtn = new javax.swing.JButton();
        editObjGroupBtn = new javax.swing.JButton();
        deleteObjGroupBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        chapterScrollPane = new javax.swing.JScrollPane();
        chapterTable = chapterTable;
        addChapterBtn = new javax.swing.JButton();
        editChapterBtn = new javax.swing.JButton();
        deleteChapterBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        scheduleScrollpane = new javax.swing.JScrollPane();
        scheduleTable = scheduleTable;
        jScrollPane3 = new javax.swing.JScrollPane();
        scheduleTextArea = new javax.swing.JTextArea();
        addScheduleBtn = new javax.swing.JButton();
        editSchedule = new javax.swing.JButton();
        deleteSchedule = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        constitutionNameLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();

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

        tabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        tabbedPane1.setName("tabbedPane1"); // NOI18N

        jPanel7.setBackground(new java.awt.Color(170, 202, 223));
        jPanel7.setName("jPanel7"); // NOI18N

        objGroupScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        objGroupScrollPane.setName("objGroupScrollPane"); // NOI18N

        objectiveGroupTable.setToolTipText("");
        objGroupScrollPane.setViewportView(objectiveGroupTable);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setName("jScrollPane2"); // NOI18N

        objGroupTextArea.setColumns(20);
        objGroupTextArea.setRows(5);
        objGroupTextArea.setText("Select an objectiveGroup to view its text content.");
        objGroupTextArea.setName("objGroupTextArea"); // NOI18N
        jScrollPane2.setViewportView(objGroupTextArea);

        addObjGroupBtn.setBackground(new java.awt.Color(255, 255, 255));
        addObjGroupBtn.setForeground(new java.awt.Color(44, 44, 130));
        addObjGroupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/add.png"))); // NOI18N
        addObjGroupBtn.setToolTipText("add");
        addObjGroupBtn.setName("addObjGroupBtn"); // NOI18N
        addObjGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addObjGroupBtnActionPerformed(evt);
            }
        });

        editObjGroupBtn.setBackground(new java.awt.Color(255, 255, 255));
        editObjGroupBtn.setForeground(new java.awt.Color(44, 44, 130));
        editObjGroupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/edit.png"))); // NOI18N
        editObjGroupBtn.setToolTipText("edit"); // NOI18N
        editObjGroupBtn.setName("editObjGroupBtn"); // NOI18N
        editObjGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editObjGroupBtnActionPerformed(evt);
            }
        });

        deleteObjGroupBtn.setBackground(new java.awt.Color(255, 255, 255));
        deleteObjGroupBtn.setForeground(new java.awt.Color(44, 44, 130));
        deleteObjGroupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/delete.png"))); // NOI18N
        deleteObjGroupBtn.setToolTipText("delete"); // NOI18N
        deleteObjGroupBtn.setName("deleteObjGroupBtn"); // NOI18N
        deleteObjGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteObjGroupBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Constantia", 0, 14));
        jLabel4.setText("Objective Group (s)");
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(objGroupScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addObjGroupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editObjGroupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteObjGroupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(246, 246, 246)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(editObjGroupBtn)
                        .addComponent(addObjGroupBtn)
                        .addComponent(deleteObjGroupBtn))
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(objGroupScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabbedPane1.addTab("Objective Group", new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/objective_group_icon.png")), jPanel7, "List if Constitution Objective Group"); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setName("jPanel8"); // NOI18N

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setName("jPanel10"); // NOI18N

        chapterScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        chapterScrollPane.setName("chapterScrollPane"); // NOI18N

        chapterTable.setToolTipText("Constitution chapter(s)");
        chapterTable.setName(""); // NOI18N
        chapterScrollPane.setViewportView(chapterTable);

        addChapterBtn.setBackground(new java.awt.Color(255, 255, 255));
        addChapterBtn.setForeground(new java.awt.Color(44, 44, 130));
        addChapterBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/add.png"))); // NOI18N
        addChapterBtn.setToolTipText("add");
        addChapterBtn.setName("addChapterBtn"); // NOI18N
        addChapterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChapterBtnActionPerformed(evt);
            }
        });

        editChapterBtn.setBackground(new java.awt.Color(255, 255, 255));
        editChapterBtn.setForeground(new java.awt.Color(44, 44, 130));
        editChapterBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/edit.png"))); // NOI18N
        editChapterBtn.setToolTipText("edit"); // NOI18N
        editChapterBtn.setName("editChapterBtn"); // NOI18N
        editChapterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editChapterBtnActionPerformed(evt);
            }
        });

        deleteChapterBtn.setBackground(new java.awt.Color(255, 255, 255));
        deleteChapterBtn.setForeground(new java.awt.Color(44, 44, 130));
        deleteChapterBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/delete.png"))); // NOI18N
        deleteChapterBtn.setToolTipText("delete"); // NOI18N
        deleteChapterBtn.setName("deleteChapterBtn"); // NOI18N
        deleteChapterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteChapterBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Constantia", 0, 14));
        jLabel5.setText("Chapter (s)");
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(addChapterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editChapterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteChapterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(236, 236, 236)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chapterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(editChapterBtn)
                        .addComponent(addChapterBtn)
                        .addComponent(deleteChapterBtn))
                    .addComponent(jLabel5))
                .addGap(19, 19, 19)
                .addComponent(chapterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane1.addTab("Chapter", new javax.swing.ImageIcon("D:\\projects\\uganda-constitution\\uganda-constitution\\src\\main\\resources\\org\\uganda\\constitution\\images\\chapter_icon.png"), jPanel8, "List of Chapters in  a constitution"); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setName("jPanel9"); // NOI18N

        jPanel11.setBackground(new java.awt.Color(122, 167, 164));
        jPanel11.setName("jPanel11"); // NOI18N

        scheduleScrollpane.setBackground(new java.awt.Color(255, 255, 255));
        scheduleScrollpane.setName("scheduleScrollpane"); // NOI18N

        scheduleTable.setToolTipText("");
        scheduleTable.setName("scheduleTable"); // NOI18N
        scheduleScrollpane.setViewportView(scheduleTable);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        scheduleTextArea.setColumns(20);
        scheduleTextArea.setRows(5);
        scheduleTextArea.setText("Select a schedule to view its text content.");
        scheduleTextArea.setName("scheduleTextArea"); // NOI18N
        jScrollPane3.setViewportView(scheduleTextArea);

        addScheduleBtn.setBackground(new java.awt.Color(255, 255, 255));
        addScheduleBtn.setForeground(new java.awt.Color(44, 44, 130));
        addScheduleBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/add.png"))); // NOI18N
        addScheduleBtn.setToolTipText("add");
        addScheduleBtn.setName("addScheduleBtn"); // NOI18N
        addScheduleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addScheduleBtnActionPerformed(evt);
            }
        });

        editSchedule.setBackground(new java.awt.Color(255, 255, 255));
        editSchedule.setForeground(new java.awt.Color(44, 44, 130));
        editSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/edit.png"))); // NOI18N
        editSchedule.setToolTipText("edit"); // NOI18N
        editSchedule.setName("editSchedule"); // NOI18N
        editSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editScheduleActionPerformed(evt);
            }
        });

        deleteSchedule.setBackground(new java.awt.Color(255, 255, 255));
        deleteSchedule.setForeground(new java.awt.Color(44, 44, 130));
        deleteSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/delete.png"))); // NOI18N
        deleteSchedule.setToolTipText("delete"); // NOI18N
        deleteSchedule.setName("deleteSchedule"); // NOI18N
        deleteSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteScheduleActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Constantia", 0, 14));
        jLabel6.setText("Schedule (s)");
        jLabel6.setName("jLabel6"); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(scheduleScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addScheduleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(246, 246, 246)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(editSchedule)
                        .addComponent(addScheduleBtn)
                        .addComponent(deleteSchedule))
                    .addComponent(jLabel6))
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scheduleScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane1.addTab("Schedule", new javax.swing.ImageIcon(getClass().getResource("/org/uganda/constitution/images/schedule_icon.png")), jPanel9, "List of Schedule in  a constitution"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tabbedPane1.getAccessibleContext().setAccessibleName("objectiveTab");

        constitutionNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        constitutionNameLabel.setText(".");
        constitutionNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        constitutionNameLabel.setName("constitutionNameLabel"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Constantia", 2, 12));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("View Chapter, ObjectiveGroup, Schedule");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel10.setName("jLabel10"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(986, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(constitutionNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(775, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(constitutionNameLabel)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(10, 10, 10)
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

    private void addObjGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addObjGroupBtnActionPerformed
        ObjectiveGroup objGroup = new ObjectiveGroup();
        objGroup.setConstitution(constitution);
        AddOrEditObjectGroup addOrEditObjGroup = new AddOrEditObjectGroup(constitution.getId(), objGroup, constitutionService, objGroupService, tabbedPane1.getSelectedIndex());
        this.dispose();
        addOrEditObjGroup.setVisible(true);
    }//GEN-LAST:event_addObjGroupBtnActionPerformed

    private void editObjGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editObjGroupBtnActionPerformed

        boolean noError = ContentValidator.validTableCheckBoxHandler(objectiveGroupTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = objectiveGroupTable.getSelectedRow();
            id = objectiveGroupTable.getModel().getValueAt(i, 1);
            checkBoxObj = objectiveGroupTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                ObjectiveGroup objGroup = objGroupService.getObjectiveGroup((String) id);
                AddOrEditObjectGroup addOrEditObjGroup = new AddOrEditObjectGroup(constitution.getId(), objGroup, constitutionService, objGroupService, tabbedPane1.getSelectedIndex());
                addOrEditObjGroup.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_editObjGroupBtnActionPerformed

    private void deleteObjGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteObjGroupBtnActionPerformed

        boolean noError = ContentValidator.validTableCheckBoxHandler(objectiveGroupTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = objectiveGroupTable.getSelectedRow();
            id = objectiveGroupTable.getModel().getValueAt(i, 1);
            checkBoxObj = objectiveGroupTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                int option = MessageBox.showConfirmationMessage("Your sure you want to delete this Objective Group?", this, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        ObjectiveGroup objG = objGroupService.getObjectiveGroup((String) id);
                        Constitution c = constitutionService.getConstitution(objG.getConstitution().getId());
                        for (ObjectiveGroup o : c.getObjectiveGroups()) {
                            if (o.getId().equals(objG.getId())) {
                                c.removeObjectiveGroup(o);
                                constitutionService.save(constitution);
                                break;
                            }
                        }

                        refreshTableData(objectiveGroupTable, StringConstants.OBJ_GROUP_COLUMN_NAMES(), getObjGroupData(), objGroupScrollPane);
                        MessageBox.showMessage("Objective Group Deleted Sucessfully", this, JOptionPane.INFORMATION_MESSAGE);
                    } catch (ValidationException ex) {
                        MessageBox.showMessage(ex.getMessage(), this, JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
    }//GEN-LAST:event_deleteObjGroupBtnActionPerformed

    private void addChapterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChapterBtnActionPerformed
        Chapter addChapter = new Chapter();
        addChapter.setConstitution(constitution);
        AddOrEditChapter addChapterForm = new AddOrEditChapter(constitution.getId(), addChapter, constitutionService, chapterService, tabbedPane1.getSelectedIndex());
        this.dispose();
        addChapterForm.setVisible(true);

    }//GEN-LAST:event_addChapterBtnActionPerformed

    private void editChapterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editChapterBtnActionPerformed

        boolean noError = ContentValidator.validTableCheckBoxHandler(chapterTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = chapterTable.getSelectedRow();
            id = chapterTable.getModel().getValueAt(i, 1);
            checkBoxObj = chapterTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                Chapter editChapter = chapterService.getChapterById((String) id);
                AddOrEditChapter editChapterForm = new AddOrEditChapter(constitution.getId(), editChapter, constitutionService, chapterService, tabbedPane1.getSelectedIndex());
                editChapterForm.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_editChapterBtnActionPerformed

    private void deleteChapterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteChapterBtnActionPerformed
        boolean noError = ContentValidator.validTableCheckBoxHandler(chapterTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = chapterTable.getSelectedRow();
            id = chapterTable.getModel().getValueAt(i, 1);
            checkBoxObj = chapterTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                int option = MessageBox.showConfirmationMessage("Your sure you want to delete this Chapter?", this, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        Chapter deleteChapter = chapterService.getChapterById((String) id);
                        Constitution parentConstitution = constitutionService.getConstitution(deleteChapter.getConstitution().getId());
                        for (Chapter dChapter : parentConstitution.getChapters()) {
                            if (dChapter.getId().equals(deleteChapter.getId())) {
                                parentConstitution.removeChapter(dChapter);
                                constitutionService.save(constitution);
                                break;
                            }
                        }

                        refreshTableData(chapterTable, StringConstants.CHAPTER_COLUMN_NAMES(), getChapterData(), chapterScrollPane);
                        MessageBox.showMessage("Chapter Deleted Sucessfully", this, JOptionPane.INFORMATION_MESSAGE);
                    } catch (ValidationException ex) {
                        MessageBox.showMessage(ex.getMessage(), this, JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
    }//GEN-LAST:event_deleteChapterBtnActionPerformed

    private void addScheduleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addScheduleBtnActionPerformed
        Schedule addSchedule = new Schedule();
        addSchedule.setConstitution(constitution);
        AddOrEditSchedule addScheduleForm = new AddOrEditSchedule(constitution.getId(), addSchedule, constitutionService, scheduleService, tabbedPane1.getSelectedIndex());
        this.dispose();
        addScheduleForm.setVisible(true);
    }//GEN-LAST:event_addScheduleBtnActionPerformed

    private void editScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editScheduleActionPerformed
        boolean noError = ContentValidator.validTableCheckBoxHandler(scheduleTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = scheduleTable.getSelectedRow();
            id = scheduleTable.getModel().getValueAt(i, 1);
            checkBoxObj = scheduleTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                Schedule editSchedule1 = scheduleService.getSchedule((String) id);
                AddOrEditSchedule ediScheduleForm = new AddOrEditSchedule(constitution.getId(), editSchedule1, constitutionService, scheduleService, tabbedPane1.getSelectedIndex());
                ediScheduleForm.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_editScheduleActionPerformed

    private void deleteScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteScheduleActionPerformed

         boolean noError = ContentValidator.validTableCheckBoxHandler(scheduleTable, this);
        if (noError) {
            Object id = null, checkBoxObj = null;
            int i = scheduleTable.getSelectedRow();
            id = scheduleTable.getModel().getValueAt(i, 1);
            checkBoxObj = scheduleTable.getModel().getValueAt(i, 0);
            boolean isCheckBoxChecked = new Boolean((Boolean) checkBoxObj);

            if (isCheckBoxChecked) {
                int option = MessageBox.showConfirmationMessage("Your sure you want to delete this Schedule?", this, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        Schedule deleteSchedule1 = scheduleService.getSchedule((String) id);
                        Constitution parentConstitution = constitutionService.getConstitution(deleteSchedule1.getConstitution().getId());
                        for (Schedule dSchedule : parentConstitution.getSchedules()) {
                            if (dSchedule.getId().equals(deleteSchedule1.getId())) {
                                parentConstitution.removeSchedule(dSchedule);
                                constitutionService.save(constitution);
                                break;
                            }
                        }

                        refreshTableData(scheduleTable, StringConstants.SCHEDULE_COLUMN_NAMES(), getScheduleData(), scheduleScrollpane);
                        MessageBox.showMessage("Schedule Deleted Sucessfully", this, JOptionPane.INFORMATION_MESSAGE);
                    } catch (ValidationException ex) {
                        MessageBox.showMessage(ex.getMessage(), this, JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
    }//GEN-LAST:event_deleteScheduleActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addChapterBtn;
    private javax.swing.JButton addObjGroupBtn;
    private javax.swing.JButton addScheduleBtn;
    private javax.swing.JScrollPane chapterScrollPane;
    private javax.swing.JTable chapterTable;
    private javax.swing.JLabel constitutionNameLabel;
    private javax.swing.JButton deleteChapterBtn;
    private javax.swing.JButton deleteObjGroupBtn;
    private javax.swing.JButton deleteSchedule;
    private javax.swing.JButton editChapterBtn;
    private javax.swing.JButton editObjGroupBtn;
    private javax.swing.JButton editSchedule;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane objGroupScrollPane;
    private javax.swing.JTextArea objGroupTextArea;
    private javax.swing.JTable objectiveGroupTable;
    private javax.swing.JScrollPane scheduleScrollpane;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JTextArea scheduleTextArea;
    private javax.swing.JTabbedPane tabbedPane1;
    // End of variables declaration//GEN-END:variables

    private void getPreviousScreen() {
        this.dispose();
        prvScreen = new UgandaConsititution();
        prvScreen.setVisible(true);
    }
}
