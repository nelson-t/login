package com.idelogix.login.dao;

import com.idelogix.login.model.Type;
import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.service.Utils;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nelson Terrazas
 */
public class TypeDAO extends GenericEntityDAO {

    //Singleton
    private static final TypeDAO typeJdbcDAOInstance = new TypeDAO();

    String queryAdd = "INSERT INTO type(name,comments,enabled) VALUES(?,?,?)";
    String queryUpdate = "UPDATE type SET name=?, comments=?, enabled=? WHERE id=?";
    String queryDelete = "DELETE FROM type WHERE id = ?";
    String queryGetById = "SELECT * FROM type where id = ?";
    String queryGetByName = "SELECT * FROM type where name = ?";
    String queryGetAll = "SELECT * FROM type ";

    public static TypeDAO getInstance() {
        return typeJdbcDAOInstance;
    }

    private TypeDAO() {
        super();
        super.setQueryStrings(queryAdd, queryUpdate, queryDelete, queryGetById, queryGetByName, queryGetAll);
    }

    @Override
    public GenericEntity getEntityFromRS(ResultSet resultSet) {
        GenericEntity theNewType = null;
        try {
            theNewType = new Type(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getDate(5));
        } catch (SQLException e) {
            Utils.log.error("Error in TypeDAO", e);
        }
        return theNewType;
    }
}
