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

import com.idelogix.login.dao.ResourceDAO;
import com.idelogix.login.dao.RoleDAO;
import com.idelogix.login.dao.UserDAO;
import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.model.Role;
import com.idelogix.login.model.RoleResourceAction;
import com.idelogix.login.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nelson Terrazas
 */
//Singleton
public class RoleService {

    private static final RoleService roleServiceInstance = new RoleService();
    //private Role theAppRole;
    //Can later on be change with Dependency Injection
    private final RoleDAO roleDao = RoleDAO.getInstance();

    public static RoleService getInstance() {
        return roleServiceInstance;
    }

    private RoleService() {
    }

    public Role getRole(int roleId) {
        return (Role) roleDao.getById(roleId);
    }

    public Role getRoleComplete(int roleId) {
        Role r = (Role) roleDao.getById(roleId);
        if (r != null) {
            r = RoleDAO.getInstance().loadRoleUsers(r);
            r = RoleDAO.getInstance().loadResourcesAndActions(r);
        }
        return r;
    }

    public RolesTableModel getRolesTableModel() {
        return (new RolesTableModel(getAllComplete()));
    }

    public List<Role> getAllComplete() {
        //Gets all roles including users and resources lists.
        ArrayList<Role> roleList = new ArrayList<>();
        for (Object o : getAll()) {
            Role r = (Role) o;
            r = RoleDAO.getInstance().loadRoleUsers(r);
            r = RoleDAO.getInstance().loadResourcesAndActions(r);
            if (r != null) {
                roleList.add(r);
            }
        }
        return roleList;
    }

    public Role getRole(String roleName) {
        return (Role) roleDao.getByName(roleName);
    }

    public boolean deleteRole(int roleId) {
        return roleDao.delete(roleId);
    }

    public boolean updateRole(Role r) {
        return roleDao.update(r);
    }

    public boolean updateRole(Integer uId, String name, String comments, boolean enabled, Date dateCreated, ArrayList users) {
        Role r = new Role(uId, name, comments, enabled, dateCreated);
        if (updateRole(r)) {
            saveUsers(r.getId(), users);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveUsers(int roleId, ArrayList users) {
        //First dele all current roles
        roleDao.deleteAllRoleUsers(roleId);
        //Then update new roles
        return roleDao.updateRoleUsers(roleId, users);
    }

    public boolean addRole(Role r) {
        return roleDao.add(r);
    }

    public ArrayList getAll() {
        return getAll(0, 0, null);
    }

    public ArrayList getAll(String searchString) {
        return getAll(0, 0, searchString);
    }

    public ArrayList getAll(int limit, int offset, String searchString) {
        ArrayList rList = roleDao.getAll(limit, offset, searchString);
        return rList;
    }

    public int getStringFieldMaxSize(String entityName, String fieldName) {
        return roleDao.getStringFieldMaxSize(entityName, fieldName);
    }

    public DefaultTableModel getDefaultTableModel(String searchText) {
        DefaultTableModel tModel = new DefaultTableModel();
        tModel.addColumn(Props.getInstance().getTxtProps("label._Id"));           //0
        tModel.addColumn(Props.getInstance().getTxtProps("label._Name"));    //1
        tModel.addColumn(Props.getInstance().getTxtProps("label._Comments"));     //2
        tModel.addColumn(Props.getInstance().getTxtProps("label._Enabled"));      //3
        tModel.addColumn(Props.getInstance().getTxtProps("label._Date_created")); //4
        Object rowData[] = new Object[5];
        ArrayList<Role> myData = getAll(searchText);
        for (int i = 0; i < myData.size(); i++) {
            rowData[0] = myData.get(i).getId();
            rowData[1] = myData.get(i).getName();
            rowData[2] = myData.get(i).getComments();
            rowData[3] = myData.get(i).getEnabled();
            rowData[4] = myData.get(i).getDateCreated(); //Assumed date is returned in format: "yyyy-MM-dd"
            tModel.addRow(rowData);
        }
        return tModel;
    }

    public String[][] getRoleResourcesActionsName2DArray(String roleName) {
        return getRoleResourcesActionsName2DArray(getRole(roleName).getId());
    }

    public String[][] getRoleResourcesActionsName2DArray(int roleId) {
        Role r = getRole(roleId);
        if (r == null) {
            return new String[0][0];
        }
        r = roleDao.loadResourcesAndActions(r);
        ArrayList<RoleResourceAction> rraList = r.getRoleResourceActionArrayList();
        String arr[][] = new String[rraList.size()][2];
        for (int i = 0; i < rraList.size(); i++) {
            arr[i][0] = rraList.get(i).getResource().getName();
            arr[i][1] = rraList.get(i).getAction().getName();
        }
        return arr;
    }

    public boolean saveRoleResourcesActions(int roleId, String[][] resourcesActions) {
        boolean success = true;
        for (String[] ra : resourcesActions) {
            if (!ResourceDAO.getInstance().addRoleResourceAction(roleId, ra[0], ra[1])) {
                success = false;
            }
        }
        return success;
    }

    public boolean deleteAllRoleResources(String roleName) {
        return deleteAllRoleResources(this.getRole(roleName).getId());
    }

    public boolean deleteAllRoleResources(int roleId) {
        return RoleDAO.getInstance().deleteAllRoleResources(roleId);
    }

    public String[] getRoleUsersNameList(String roleName) {
        return getRoleUsersNameList(getRole(roleName).getId());
    }

    public String[] getRoleUsersNameList(int roleId) {
        Role r = getRole(roleId);
        if (r == null) {
            return new String[0];
        }
        r = roleDao.loadRoleUsers(r);
        ArrayList<User> userRolesList = r.getUserArrayList();
        String arr[] = new String[userRolesList.size()];
        for (int i = 0; i < userRolesList.size(); i++) {
            arr[i] = userRolesList.get(i).getName();
        }
        return arr;
    }

    public String[] getRoleNotUserNamesList(int roleId) {
        ArrayList<GenericEntity> allUserList = UserDAO.getInstance().getAll(); //Get list of all users
        Role r = getRole(roleId);
        if (r != null) {
            r = roleDao.loadRoleUsers(r); //Get users belonging to the role
            ArrayList<User> userRolesList = r.getUserArrayList();
            for (int i = 0; i < userRolesList.size(); i++) {
                int index = -1;
                for (int j = 0; j < allUserList.size(); j++) {
                    if (userRolesList.get(i).getName().equals(allUserList.get(j).getName())) {
                        index = j;
                    }
                }
                if (index >= 0) {
                    allUserList.remove(index);
                }
            }
        }
        String arr[] = new String[allUserList.size()];
        for (int i = 0; i < allUserList.size(); i++) {
            arr[i] = allUserList.get(i).getName();
        }
        return arr;
    }

    public boolean createRole(Integer id, String name, String comments, Boolean enabled, Date dateCreated, ArrayList users) {
        Role r = new Role(id, name, comments, enabled, dateCreated);
        if (addRole(r)) {
            r = getRole(r.getName()); // We get autogenerate ID
            saveUsers(r.getId(), users);
            return true;
        } else {
            return false;
        }
    }

}
