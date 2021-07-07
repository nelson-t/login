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
public class RoleResourceAction {

    protected RoleResourceActionPK roleResourceActionPK;
    private Action action;
    private Resource resource;
    private Role role;

    public RoleResourceAction() {
    }

    public RoleResourceAction(RoleResourceActionPK roleResourceActionPK) {
        this.roleResourceActionPK = roleResourceActionPK;
    }

    public RoleResourceAction(int roleId, int resourceId, int actionId) {
        this.roleResourceActionPK = new RoleResourceActionPK(roleId, resourceId, actionId);
    }

    public RoleResourceActionPK getRoleResourceActionPK() {
        return roleResourceActionPK;
    }

    public void setRoleResourceActionPK(RoleResourceActionPK roleResourceActionPK) {
        this.roleResourceActionPK = roleResourceActionPK;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleResourceAction)) {
            return false;
        }
        RoleResourceAction other = (RoleResourceAction) object;
        if ((this.roleResourceActionPK == null && other.roleResourceActionPK != null) || (this.roleResourceActionPK != null && !this.roleResourceActionPK.equals(other.roleResourceActionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idelogix.login.domain.RoleResourceAction[ roleResourceActionPK=" + roleResourceActionPK + " ]";
    }

}
