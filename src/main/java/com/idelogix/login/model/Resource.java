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
public class Resource extends GenericEntity {

    private ArrayList<Action> actionArrayList;
    private ArrayList<RoleResourceAction> roleResourceActionArrayList;
    private Type type;
    
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }    

    public Resource(Integer id, String name, String comments, Boolean enabled, Date dateCreated) {
        super(id, name, comments, enabled, dateCreated);
    }

    public ArrayList<Action> getActionArrayList() {
        return actionArrayList;
    }

    public void setActionArrayList(ArrayList<Action> actionArrayList) {
        this.actionArrayList = actionArrayList;
    }

    public ArrayList<RoleResourceAction> getRoleResourceActionArrayList() {
        return roleResourceActionArrayList;
    }

    public void setRoleResourceActionArrayList(ArrayList<RoleResourceAction> roleResourceActionArrayList) {
        this.roleResourceActionArrayList = roleResourceActionArrayList;
    }
}
