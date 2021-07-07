package com.idelogix.login.dao;

import com.idelogix.login.model.Action;
import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.service.Utils;
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
    String queryUpdate = "UPDATE action SET name=?, comments=?, enabled=? WHERE id=?";
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

    @Override
    public GenericEntity getEntityFromRS(ResultSet resultSet) {
        GenericEntity theNewAction = null;
        try {
            theNewAction = new Action(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getDate(5));
        } catch (SQLException e) {
            Utils.log.error("Error in ActionDAO", e);
        }
        return theNewAction;
    }
}
