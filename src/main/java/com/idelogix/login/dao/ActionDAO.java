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

import com.idelogix.login.model.Action;
import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.service.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nelson Terrazas
 */
public class ActionDAO extends GenericEntityDAO {

    //Singleton
    private static final ActionDAO actionJdbcDAOInstance = new ActionDAO();

    String queryAdd = "INSERT INTO action(name,comments,enabled) VALUES(?,?,?)";
    String queryUpdate = "UPDATE action SET name=?, comments=?, enabled=?, allow_access=?, button_text=? WHERE id=?";
    String queryDelete = "DELETE FROM action WHERE id = ?";
    String queryGetById = "SELECT * FROM action where id = ?";
    String queryGetByName = "SELECT * FROM action where name = ?";
    String queryGetAll = "SELECT * FROM action";

    public static ActionDAO getInstance() {
        return actionJdbcDAOInstance;
    }

    private ActionDAO() {
        super();
        super.setQueryStrings(queryAdd, queryUpdate, queryDelete, queryGetById, queryGetByName, queryGetAll);
    }
    
    public boolean add(Action action) {
        super.add(action);
        Action action2 = (Action) super.getByName(action.getName());
        action2.setAllowAccess(action.getAllowAccess());
        action2.setButtonText(action.getButtonText());
        return (update(action2));
    }

    public boolean update(Action action) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryUpdate);
            ptmt.setString(1, action.getName());
            ptmt.setString(2, action.getComments());
            ptmt.setInt(3, (action.getEnabled()) ? 1 : 0);
            ptmt.setInt(4, (action.getAllowAccess()) ? 1 : 0);
            ptmt.setString(5, action.getButtonText());
            //For "Where" clause
            ptmt.setInt(6, action.getId());
            ptmt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            Utils.log.error("Error on Action DAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }

    @Override
    public GenericEntity getEntityFromRS(ResultSet resultSet) {
        GenericEntity theNewAction = null;
        try {
            theNewAction = new Action(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getDate(5),
                                    resultSet.getBoolean(6), resultSet.getString(7));
        } catch (SQLException e) {
            Utils.log.error("Error in ActionDAO", e);
        }
        return theNewAction;
    }
}
