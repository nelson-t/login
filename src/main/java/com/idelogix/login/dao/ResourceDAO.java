package com.idelogix.login.dao;

import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.model.Resource;
import com.idelogix.login.service.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nelson Terrazas
 */
public class ResourceDAO extends GenericEntityDAO {

    //Singleton implementation
    private static final ResourceDAO resourceJdbcDAOInstance = new ResourceDAO();

    String queryAdd         = "INSERT INTO resource(name,comments,enabled) VALUES(?,?,?)";
    String queryUpdate      = "UPDATE resource SET name=?, comments=?, enabled=? WHERE id=?";
    String queryDelete      = "DELETE FROM resource WHERE id = ?";
    String queryGetById     = "SELECT * FROM resource where action_id = ?";
    String queryGetByName   = "SELECT * FROM resource where name = ?";
    String queryGetAll      = "SELECT * FROM resource ";
    String queryAuth1       = "SELECT * FROM role_resource_action WHERE role_id = ? AND resource_id = ? AND action_id = ?";

    public static ResourceDAO getInstance() {
        return resourceJdbcDAOInstance;
    }

    private ResourceDAO() {
        super();
        super.setQueryStrings(queryAdd, queryUpdate, queryDelete, queryGetById, queryGetByName, queryGetAll);
    }

    @Override
    public GenericEntity getEntityFromRS(ResultSet resultSet) {
        GenericEntity theNewResource = null;
        try {
            theNewResource = new Resource(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getDate(5));
        } catch (SQLException e) {
            Utils.log.error("Error on ResourceDAO", e);
        }
        return theNewResource;
    }
    
    public boolean isActionAllowed(int resourceId, int actionId, int roleId){
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        boolean success=false;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryAuth1);
            ptmt.setInt(1, roleId);
            ptmt.setInt(2, resourceId);
            ptmt.setInt(3, actionId);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                success=true;
            }
        } catch (SQLException e) {
            Utils.log.error("Error on ResoruceDAO", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e ) {
                Utils.log.error("Error on ResoruceDAO", e);
            } catch (Exception e) {
                Utils.log.error("Error on ResoruceDAO", e);
            }
        }
        return success;    
    }
}
