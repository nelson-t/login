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
 * @author Nelson Terrazas
 */
public class Role extends GenericEntity {

    private ArrayList<User> userArrayList;
    private ArrayList<RoleResourceAction> roleResourceActionArrayList;

    public Role(Integer id, String name, String comments, Boolean enabled, Date dateCreated) {
        super(id, name, comments, enabled, dateCreated);
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<RoleResourceAction> getRoleResourceActionArrayList() {
        return roleResourceActionArrayList;
    }

    public void setRoleResourceActionArrayList(ArrayList<RoleResourceAction> roleResourceActionArrayList) {
        this.roleResourceActionArrayList = roleResourceActionArrayList;
    }
}