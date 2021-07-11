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

package com.idelogix.login.service;

import com.idelogix.login.model.Role;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nelson Terrazas
 */
public class RolesTableModel extends AbstractTableModel {

    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int COMMENTS_COL = 2;
    private static final int ENABLED_COL = 3;
    private static final int DATE_CREATED_COL = 4;
    private static final int USERS_COL = 5;
    private static final int ROLE_RESOURCE_ACTIONS_COL = 6;
    
    private static final String ID_COLNAME = Props.getInstance().getTxtProps("label._Id");
    private static final String NAME_COLNAME = Props.getInstance().getTxtProps("label._Name");
    private static final String COMMENTS_COLNAME = Props.getInstance().getTxtProps("label._Comments");
    private static final String ENABLED_COLNAME = Props.getInstance().getTxtProps("label._Enabled");
    private static final String DATE_CREATED_COLNAME = Props.getInstance().getTxtProps("label._Date_created");

    private final String[] columnNames = {ID_COLNAME, NAME_COLNAME, COMMENTS_COLNAME, ENABLED_COLNAME, DATE_CREATED_COLNAME, "user_array", "resource_action_array" };
    private final List<Role> roles;

    public RolesTableModel(List<Role> theRoles) {
        roles = theRoles;
    }

    public Role getRoleById(int roleId){
        for(Role r : roles){
            if(r.getId()==roleId){
                return r;
            }
        }
        return null;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return roles.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Role tempRole = roles.get(row);
        switch (col) {
            case ID_COL:
                return tempRole.getId();
            case NAME_COL:
                return tempRole.getName();
            case COMMENTS_COL:
                return tempRole.getComments();
            case ENABLED_COL:
                return tempRole.getEnabled();
            case DATE_CREATED_COL:
                return tempRole.getDateCreated();
            case USERS_COL:
                return tempRole.getUserArrayList();
            case ROLE_RESOURCE_ACTIONS_COL:
                return tempRole.getRoleResourceActionArrayList();
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
