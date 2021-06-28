/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.service;

import com.idelogix.login.dao.ActionDAO;
import com.idelogix.login.dao.ResourceDAO;
import com.idelogix.login.dao.RoleDAO;

/**
 *
 * @author Nelson Terrazas
 */
public class ResourceService {

    private static final ResourceService resourceServiceInstance = new ResourceService();
    //Can later on be change with Dependency Injection
    private final ResourceDAO resourceDao = ResourceDAO.getInstance();
    //private final IResourceDAO securityDao = ResourceJdbcDAO.getInstance();

    public static ResourceService getInstance() {
        return resourceServiceInstance;
    }

    private ResourceService() {
    }

    public boolean isActionAllowed(String resourceName, String actionName, String roleName){
        int resourceId  = resourceDao.getByName(resourceName).getId();
        int actionId    = ActionDAO.getInstance().getByName(actionName).getId();
        int roleId      = RoleDAO.getInstance().getByName(roleName).getId();
        return isActionAllowed(resourceId, actionId, roleId);
    }

    public boolean isActionAllowed(int resourceId, int actionId, int roleId){
        return resourceDao.isActionAllowed(resourceId, actionId, roleId);
    }
    
}
