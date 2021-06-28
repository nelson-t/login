/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class User extends GenericEntity {

    private String password=null;
    private ArrayList<Role> roleArrayList=null;

    public User(Integer id, String name, String comments, Boolean enabled, Date dateCreated, String password) {
        super(id, name, comments, enabled, dateCreated);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Role> getRoleArrayList() {
        return roleArrayList;
    }

    public void setRoleArrayList(ArrayList<Role> roleArrayList) {
        this.roleArrayList = roleArrayList;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + comments + ", " + enabled + ", " + dateCreated + ", " + password;
    }
}
