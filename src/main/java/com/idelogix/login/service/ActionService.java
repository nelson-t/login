/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.service;

import com.idelogix.login.dao.ActionDAO;
import com.idelogix.login.model.Action;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ActionService {
    private static final ActionService actionServiceInstance = new ActionService();
    private final ActionDAO actionDao = ActionDAO.getInstance();

    public static ActionService getInstance() {
        return actionServiceInstance;
    }

    private ActionService() {
    }
    
    public Action getAction(int actionId){
        return (Action) actionDao.getById(actionId);
    } 
    
    public Action getAction(String actionName) {
        return (Action) actionDao.getByName(actionName);
    }
  
    public ArrayList getAll() {
        return getAll(0, 0, null);
    }

    public ArrayList getAll(String searchString) {
        return getAll(0, 0, searchString);
    }

    public ArrayList getAll(int limit, int offset, String searchString) {
        ArrayList rList = actionDao.getAll(limit, offset, searchString);
        return rList;
    }
    
    public ArrayList<String> getAllNames(){
        ArrayList allActions = getAll();
        ArrayList<String> names = new ArrayList<>();
        for(Object o : allActions){
            names.add(((Action) o).getName());
        }
        return names;
    }
    
    public int getStringFieldMaxSize(String entityName, String fieldName){
        return actionDao.getStringFieldMaxSize(entityName, fieldName);
    }
    
    public boolean addAction(String name, String comments, boolean enabled, boolean allowAccess, String buttonText) {
        Action t = new Action(null, name, comments, enabled, null, allowAccess, buttonText);
        return addAction(t);
    }    

    public boolean addAction(Action t) {
        return actionDao.add(t);
    }
  
    public boolean deleteAction(int actionId) {
        return actionDao.delete(actionId);
    }

    public boolean updateAction(Integer id, String name, String comments, boolean enabled, boolean allowAccess, String buttonText) {
        Action t = new Action(id, name, comments, enabled, null, allowAccess, buttonText);
        return updateAction(t);
    }   

    public boolean updateAction(Action t) {
        return actionDao.update(t);
    }
    
    public DefaultTableModel getTableModel(String searchText) {
        DefaultTableModel tModel = new DefaultTableModel();
        tModel.addColumn(Props.getInstance().getTxtProps("label._Id"));      //0
        tModel.addColumn(Props.getInstance().getTxtProps("label._Name"));    //1
        tModel.addColumn(Props.getInstance().getTxtProps("label._Comments"));     //2
        tModel.addColumn(Props.getInstance().getTxtProps("label._Enabled"));      //3
        tModel.addColumn(Props.getInstance().getTxtProps("label._Date_created")); //4
        tModel.addColumn(Props.getInstance().getTxtProps("label._Allow_Access"));      //5
        tModel.addColumn(Props.getInstance().getTxtProps("label._Button_text"));     //6
        
        Object rowData[] = new Object[7];
        ArrayList<Action> myData = getAll(searchText);
        for (int i = 0; i < myData.size(); i++) {
            rowData[0] = myData.get(i).getId();
            rowData[1] = myData.get(i).getName();
            rowData[2] = myData.get(i).getComments();
            rowData[3] = myData.get(i).getEnabled();
            rowData[4] = myData.get(i).getDateCreated(); //Assumed date is returned in format: "yyyy-MM-dd"
            rowData[5] = myData.get(i).getAllowAccess();            
            rowData[6] = myData.get(i).getButtonText();            
            tModel.addRow(rowData);
        }
        return tModel;
    } 
}
