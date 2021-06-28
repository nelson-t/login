package com.idelogix.login.dao;

import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.model.Role;
import com.idelogix.login.service.Utils;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nelson Terrazas
 */
public class RoleDAO extends GenericEntityDAO {

    //Singleton implementation
    private static final RoleDAO roleJdbcDAOInstance = new RoleDAO();

    String queryAdd         = "INSERT INTO role(name,comments,enabled) VALUES(?,?,?)";
    String queryUpdate      = "UPDATE role SET name=?, comments=?, enabled=? WHERE id=?";
    String queryDelete      = "DELETE FROM role WHERE id = ?";
    String queryGetById     = "SELECT * FROM role where action_id = ?";
    String queryGetByName   = "SELECT * FROM role where name = ?";
    String queryGetAll      = "SELECT * FROM role ";

    public static RoleDAO getInstance() {
        return roleJdbcDAOInstance;
    }

    private RoleDAO() {
        super();
        super.setQueryStrings(queryAdd, queryUpdate, queryDelete, queryGetById, queryGetByName, queryGetAll);
    }

    @Override
    public GenericEntity getEntityFromRS(ResultSet resultSet) {
        GenericEntity theNewRole = null;
        try {
            theNewRole = new Role(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getDate(5));
        } catch (SQLException e) {
            Utils.log.error("Error on RoleDAO", e);
        }
        return theNewRole;
    }
}
