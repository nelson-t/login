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

package com.idelogix.login.model;


/**
 *
 * @author Nelson Terrazas
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
