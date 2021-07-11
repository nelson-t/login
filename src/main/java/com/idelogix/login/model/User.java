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

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Nelson Terrazas
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
