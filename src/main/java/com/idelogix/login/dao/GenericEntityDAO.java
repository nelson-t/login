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

import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.service.Props;
import com.idelogix.login.service.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson Terrazas
 */
abstract class GenericEntityDAO implements IGenericEntityDAO<GenericEntity> {

    private String queryAdd = null;
    private String queryUpdate = null;
    private String queryDelete = null;
    private String queryGetById = null;
    private String queryGetByName = null;
    private String queryGetAll = null;

    Connection getConnection() {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void setQueryStrings(String queryAdd, String queryUpdate, String queryDelete, String queryGetById, String queryGetByName, String queryGetAll) {
        this.queryAdd = queryAdd;
        this.queryUpdate = queryUpdate;
        this.queryDelete = queryDelete;
        this.queryGetById = queryGetById;
        this.queryGetByName = queryGetByName;
        this.queryGetAll = queryGetAll;
    }

    @Override
    public boolean add(GenericEntity genericEntity) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryAdd);
            ptmt.setString(1, genericEntity.getName());
            ptmt.setString(2, genericEntity.getComments());
            ptmt.setBoolean(3, genericEntity.getEnabled());
            ptmt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            Utils.log.error("Error on GenericDAO", e);
        } finally {
             Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }

    @Override
    public boolean update(GenericEntity genericEntity) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryUpdate);
            ptmt.setString(1, genericEntity.getName());
            ptmt.setString(2, genericEntity.getComments());
            ptmt.setInt(3, (genericEntity.getEnabled()) ? 1 : 0);
            //For "Where" clause
            ptmt.setInt(4, genericEntity.getId());
            ptmt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            Utils.log.error("Error on GenericDAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, null);
        }
        return success;
    }

    @Override
    public boolean delete(Integer id) {
        int delCount = 0;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryDelete);
            ptmt.setInt(1, id);
            delCount = ptmt.executeUpdate();
        } catch (SQLException e) {
            Utils.log.error(e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, null);
        }
        return (delCount > 0);
    }

    @Override
    public GenericEntity getById(Integer id) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        GenericEntity ge = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryGetById);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                ge = getEntityFromRS(resultSet);
            }
        } catch (SQLException e) {
            Utils.log.error("Error on GenericDAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return ge;
    }

    @Override
    public GenericEntity getByName(String name) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        GenericEntity ge = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(queryGetByName);
            ptmt.setString(1, name);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                ge = getEntityFromRS(resultSet);
            }
        } catch (SQLException e) {
            Utils.log.error("Error on GenericDAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return ge;
    }

    @Override
    public ArrayList<GenericEntity> getAll() {
        return getAll(0, 0, null);
    }

    @Override
    public ArrayList<GenericEntity> getAll(String searchString) {
        return getAll(0, 0, searchString);
    }

    @Override
    public ArrayList<GenericEntity> getAll(int limit, int offset, String searchString) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        String queryGetAll2 = queryGetAll;
        ArrayList<GenericEntity> gel = null;
        ResultSetMetaData rsmd;

        try {
            if (! (searchString == null || searchString.equals("")) ) {
                queryGetAll2 = queryGetAll2 + " WHERE INSTR(CONCAT(comments,name), ?)";
            }
            if (limit != 0) {
                queryGetAll2 = queryGetAll2 + " ORDER BY name LIMIT ? OFFSET ?";
            } else {          
                queryGetAll2 = queryGetAll2 + " ORDER BY name";
            }
            
            connection = getConnection();
            ptmt = connection.prepareStatement(queryGetAll2);

            if (! (searchString == null || searchString.equals("")) ) {
                ptmt.setString(1, searchString);
                if (limit != 0) {
                    ptmt.setInt(2, limit);
                    ptmt.setInt(3, offset);
                }
            } else {
                if (limit != 0) {
                    ptmt.setInt(1, limit);
                    ptmt.setInt(2, offset);
                }
            }
            resultSet = ptmt.executeQuery();
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            gel = new ArrayList(columnCount);
            while (resultSet.next()) {
                GenericEntity g = getEntityFromRS(resultSet);
                gel.add(g);
            }
        } catch (SQLException e) {
            Utils.log.error("Error on GenericDAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, resultSet);
        }
        return gel;
    }

    public int getStringFieldMaxSize(String entityName, String fieldName){
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        String theQuery = Props.getInstance().getAppProps("datasource.stringFieldSizeQuery");
        int theSize=0;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(theQuery);
            ptmt.setString(1, fieldName);
            ptmt.setString(2, entityName);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                theSize=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            Utils.log.error("Error on GenericDAO", e);
        } finally {
           Utils.tryCloseDB(connection, ptmt, resultSet);
        }
      return theSize;
    }

    public abstract GenericEntity getEntityFromRS(ResultSet resultSet);

}
