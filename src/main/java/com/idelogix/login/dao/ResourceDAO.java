package com.idelogix.login.dao;

import com.idelogix.login.model.Action;
import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.model.Resource;
import com.idelogix.login.service.ActionService;
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
public class ResourceDAO extends GenericEntityDAO {

    //Singleton implementation
    private static final ResourceDAO resourceJdbcDAOInstance = new ResourceDAO();

    String queryAdd = "INSERT INTO resource(name,comments,enabled) VALUES(?,?,?)";
    String queryUpdate = "UPDATE resource SET name=?, comments=?, enabled=? WHERE id=?";
    String queryDelete = "DELETE   FROM resource WHERE id = ?";
    String queryGetById = "SELECT * FROM resource WHERE id = ?";
    String queryGetByName = "SELECT * FROM resource WHERE name = ?";
    String queryGetAll = "SELECT * FROM resource";
    String queryAuth1 = "SELECT * FROM role_resource_action WHERE role_id = ? AND resource_id = ? AND action_id = ?";
    String queryGetResourceActions = "SELECT * FROM resource_action WHERE resource_id = ?";
    String queryAddRoleResourceAction = "INSERT INTO role_resource_action(role_id, resource_id, action_id) VALUES(?,?,?)";
    String queryGetByType = "SELECT * FROM resource WHERE type_id = ? ORDER BY name";
    String queryGetByAction = "SELECT resource.name FROM resource, resource_action where resource.id=resource_action.resource_id AND resource_action.action_id=?";
    String queryDeleteAllResourceActions="DELETE from resource_action WHERE resource_id=? ";
    String queryAddResourceAction = "INSERT INTO resource_action() VALUES (?,?)";
            
    public static ResourceDAO getInstance() {
        return resourceJdbcDAOInstance;
    }

    private ResourceDAO() {
        super();
        super.setQueryStrings(queryAdd, queryUpdate, queryDelete, queryGetById, queryGetByName, queryGetAll);
    }
    
    @Override
    public Resource getById(Integer id) {
        Resource r = (Resource) super.getById(id);
        if (r != null) {
            r = loadResourceActions(r);
        }
        return r;
    }

    @Override
    public Resource getByName(String name) {
        Resource r = (Resource) super.getByName(name);
        if (r != null) {
            r = loadResourceActions(r);
        }
        return r;
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

    public boolean isActionAllowed(int resourceId, int actionId, int roleId) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        boolean success = false;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryAuth1);
            ptmt.setInt(1, roleId);
            ptmt.setInt(2, resourceId);
            ptmt.setInt(3, actionId);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                success = true;
            }
        } catch (SQLException e) {
            Utils.log.error("Error on ResoruceDAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return success;
    }

    public Resource loadResourceActions(Resource r) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        ArrayList<Action> actionList;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryGetResourceActions);
            ptmt.setInt(1, r.getId());
            resultSet = ptmt.executeQuery();
            actionList = new ArrayList<>();
            while (resultSet.next()) {
                Action a = (Action) ActionDAO.getInstance().getById(resultSet.getInt(2));
                actionList.add(a);
            }
            r.setActionArrayList(actionList);
        } catch (SQLException e) {
            Utils.log.error("Error on ResourceDAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return r;
    }

    public boolean addRoleResourceAction(int roleId, String resourceName, String actionName) {
        int resourceId = this.getByName(resourceName).getId();
        int actionId = ActionDAO.getInstance().getByName(actionName).getId();
        return addRoleResourceAction(roleId, resourceId, actionId);
    }

    public boolean addRoleResourceAction(int roleId, int resourceId, int actionId) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        int result = -1;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryAddRoleResourceAction);
            ptmt.setInt(1, roleId);
            ptmt.setInt(2, resourceId);
            ptmt.setInt(3, actionId);
            result = ptmt.executeUpdate();
        } catch (SQLException e) {
            Utils.log.error("Error on ResourceDAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return (result > 0);
    }

    public ArrayList<String> getResourceNamesByType(int typeId) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        ArrayList<String> names = new ArrayList<>();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryGetByType);
            ptmt.setInt(1, typeId);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                names.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            Utils.log.error("Error on ResourceDAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return names;
    }    

    public ArrayList<String> getResourceNamesByAction(int actionId) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        ArrayList<String> names = new ArrayList<>();
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryGetByAction);
            ptmt.setInt(1, actionId);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                names.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            Utils.log.error("Error on ResourceDAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return names;
    }       
    
    public boolean deleteAllResourceActions(Integer id) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryDeleteAllResourceActions);
            ptmt.setInt(1, id);
            success = (ptmt.executeUpdate() > 0);
        } catch (SQLException e) {
            Utils.log.error("Error on ResourceDAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }

    public boolean updateResourceActions(int resourceId, ArrayList<String> actionList) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            int updateCount = 0;
            for (String a : actionList) {
                Action action = ActionService.getInstance().getAction(a);
                if (!(action == null)) {
                    ptmt = connection.prepareStatement(queryAddResourceAction);
                    ptmt.setInt(1, resourceId);
                    ptmt.setInt(2, action.getId());
                    updateCount = updateCount + (ptmt.executeUpdate());
                }
            }
            success = (updateCount > 0);
        } catch (SQLException e) {
            Utils.log.error("Error on Resource DAO", e);
        } finally {
            Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }
    
    
}
