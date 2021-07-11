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

import com.idelogix.login.dao.RoleResourceActionDAO;
import com.idelogix.login.model.RoleResourceAction;
import com.idelogix.login.service.Globals;
import com.idelogix.login.service.Props;
import com.idelogix.login.service.ResourceService;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListModel;

/**
 *
 * @author Nelson Terrazas
 */
public class UIUtils {

    static void transferElement(String rName, JList<String> jList1, JList<String> jList2) {
        if (rName == null) {
            return;
        }
        ListModel<String> model1 = jList1.getModel();
        String[] sArray1 = new String[model1.getSize() - 1];
        int j = 0;
        for (int i = 0; i < model1.getSize(); i++) {
            String s = (String) model1.getElementAt(i);
            if (!rName.equals(s)) {
                sArray1[j++] = s;
            }
        }
        ListModel<String> model2 = jList2.getModel();
        String[] sArray2 = new String[model2.getSize() + 1];
        for (int i = 0; i < model2.getSize(); i++) {
            String s = (String) model2.getElementAt(i);
            sArray2[i] = s;
        }
        sArray2[model2.getSize()] = rName;
        jList1.setListData(sArray1);
        jList2.setListData(sArray2);
    }

    static ArrayList jListToArrayList(JList jList) {
        ListModel<String> model = jList.getModel();
        ArrayList<String> aList = new ArrayList(model.getSize());
        for (int i = 0; i < model.getSize(); i++) {
            String s = (String) model.getElementAt(i);
            aList.add(s);
        }
        return aList;
    }

    static void hideTableColumns(JTable t, int[] cols) {
        for (int i = 0; i < cols.length; i++) {
            t.getColumnModel().getColumn(cols[i]).setMinWidth(0);
            t.getColumnModel().getColumn(cols[i]).setMaxWidth(0);
        }
    }

    static String getLocalText(String tag) {
        return Props.getInstance().getTxtProps(tag);
    }

    static void enableActionButtons(JInternalFrame frame) {
        //Get all logged role's resources & actions
        ArrayList<RoleResourceAction> rra = RoleResourceActionDAO.getInstance().getResourcesRoleActions(Globals.getLoggedUserRoleId());
        //Verify that each JButton and related action is included in Role's rights.
        for (Component c : getAllComponents(frame)) {
            if (c.getClass().getTypeName().equals("javax.swing.JButton")) {
                String bText = ((JButton) c).getText();
                for (RoleResourceAction r : rra) {
                    if (r.getResource().getName().equals(frame.getClass().getSimpleName()) && r.getAction().getButtonText().equals(bText)) {
                        ((JButton) c).setEnabled(true);
                    }
                }
            }
        }
    }

    static void checkAccessRights(JInternalFrame frame) {
        for (Component c : getAllComponents(frame)) {
            if (c.getClass().getTypeName().equals("javax.swing.JButton")) {
                if (((JButton) c).getText().equals("_Edit")) {
                    if (!isResourceActionAllowed(frame, Globals.EDIT_ACTION)) {
                        ((JButton) c).setVisible(false);
                    }
                }
                if (((JButton) c).getText().equals("_Create")) {
                    if (!isResourceActionAllowed(frame, Globals.CREATE_ACTION)) {
                        ((JButton) c).setVisible(false);
                    }
                }
                if (((JButton) c).getText().equals("_Delete")) {
                    if (!isResourceActionAllowed(frame, Globals.DELETE_ACTION)) {
                        ((JButton) c).setVisible(false);
                    }
                }
            }
        }
    }

    static boolean isResourceActionAllowed(JInternalFrame jf, String actionName) {
        return ResourceService.getInstance().isActionAllowed(jf.getClass().getSimpleName(), actionName, Globals.getLoggedUserRoleName());
    }

    static boolean isAccessAllowed(JInternalFrame jf) {
        if (!ResourceService.getInstance().isAccessAllowed(jf.getClass().getSimpleName(), Globals.getLoggedUserRoleName())) {
            JOptionPane.showMessageDialog(javax.swing.FocusManager.getCurrentManager().getActiveWindow(), getLocalText("msg._You_are_not_authorized!"));
            jf.dispose();
            return false;
        }
        return true;
    }

    static void getLocalText(Container jf) {
        if (jf.getClass().getSuperclass().getName().equals("javax.swing.JInternalFrame")) {
            String title = UIUtils.getLocalText("title." + ((javax.swing.JInternalFrame) jf).getTitle());
            ((JInternalFrame) jf).setTitle(title);
        }
        for (Component c : getAllComponents(jf)) {
            //System.out.println("==>" + c.getClass().getTypeName());
            if (c.getClass().getTypeName().equals("javax.swing.JLabel") && ((JLabel) c).getText().startsWith("_")) {
                ((JLabel) c).setText(UIUtils.getLocalText("label." + ((JLabel) c).getText()));
            }
            if (c.getClass().getTypeName().equals("javax.swing.JButton") && ((JButton) c).getText().startsWith("_")) {
                ((JButton) c).setText(UIUtils.getLocalText("button." + ((JButton) c).getText()));
            }
            if (c.getClass().getTypeName().equals("javax.swing.JMenu") && ((JMenu) c).getText().startsWith("_")) {
                ((JMenu) c).setText(UIUtils.getLocalText("menu." + ((JMenu) c).getText()));
            }
            if (c.getClass().getTypeName().equals("javax.swing.JMenuItem") && ((JMenuItem) c).getText().startsWith("_")) {
                ((JMenuItem) c).setText(UIUtils.getLocalText("menu." + ((JMenuItem) c).getText()));
            }
            if (c.getClass().getTypeName().equals("javax.swing.JTabbedPane")) {
                for (int i = 0; i < ((JTabbedPane) c).getTabCount(); i++) {
                    if (((JTabbedPane) c).getTitleAt(i).startsWith("_")) {
                        ((JTabbedPane) c).setTitleAt(i, UIUtils.getLocalText("tab." + ((JTabbedPane) c).getTitleAt(i)));
                    }
                }
            }
        }
    }

    static List<Component> getAllComponents(final Container c) {
        //Recursive
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<>();
        for (Component comp : comps) {
            compList.add(comp);
            //System.out.println("==>" + comp.getClass().getTypeName());
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
            if (comp instanceof JMenu) {
                Component[] menuItems = ((JMenu) comp).getMenuComponents();
                for (Component compMenu : menuItems) {
                    compList.add(compMenu);
                }
            }
        }
        return compList;
    }

}
