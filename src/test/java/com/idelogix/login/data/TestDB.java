/*
 * The MIT License
 *
 * Copyright 2021 Administrator.
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
package com.idelogix.login.data;

import com.idelogix.login.dao.ConnectionFactory;
import com.idelogix.login.dao.DBUtils;
import com.idelogix.login.service.Utils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Administrator
 */
public class TestDB {

    @Test
    public void testDatabaseConection() {
        Utils.print("Checking if database connection is OK...");
        assertTrue(DBUtils.isDatabaseConection());
    }

    @Test
    public void testDatabaseExist() {
        Utils.print("Checking if database exists...");
        try {
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                assertTrue(DBUtils.databaseExists(conn, "login"));
                assertTrue(!DBUtils.databaseExists(conn, "XYZ"));
            }
        } catch (Exception ex) {
            Utils.log.error("Error on TestDB", ex);
            assertTrue(false);
        }
    }

    @Test
    public void testTableExist() {
        Utils.print("Checking if tables exist...");
        try {
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                assertTrue(DBUtils.tableExists(conn, "user"));
                assertTrue(DBUtils.tableExists(conn, "role"));
                assertTrue(DBUtils.tableExists(conn, "resource"));
                assertTrue(DBUtils.tableExists(conn, "action"));
                assertTrue(DBUtils.tableExists(conn, "type"));
                assertTrue(DBUtils.tableExists(conn, "user_role"));
                assertTrue(DBUtils.tableExists(conn, "resource_action"));
                assertTrue(DBUtils.tableExists(conn, "role_resource_action"));
                assertTrue(!DBUtils.tableExists(conn, "XYZ"));
            }
        } catch (Exception ex) {
            Utils.log.error("Error on TestDB", ex);
            assertTrue(false);
        }
    }

    @Test
    public void testStoreProcedure() {
        try {
            try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
                CallableStatement cs = connection.prepareCall("{call getNumResources(?)}");
                cs.registerOutParameter(1, Types.NUMERIC);
                cs.execute();
                String qty = cs.getString(1);
                System.out.println("Number of resources:" + qty + "\n");
                assertTrue(Integer.parseInt(qty) > 0);
            }
        } catch (Exception ex) {
            Utils.log.error("Error on TestDB", ex);
            assertTrue(false);
        }
    }

    @Test
    public void testStoreProcedure2() {
        try {
            try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
                CallableStatement cs = connection.prepareCall("{call getUserAuthData(?)}");
                cs.setString(1, "admin");
                ResultSet rs = cs.executeQuery();
                int nRecs = 0;
                Utils.print("'admin' access rights:");
                while (rs.next()) {
                    Utils.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8) + " ");
                    nRecs++;
                }
                System.out.println("Number of records:" + nRecs + "\n");
                assertTrue(nRecs > 0);
            }
        } catch (Exception ex) {
            Utils.log.error("Error on TestDB", ex);
            assertTrue(false);
        }
    }
}
