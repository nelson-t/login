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
 * @author Nelson Terrazas
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
    
    public ArrayList<RoleResourceAction> getResourcesRoleActions(int roleId)
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
