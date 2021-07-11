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

package com.idelogix.login.dao;

import com.idelogix.login.model.Role;
import com.idelogix.login.model.RoleResourceAction;
import com.idelogix.login.model.RoleResourceActionPK;
import com.idelogix.login.model.User;
import com.idelogix.login.service.ActionService;
import com.idelogix.login.service.ResourceService;
import com.idelogix.login.service.RoleService;
import com.idelogix.login.service.UserService;
import com.idelogix.login.service.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson Terrazas
 */
public class RoleDAO extends GenericEntityDAO {

    private static final RoleDAO roleJdbcDAOInstance = new RoleDAO();

    String queryAdd = "INSERT INTO role(name,comments,enabled) VALUES(?,?,?)";
    String queryUpdate = "UPDATE role SET name=?, comments=?, enabled=? WHERE id=?";
    String queryGetById = "SELECT * FROM role where id = ?";
    String queryGetByName = "SELECT * FROM role where name = ?";
    String queryGetAll = "SELECT * FROM role";
    String queryGetUserList = "SELECT * FROM user_role  WHERE role_id = ? ";
    String queryGetRoleResourceActionList = "SELECT * FROM role_resource_action WHERE role_id = ? ";
    String queryAddRoleUser = "INSERT INTO user_role() VALUES (?,?)";
    String queryDelete = "DELETE FROM role WHERE id = ?";
    String queryDeleteAllRoleUsers = "DELETE FROM user_role WHERE role_id = ? ";
    String queryDeleteAllRoleResources = "DELETE FROM role_resource_action WHERE role_id = ? ";
    
    public static RoleDAO getInstance() {
        return roleJdbcDAOInstance;
    }

    private RoleDAO() {
        super();
        super.setQueryStrings(queryAdd, queryUpdate, queryDelete, queryGetById, queryGetByName, queryGetAll);
    }


    @Override
    public Role getEntityFromRS(ResultSet resultSet) {
        Role theNewRole = null;
        try {
            theNewRole = new Role(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getDate(5));
        } catch (SQLException e) {
            Utils.log.error("Error on RoleDAO", e);
        }
        return theNewRole;
    }

    public Role loadRoleUsers(Role r) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        ArrayList<User> userList;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryGetUserList);
            ptmt.setInt(1, r.getId());
            resultSet = ptmt.executeQuery();
            userList = new ArrayList<>();
            while (resultSet.next()) {
                User u = (User) UserDAO.getInstance().getById(resultSet.getInt(1));
                userList.add(u);
            }
            r.setUserArrayList(userList);
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return r;
    }
    
     public Role loadResourcesAndActions(Role r) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        ArrayList<RoleResourceAction> roleResourceActionArrayList;        
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryGetRoleResourceActionList);
            ptmt.setInt(1, r.getId());
            resultSet = ptmt.executeQuery();
            roleResourceActionArrayList = new ArrayList<>();
            while (resultSet.next()) {
                int roleId      =resultSet.getInt(1);
                int resourceId  =resultSet.getInt(2);
                int actionId    =resultSet.getInt(3);
                //Cretae key object
                RoleResourceActionPK pk = new RoleResourceActionPK(roleId, resourceId, actionId);
                //Create and uodate RRA
                RoleResourceAction rra = new RoleResourceAction(pk);
                rra.setRole(RoleService.getInstance().getRole(roleId));
                rra.setResource(ResourceService.getInstance().getResource(resourceId));
                rra.setAction(ActionService.getInstance().getAction(actionId));
                //Add RRA object to array
                roleResourceActionArrayList.add(rra);
            }
            //Load RRA array to role object
            r.setRoleResourceActionArrayList(roleResourceActionArrayList);
        } catch (SQLException e) {
            Utils.log.error("Error on RoleDAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return r;
    }
    
    public boolean deleteAllRoleUsers(Integer roleId) {
        boolean success = true;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryDeleteAllRoleUsers);
            ptmt.setInt(1, roleId);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
            success=false;
        } finally {
            Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }

    public boolean deleteAllRoleResources(Integer roleId) {
        boolean success = true;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryDeleteAllRoleResources);
            ptmt.setInt(1, roleId);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
            success=false;
        } finally {
            Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }
    
    public boolean updateRoleUsers(Integer roleId, ArrayList<String> userList) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            int updateCount = 0;
            for (String u : userList) {
                User user = UserService.getInstance().getUser(u);
                if (!(user == null)) {
                    ptmt = connection.prepareStatement(queryAddRoleUser);
                    ptmt.setInt(1, user.getId());
                    ptmt.setInt(2, roleId);
                    updateCount = updateCount + (ptmt.executeUpdate());
                }
            }
            success = (updateCount > 0);
        } catch (SQLException e) {
            Utils.log.error("Error on RoleDAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }
}
