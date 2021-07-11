/*
 * The MIT License
 *
 * Copyright 2021 Nelson J. Terrazas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.idelogix.login.ui;

import com.idelogix.login.service.Globals;
import com.idelogix.login.service.ResourceService;
import com.idelogix.login.service.Utils;
import com.idelogix.login.service.TypeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nelson Terrazas
 */
public class TypesUI extends javax.swing.JInternalFrame {

    public TypesUI() {
        initComponents(); //Auto-generated by NetBeans
        myInitComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        pTable = new javax.swing.JScrollPane();
        tblMain = new javax.swing.JTable();
        lFilter = new javax.swing.JLabel();
        tSearch = new javax.swing.JTextField();
        bReload = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        bDelete = new javax.swing.JButton();
        bCreate = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        recordTabs = new javax.swing.JTabbedPane();
        tabGeneralInfo = new javax.swing.JPanel();
        lEnabled = new javax.swing.JLabel();
        cbEnabled = new javax.swing.JCheckBox();
        lDateCreated = new javax.swing.JLabel();
        tDateCreated = new javax.swing.JTextField();
        lComments = new javax.swing.JLabel();
        tComments = new javax.swing.JTextField();
        lName = new javax.swing.JLabel();
        tName = new javax.swing.JTextField();
        tId = new javax.swing.JTextField();
        lUserId = new javax.swing.JLabel();
        tabResources = new javax.swing.JPanel();
        jScrollPaneLeft = new javax.swing.JScrollPane();
        listResources = new javax.swing.JList<>();
        lAvailableRoles = new javax.swing.JLabel();
        pSaveData = new javax.swing.JPanel();
        bCancel = new javax.swing.JButton();
        bSave = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("_Types_Maintenance");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 500));
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        tblMain.setModel(typeService.getTableModel(null));
        pTable.setViewportView(tblMain);

        lFilter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lFilter.setText("_Filter");
        lFilter.setToolTipText("");

        tSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tSearchMouseClicked(evt);
            }
        });

        bReload.setText("_Reload");
        bReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bReload))
                    .addComponent(pTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bReload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pTable)
                .addGap(7, 7, 7))
        );

        getContentPane().add(leftPanel);

        rightPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        bDelete.setText("_Delete");
        bDelete.setEnabled(false);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bCreate.setText("_Create");
        bCreate.setEnabled(false);
        bCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateActionPerformed(evt);
            }
        });

        bEdit.setText("_Edit");
        bEdit.setEnabled(false);
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        tabGeneralInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lEnabled.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lEnabled.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lEnabled.setText("_Enabled");

        cbEnabled.setEnabled(false);

        lDateCreated.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lDateCreated.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lDateCreated.setText("_Date_Created");

        tDateCreated.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tDateCreated.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tDateCreated.setEnabled(false);

        lComments.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lComments.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lComments.setText("_Comments");

        tComments.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tComments.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        tComments.setEnabled(false);
        tComments.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tCommentsKeyPressed(evt);
            }
        });

        lName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lName.setText("_Name");

        tName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tName.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tName.setEnabled(false);
        tName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNameKeyPressed(evt);
            }
        });

        tId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tId.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tId.setEnabled(false);

        lUserId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lUserId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lUserId.setText("_Id");

        javax.swing.GroupLayout tabGeneralInfoLayout = new javax.swing.GroupLayout(tabGeneralInfo);
        tabGeneralInfo.setLayout(tabGeneralInfoLayout);
        tabGeneralInfoLayout.setHorizontalGroup(
            tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGeneralInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabGeneralInfoLayout.createSequentialGroup()
                        .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lComments, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tName)
                            .addComponent(tId)
                            .addComponent(tComments)))
                    .addGroup(tabGeneralInfoLayout.createSequentialGroup()
                        .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lEnabled, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEnabled))
                        .addGap(0, 159, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabGeneralInfoLayout.setVerticalGroup(
            tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabGeneralInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabGeneralInfoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lName))
                .addGap(12, 12, 12)
                .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tComments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lComments))
                .addGap(18, 18, 18)
                .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDateCreated))
                .addGap(6, 6, 6)
                .addGroup(tabGeneralInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lEnabled)
                    .addComponent(cbEnabled))
                .addGap(143, 143, 143))
        );

        lName.getAccessibleContext().setAccessibleDescription("");

        recordTabs.addTab("_Type_information", tabGeneralInfo);

        listResources.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "User 1", "User 2", "User 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listResources.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listResources.setToolTipText("");
        listResources.setEnabled(false);
        jScrollPaneLeft.setViewportView(listResources);

        lAvailableRoles.setText("_Resources_with_this_type");

        javax.swing.GroupLayout tabResourcesLayout = new javax.swing.GroupLayout(tabResources);
        tabResources.setLayout(tabResourcesLayout);
        tabResourcesLayout.setHorizontalGroup(
            tabResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lAvailableRoles))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        tabResourcesLayout.setVerticalGroup(
            tabResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lAvailableRoles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        recordTabs.addTab("_Type_resources", tabResources);

        pSaveData.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));

        bCancel.setText("_Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        bSave.setText("_Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pSaveDataLayout = new javax.swing.GroupLayout(pSaveData);
        pSaveData.setLayout(pSaveDataLayout);
        pSaveDataLayout.setHorizontalGroup(
            pSaveDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSaveDataLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(bSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCancel)
                .addContainerGap())
        );
        pSaveDataLayout.setVerticalGroup(
            pSaveDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSaveDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pSaveDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancel)
                    .addComponent(bSave))
                .addContainerGap())
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recordTabs)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                        .addComponent(bDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bEdit))
                    .addComponent(pSaveData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addComponent(recordTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pSaveData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEdit)
                    .addComponent(bCreate)
                    .addComponent(bDelete))
                .addContainerGap())
        );

        recordTabs.getAccessibleContext().setAccessibleName("");

        getContentPane().add(rightPanel);

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateActionPerformed
        clearValues();
        editRecord();
    }//GEN-LAST:event_bCreateActionPerformed

    private void tSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tSearchMouseClicked
        if (tSearch.getText().equals(UIUtils.getLocalText("msg._Enter_Text")))
            tSearch.setText("");
    }//GEN-LAST:event_tSearchMouseClicked

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        if (Integer.parseInt(tId.getText()) != 0)
            editRecord();
    }//GEN-LAST:event_bEditActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        deleteRecord();
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        setAllControlsEnabled(true);
        clearValues();
        setEditableFieldsEnabled(false);
        pSaveData.setVisible(false);
        if (tblMain.getSelectedRow() >= 0)
            updateValuesFromTable(tblMain.getSelectedRow());
    }//GEN-LAST:event_bCancelActionPerformed

    private void bReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReloadActionPerformed
        reloadTable();
        clearValues();
    }//GEN-LAST:event_bReloadActionPerformed

    private void tNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNameKeyPressed
        if (tName.getText().length() >= MAX_TYPE_NAME) {
            JOptionPane.showMessageDialog(this, UIUtils.getLocalText("msg._Max._length_exceeded!") + "  " + MAX_TYPE_NAME);
            tName.setText(tName.getText().substring(0, MAX_TYPE_NAME - 1));
        }
    }//GEN-LAST:event_tNameKeyPressed

    private void tCommentsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCommentsKeyPressed
        if (tComments.getText().length() >= MAX_TYPE_COMMENTS) {
            JOptionPane.showMessageDialog(this, UIUtils.getLocalText("msg._Max._length_exceeded!") + "  " + MAX_TYPE_COMMENTS);
            tComments.setText(tComments.getText().substring(0, MAX_TYPE_COMMENTS - 1));
        }
    }//GEN-LAST:event_tCommentsKeyPressed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        if (Integer.parseInt(tId.getText()) == 0) {
            createRecord();
        } else {
            saveRecord();
        }
        setAllControlsEnabled(true);
        setEditableFieldsEnabled(false);
        pSaveData.setVisible(false);
        JOptionPane.showMessageDialog(this, UIUtils.getLocalText("msg._Operation_successful!"));
    }//GEN-LAST:event_bSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bCreate;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bReload;
    private javax.swing.JButton bSave;
    private javax.swing.JCheckBox cbEnabled;
    private javax.swing.JScrollPane jScrollPaneLeft;
    private javax.swing.JLabel lAvailableRoles;
    private javax.swing.JLabel lComments;
    private javax.swing.JLabel lDateCreated;
    private javax.swing.JLabel lEnabled;
    private javax.swing.JLabel lFilter;
    private javax.swing.JLabel lName;
    private javax.swing.JLabel lUserId;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JList<String> listResources;
    private javax.swing.JPanel pSaveData;
    private javax.swing.JScrollPane pTable;
    private javax.swing.JTabbedPane recordTabs;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JTextField tComments;
    private javax.swing.JTextField tDateCreated;
    private javax.swing.JTextField tId;
    private javax.swing.JTextField tName;
    private javax.swing.JTextField tSearch;
    private javax.swing.JPanel tabGeneralInfo;
    private javax.swing.JPanel tabResources;
    private javax.swing.JTable tblMain;
    // End of variables declaration//GEN-END:variables

    private final TypeService typeService = TypeService.getInstance();

    private final int MAX_TYPE_NAME = typeService.getStringFieldMaxSize("type", "name");
    private final int MAX_TYPE_COMMENTS = typeService.getStringFieldMaxSize("type", "comments");
   
    private boolean enabledEdit;
    private boolean enabledCreate;
    private boolean enabledDelete;

    private void myInitComponents() {
        UIUtils.enableActionButtons(this);
        enabledEdit = bEdit.isEnabled();
        enabledCreate = bCreate.isEnabled();
        enabledDelete = bDelete.isEnabled();
        pSaveData.setVisible(false);
        tSearch.setText(UIUtils.getLocalText("msg._Enter_Text"));
        clearValues();
        setTableConfiguration();
        setAllControlsEnabled(true);
    }

    private void clearValues() {
        tId.setText("0");
        tName.setText("");
        SimpleDateFormat formatter = new SimpleDateFormat(Globals.DATE_FORMAT);
        tDateCreated.setText(formatter.format(new Date()));
        tComments.setText("");
        cbEnabled.setSelected(false);
        listResources.setListData(new String[0]);
    }

    private void setTableConfiguration() {
        tblMain.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);        
        //Avoid editing
        tblMain.setDefaultEditor(Object.class, null);
        //Show only first 3 rows (0,1,2), hide the rest
        UIUtils.hideTableColumns(tblMain, new int[]{3, 4});

        tblMain.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && tblMain.getSelectedRow() >= 0) {
                    updateValuesFromTable(tblMain.getSelectedRow());
                }
            }
        });
    }

    private void editRecord() {
        setAllControlsEnabled(false);
        setEditableFieldsEnabled(true);
        recordTabs.setSelectedIndex(0);
        pSaveData.setVisible(true);
    }

    private void createRecord() {
        boolean success = TypeService.getInstance().addType(tName.getText(), tComments.getText(), cbEnabled.isSelected());
        if (success) {
            tId.setText("" + TypeService.getInstance().getType(tName.getText()).getId());
            tSearch.setText("");
            reloadTable();
        } else {
            JOptionPane.showMessageDialog(this, UIUtils.getLocalText("msg._Operation_was_not_successful!"));
        }
    }

    private void reloadTable() {
        String sText = tSearch.getText();
        if (tSearch.getText().equals(UIUtils.getLocalText("msg._Enter_Text"))) {
            sText = "";
        }
        tblMain.setModel(typeService.getTableModel(sText));
        setTableConfiguration();
        tblMain.repaint();
    }

    private void updateTableRow(int row, String name, String comments, boolean enabled) {
        tblMain.getModel().setValueAt(name, row, 1);
        tblMain.getModel().setValueAt(comments, row, 2);
        tblMain.getModel().setValueAt(enabled, row, 3);
    }

    private void saveRecord() {
        typeService.updateType(Integer.parseInt(tId.getText()), tName.getText(), tComments.getText(), cbEnabled.isSelected());
        updateTableRow(tblMain.getSelectedRow(), tName.getText(), tComments.getText(), cbEnabled.isSelected());
    }

    private void deleteRecord() {
        int opc = Utils.confirmDialog(this, UIUtils.getLocalText("msg._Continue_deleteing_record?"));
        if (opc == 0) {
            if (typeService.deleteType(Integer.parseInt(tId.getText()))) {
                JOptionPane.showMessageDialog(this, UIUtils.getLocalText("msg._Operation_successful!"));
                DefaultTableModel dtm = (DefaultTableModel) tblMain.getModel();
                dtm.removeRow(tblMain.getSelectedRow());
                clearValues();
            } else {
                JOptionPane.showMessageDialog(this, UIUtils.getLocalText("msg._Operation_was_not_successful"));
            }
        }
    }

    private void updateValuesFromTable(int selectedRowIndex) {
        tId.setText(String.valueOf(tblMain.getValueAt(selectedRowIndex, 0)));
        tName.setText((String) tblMain.getValueAt(selectedRowIndex, 1));
        tComments.setText((String) tblMain.getValueAt(selectedRowIndex, 2));
        cbEnabled.setSelected((Boolean) tblMain.getValueAt(selectedRowIndex, 3));
        Date d = null;
        try {
            //Get original date from TablemModel with yyyy-MM-dd format
            d = new SimpleDateFormat("yyyy-MM-dd").parse("" + tblMain.getValueAt(selectedRowIndex, 4));
        } catch (ParseException ex) {
            Logger.getLogger(TypesUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Convert to app's default date format and display
        SimpleDateFormat DateFor = new SimpleDateFormat(Globals.DATE_FORMAT);
        String stringDate = DateFor.format(d);
        tDateCreated.setText(stringDate);
        //Get array of users names with that role and update list in corresponding tab
        listResources.setListData(ResourceService.getInstance().getResourceNamesByType(Integer.parseInt(tId.getText())));
    }

    private void setEditableFieldsEnabled(boolean sw) {
        tName.setEnabled(sw);
        tComments.setEnabled(sw);
        cbEnabled.setEnabled(sw);
        tName.requestFocus();
    }

    private void setAllControlsEnabled(boolean sw) {
        tSearch.setEnabled(sw);
        lFilter.setEnabled(sw);
        bReload.setEnabled(sw);
        tblMain.setEnabled(sw);
        if (!sw) {
            bDelete.setEnabled(sw);
            bCreate.setEnabled(sw);
            bEdit.setEnabled(sw);
        } else {
            bDelete.setEnabled(enabledDelete);
            bCreate.setEnabled(enabledCreate);
            bEdit.setEnabled(enabledEdit);
        }
    }

}
