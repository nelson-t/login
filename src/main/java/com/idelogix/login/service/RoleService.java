/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.service;

import com.idelogix.login.dao.RoleDAO;
import com.idelogix.login.model.Role;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
//Singleton
public class RoleService {

    private static final RoleService roleServiceInstance = new RoleService();
    //private Role theAppRole;
    //Can later on be change with Dependency Injection
    private final RoleDAO roleDao = RoleDAO.getInstance();

    public static RoleService getInstance() {
        return roleServiceInstance;
    }

    private RoleService() {
    }

    public Role getRole(int roleId) {
        return (Role) roleDao.getById(roleId);
    }

    public Role getRole(String roleName) {
        return (Role) roleDao.getByName(roleName);
    }

    public boolean deleteRole(int roleId) {
        return roleDao.delete(roleId);
    }

    public boolean updateRole(Role u) {
        return roleDao.update(u);
    }

    public boolean addRole(Role u) {
        return roleDao.add(u);
    }

    public ArrayList getAll() {
        return getAll(0, 0, null);
    }

    public ArrayList getAll(String searchString) {
        return getAll(0, 0, searchString);
    }

    public ArrayList getAll(int limit, int offset, String searchString) {
        ArrayList uList = roleDao.getAll(limit, offset, searchString);
        return uList;
    }
    
}
