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
