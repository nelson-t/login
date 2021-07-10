/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.ui;

import java.awt.event.WindowEvent;
import java.util.Arrays;
import javax.swing.JInternalFrame;

/**
 *
 * @author Administrator
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public MainUI() {
        initComponents();
        myInitComponents();
    }

    /**
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor. This method is called from within the
     * constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pInfo = new javax.swing.JPanel();
        lInfo = new javax.swing.JLabel();
        mainPanel = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        javax.swing.JMenuItem mExit = new javax.swing.JMenuItem();
        mAdmin = new javax.swing.JMenu();
        mUsers = new javax.swing.JMenuItem();
        mRoles = new javax.swing.JMenuItem();
        mActions = new javax.swing.JMenuItem();
        mResources = new javax.swing.JMenuItem();
        mTypes = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        mInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("mainFrame"); // NOI18N
        setSize(new java.awt.Dimension(0, 0));

        pInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));

        lInfo.setText("_Welcome");

        javax.swing.GroupLayout pInfoLayout = new javax.swing.GroupLayout(pInfo);
        pInfo.setLayout(pInfoLayout);
        pInfoLayout.setHorizontalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );
        pInfoLayout.setVerticalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        lInfo.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mFile.setText("_File");
        mFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mExit.setText("_Exit");
        mExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mExitActionPerformed(evt);
            }
        });
        mFile.add(mExit);

        jMenuBar1.add(mFile);

        mAdmin.setText("_Admin");
        mAdmin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mUsers.setText("_Users");
        mUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mUsersActionPerformed(evt);
            }
        });
        mAdmin.add(mUsers);

        mRoles.setText("_Roles");
        mRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRolesActionPerformed(evt);
            }
        });
        mAdmin.add(mRoles);

        mActions.setText("_Actions");
        mActions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionsActionPerformed(evt);
            }
        });
        mAdmin.add(mActions);

        mResources.setText("_Resources");
        mResources.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mResourcesActionPerformed(evt);
            }
        });
        mAdmin.add(mResources);

        mTypes.setText("_Types");
        mTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTypesActionPerformed(evt);
            }
        });
        mAdmin.add(mTypes);

        jMenuBar1.add(mAdmin);

        mHelp.setText("_Help");

        mInfo.setText("_Info");
        mInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mInfoActionPerformed(evt);
            }
        });
        mHelp.add(mInfo);

        jMenuBar1.add(mHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(mainPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mExitActionPerformed
        System.out.println("Trying...Exit");
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //Closes all JFrames
        //System.exit(0);        
    }//GEN-LAST:event_mExitActionPerformed

    private void mUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mUsersActionPerformed
        UsersUI uView = new UsersUI();
        validateNewUI(uView);

    }//GEN-LAST:event_mUsersActionPerformed

    private void mRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRolesActionPerformed
        RolesUI rView = new RolesUI();
        validateNewUI(rView);

    }//GEN-LAST:event_mRolesActionPerformed

    private void mTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTypesActionPerformed
        TypesUI tView = new TypesUI();
        validateNewUI(tView);
    }//GEN-LAST:event_mTypesActionPerformed

    private void mActionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mActionsActionPerformed
        ActionsUI aView = new ActionsUI();
        validateNewUI(aView);
    }//GEN-LAST:event_mActionsActionPerformed

    private void mResourcesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mResourcesActionPerformed
        ResourcesUI rView = new ResourcesUI();
        validateNewUI(rView);
    }//GEN-LAST:event_mResourcesActionPerformed

    private void mInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mInfoActionPerformed
        InfoUI iView = new InfoUI();
        validateNewUI(iView);
    }//GEN-LAST:event_mInfoActionPerformed

    public void validateNewUI(JInternalFrame newUI){
        //Check if user has access right
        if (UIUtils.isAccessAllowed(newUI)) {
            final JInternalFrame[] frames = mainPanel.getAllFrames();
            //Makes sure just one window of its type open.
            if (!Arrays.asList(frames).toString().contains(newUI.getClass().getSimpleName())) {
                mainPanel.add(newUI);
                UIUtils.getLocalText(newUI);      ///Get text for labels/fields 
                mainPanel.validate();
                newUI.moveToFront();
            }
        }
    }
    
    public void setInfoText(String s) {
        this.lInfo.setText(s);
    }

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lInfo;
    private javax.swing.JMenuItem mActions;
    private javax.swing.JMenu mAdmin;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mHelp;
    private javax.swing.JMenuItem mInfo;
    private javax.swing.JMenuItem mResources;
    private javax.swing.JMenuItem mRoles;
    private javax.swing.JMenuItem mTypes;
    private javax.swing.JMenuItem mUsers;
    private javax.swing.JDesktopPane mainPanel;
    private javax.swing.JPanel pInfo;
    // End of variables declaration//GEN-END:variables

    private void myInitComponents() {
        UIUtils.getLocalText(this);
    }
}
