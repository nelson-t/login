/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.service;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Globals {
    static public final String VIEW_ACTION     = "view";
    static public final String EDIT_ACTION     = "edit";
    static public final String CREATE_ACTION   = "create";
    static public final String DELETE_ACTION   = "delete";
    
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
