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
