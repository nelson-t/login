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

package com.idelogix.login.service;

import java.util.Date;

/**
 *
 * @author Nelson Terrazas
 */
public class Globals {
    static public final String VIEW_ACTION     = "view";
    static public final String EDIT_ACTION     = "edit";
    static public final String CREATE_ACTION   = "create";
    static public final String DELETE_ACTION   = "delete";
    static public final String DATE_FORMAT = Props.getInstance().getAppProps("app.dateFormat");
    
    static int loggedUserId;
    static String loggedUserName;
    static int loggedUserRoleId;
    static String loggedUserRoleName;
    static Date loggedTime;

    public static int getLoggedUserId() {
        return loggedUserId;
    }

    public static void setLoggedUserId(int loggedUserId) {
        Globals.loggedUserId = loggedUserId;
    }

    public static String getLoggedUserName() {
        return loggedUserName;
    }

    public static void setLoggedUserName(String loggedUserName) {
        Globals.loggedUserName = loggedUserName;
    }

    public static int getLoggedUserRoleId() {
        return loggedUserRoleId;
    }

    public static void setLoggedUserRoleId(int loggedUserRoleId) {
        Globals.loggedUserRoleId = loggedUserRoleId;
    }

    public static String getLoggedUserRoleName() {
        return loggedUserRoleName;
    }

    public static void setLoggedUserRoleName(String loggedUserRoleName) {
        Globals.loggedUserRoleName = loggedUserRoleName;
    }

    public static Date getLoggedTime() {
        return loggedTime;
    }

    public static void setLoggedTime(Date loggedTime) {
        Globals.loggedTime = loggedTime;
    }

}
