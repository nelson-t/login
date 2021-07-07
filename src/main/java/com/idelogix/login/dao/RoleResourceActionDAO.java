/*
 * To change this license header, choose License HeaderesultSet in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.dao;

import com.idelogix.login.model.RoleResourceAction;
import com.idelogix.login.model.RoleResourceActionPK;
import com.idelogix.login.service.ActionService;
import com.idelogix.login.service.ResourceService;
import com.idelogix.login.service.RoleService;
import com.idelogix.login.service.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class RoleResourceActionDAO {
    
    private static final RoleResourceActionDAO roleResourceActionDAO = new RoleResourceActionDAO();

    String queryGetRoleResourceActionList = "SELECT * FROM role_resource_action WHERE role_id = ?";

    public static RoleResourceActionDAO getInstance() {
        return roleResourceActionDAO;
    }

    private RoleResourceActionDAO() {
    }
    
    Connection getConnection() throws SQLException {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }
    
    ArrayList<RoleResourceAction> getResourcesRoleActions(int roleId)
    {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        ArrayList<RoleResourceAction> rraList=null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryGetRoleResourceActionList);
            //For "Where" clause
            ptmt.setInt(1, roleId);
            resultSet = ptmt.executeQuery();
            rraList = new ArrayList<>();
            while (resultSet.next()) {
                RoleResourceActionPK pk=new RoleResourceActionPK(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3));
                RoleResourceAction rra = new RoleResourceAction(pk);
                rra.setRole(RoleService.getInstance().getRole(pk.getRoleId()));
                rra.setResource(ResourceService.getInstance().getResource(pk.getResourceId()));
                rra.setAction(ActionService.getInstance().getAction(pk.getActionId()));
                rraList.add(rra);
            }
        } catch (SQLException e) {
            Utils.log.error("Error on RRA DAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return rraList;
    }
}
