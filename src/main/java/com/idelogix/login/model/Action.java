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
public class Action extends GenericEntity {

    private ArrayList<Resource> resourceArrayList;
    private ArrayList<RoleResourceAction> roleResourceActionArrayList;

    public Action(Integer id, String name, String comments, Boolean enabled, Date dateCreated) {
        super(id, name, comments, enabled, dateCreated);
    }

    public ArrayList<Resource> getResourceArrayList() {
        return resourceArrayList;
    }

    public void setResourceArrayList(ArrayList<Resource> resourceArrayList) {
        this.resourceArrayList = resourceArrayList;
    }

    public ArrayList<RoleResourceAction> getRoleResourceActionArrayList() {
        return roleResourceActionArrayList;
    }

    public void setRoleResourceActionArrayList(ArrayList<RoleResourceAction> roleResourceActionArrayList) {
        this.roleResourceActionArrayList = roleResourceActionArrayList;
    }

}
