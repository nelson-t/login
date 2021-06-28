/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.service;

import com.idelogix.login.dao.RoleDAO;
import com.idelogix.login.dao.UserDAO;
import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.model.Role;
import com.idelogix.login.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
//Singleton
public class UserService {

    private static final UserService userServiceInstance = new UserService();
    //Can later on be change with Dependency Injection
    private final UserDAO userDao = UserDAO.getInstance();

    public static UserService getInstance() {
        return userServiceInstance;
    }

    private UserService() {
    }

    public boolean createUser(Integer id, String name, String comments, Boolean enabled, Date dateCreated, String password, ArrayList roles) {
        String newPassword = Utils.crypt(password);
        User u = new User(id, name, comments, enabled, dateCreated, newPassword);
        if (addUser(u)) {
            saveRoles(u.getId(), roles);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUser(Integer uId, String name, String comments, boolean enabled, Date dateCreated, String password, ArrayList roles) {
        User u = new User(uId, name, comments, enabled, dateCreated, password);
        if (updateUser(u)) {
            saveRoles(u.getId(), roles);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveRoles(int userId, ArrayList roles) {
        //First dele all current roles
        userDao.deleteUserRoles(userId);
        //Then update new roles
        return userDao.updateUserRoles(userId, roles);
    }

    public boolean userExists(String name) {
        return (getUser(name) != null);
    }

    public User getUser(int userId) {
        return userDao.getById(userId);
    }

    public User getUser(String userName) {
        return userDao.getByName(userName);
    }

    public boolean deleteUser(int userId) {
        return userDao.delete(userId);
    }

    public boolean updateUser(User u) {
        return userDao.update(u);
    }

    public boolean addUser(User u) {
        return userDao.add(u);
    }

    public ArrayList getAll() {
        return getAll(0, 0, null);
    }

    public ArrayList getAll(String searchString) {
        return getAll(0, 0, searchString);
    }

    public ArrayList getAll(int limit, int offset, String searchString) {
        ArrayList uList = userDao.getAll(limit, offset, searchString);
        return uList;
    }

    public boolean setUserPassword(int uid, String oldPassword, String newPassword) {
        User u = (User) userDao.getById(uid);
        if (oldPassword == null || u.getPassword().equals(Utils.crypt(oldPassword))) {
            u.setPassword(Utils.crypt(newPassword));
            userDao.update(u);
            return true;
        }
        return false;
    }

    public DefaultTableModel getTableModel(String searchText) {
        DefaultTableModel tModel = new DefaultTableModel();
        tModel.addColumn(Props.getInstance().getTxtProps("label._User_Id"));           //0
        tModel.addColumn(Props.getInstance().getTxtProps("label._User_name"));         //1
        tModel.addColumn(Props.getInstance().getTxtProps("label._Comments"));     //2
        tModel.addColumn(Props.getInstance().getTxtProps("label._Enabled"));      //3
        tModel.addColumn(Props.getInstance().getTxtProps("label._Date_created"));  //4
        tModel.addColumn(Props.getInstance().getTxtProps("label._Password"));     //5
        Object rowData[] = new Object[6];
        ArrayList<User> myData = getAll(searchText);
        for (int i = 0; i < myData.size(); i++) {
            rowData[0] = myData.get(i).getId();
            rowData[1] = myData.get(i).getName();
            rowData[2] = myData.get(i).getComments();
            rowData[3] = myData.get(i).getEnabled();
            rowData[4] = myData.get(i).getDateCreated(); //Assumed date is returned in format: "yyyy-MM-dd"
            rowData[5] = ((User) myData.get(i)).getPassword();
            tModel.addRow(rowData);
        }
        return tModel;
    }

    public boolean validateUserLogin(String userName, String userPassword, String roleName) {
        User u = (User) userDao.getByName(userName);
        if (u == null) // user name does not exist
        {
            return false;
        }
        if (u.getPassword().equals(Utils.crypt(userPassword))) {
            Globals.setLoggedUserId(u.getId());
            Globals.setLoggedUserName(u.getName());
            Globals.setLoggedUserRoleName(roleName);
            Globals.setLoggedUserRoleId(RoleService.getInstance().getRole(roleName).getId());
            return true;
        } else {
            return false;
        }
    }

    public String[] getUserRoleList(String name) {
        return getUserRoleList(getUser(name).getId());
    }

    public String[] getUserRoleList(int uId) {
        User u = getUser(uId);
        ArrayList<Role> userRolesList = u.getRoleArrayList();
        String arr[] = new String[userRolesList.size()];
        for (int i = 0; i < userRolesList.size(); i++) {
            arr[i] = userRolesList.get(i).getName();
        }
        return arr;
    }

    public String[] getUserAvailableNewRoles(String[] aRoles) {
        List userCurrentRoles = Arrays.asList(aRoles);
        ArrayList<GenericEntity> allRolesList = RoleDAO.getInstance().getAll();
        String arr[] = new String[allRolesList.size()];
        for (int i = 0; i < allRolesList.size(); i++) {
            if (!userCurrentRoles.contains(allRolesList.get(i).getName())) {
                arr[i] = allRolesList.get(i).getName();
            }
        }
        return arr;
    }
    
    public int getStringFieldMaxSize(String entityName, String fieldName){
        return userDao.getStringFieldMaxSize(entityName, fieldName);
    }
}
