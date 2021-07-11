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

import com.idelogix.login.service.Props;
import com.idelogix.login.service.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nelson Terrazas
 */
public class ConnectionFactory {

    private String driverClassName = Props.getInstance().getAppProps("datasource.driverClassName");
    private String connectionUrl = Props.getInstance().getAppProps("datasource.connectionUrl");
    private String dbUser = Props.getInstance().getAppProps("datasource.dbUser");
    private String dbPwd = Props.getInstance().getAppProps("datasource.dbPwd");
    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
        try {
            //Register JDBC Driver
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            Utils.log.error(e);
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        } catch (SQLException e) {
            Utils.log.error("Error on ConnectionFactory", e);
        }
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
