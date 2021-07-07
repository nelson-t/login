/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.model;

/**
 *
 * @author Administrator
 */
public class RoleResourceActionPK {

    private int roleId;
    private int resourceId;
    private int actionId;

    public RoleResourceActionPK() {
    }

    public RoleResourceActionPK(int roleId, int resourceId, int actionId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.actionId = actionId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleResourceActionPK)) {
            return false;
        }
        RoleResourceActionPK other = (RoleResourceActionPK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.resourceId != other.resourceId) {
            return false;
        }
        if (this.actionId != other.actionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idelogix.login.domain.RoleResourceActionPK[ roleId=" + roleId + ", resourceId=" + resourceId + ", actionId=" + actionId + " ]";
    }

}
