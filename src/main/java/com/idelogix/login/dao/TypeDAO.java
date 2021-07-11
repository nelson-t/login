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

    String queryAdd     = "INSERT INTO type(name,comments,enabled) VALUES(?,?,?)";
    String queryUpdate  = "UPDATE type SET name=?, comments=?, enabled=? WHERE id=?";
    String queryDelete  = "DELETE FROM type WHERE id = ?";
    String queryGetById = "SELECT * FROM type where id = ?";
    String queryGetByName = "SELECT * FROM type where name = ?";
    String queryGetAll  = "SELECT * FROM type";

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
