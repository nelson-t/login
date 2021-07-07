/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.service;

import com.idelogix.login.dao.TypeDAO;
import com.idelogix.login.model.Type;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nelson Terrazas
 */
public class TypeService {

    private static final TypeService typeServiceInstance = new TypeService();
    //Can later on be change with Dependency Injection
    private final TypeDAO typeDao = TypeDAO.getInstance();
    //private final ITypeDAO securityDao = TypeJdbcDAO.getInstance();

    public static TypeService getInstance() {
        return typeServiceInstance;
    }

    private TypeService() {
    }
    
    public Type getType(int typeId){
        return (Type) typeDao.getById(typeId);
    }    
    
    public Type getType(String typeName) {
        return (Type) typeDao.getByName(typeName);
    }

  
    public ArrayList getAll() {
        return getAll(0, 0, null);
    }

    public ArrayList getAll(String searchString) {
        return getAll(0, 0, searchString);
    }

    public ArrayList getAll(int limit, int offset, String searchString) {
        ArrayList rList = typeDao.getAll(limit, offset, searchString);
        return rList;
    }
    
    public ArrayList<String> getAllNames(){
        ArrayList allTypes = getAll();
        ArrayList<String> names = new ArrayList<>();
        for(Object o : allTypes){
            names.add(((Type) o).getName());
        }
        return names;
    }
    
    public int getStringFieldMaxSize(String entityName, String fieldName){
        return typeDao.getStringFieldMaxSize(entityName, fieldName);
    }
    
    public boolean addType(String name, String comments, Boolean enabled) {
        Type t = new Type(null, name, comments, enabled, null);
        return addType(t);
    }    

    public boolean addType(Type t) {
        return typeDao.add(t);
    }
  
      public boolean deleteType(int typeId) {
        return typeDao.delete(typeId);
    }

    public boolean updateType(Type t) {
        return typeDao.update(t);
    }

    public boolean updateType(Integer id, String name, String comments, boolean enabled) {
        Type t = new Type(id, name, comments, enabled, null);
        return updateType(t);
    }   
    
    public DefaultTableModel getTableModel(String searchText) {
        DefaultTableModel tModel = new DefaultTableModel();
        tModel.addColumn(Props.getInstance().getTxtProps("label._Id"));      //0
        tModel.addColumn(Props.getInstance().getTxtProps("label._Name"));    //1
        tModel.addColumn(Props.getInstance().getTxtProps("label._Comments"));     //2
        tModel.addColumn(Props.getInstance().getTxtProps("label._Enabled"));      //3
        tModel.addColumn(Props.getInstance().getTxtProps("label._Date_created")); //4
        Object rowData[] = new Object[5];
        ArrayList<Type> myData = getAll(searchText);
        for (int i = 0; i < myData.size(); i++) {
            rowData[0] = myData.get(i).getId();
            rowData[1] = myData.get(i).getName();
            rowData[2] = myData.get(i).getComments();
            rowData[3] = myData.get(i).getEnabled();
            rowData[4] = myData.get(i).getDateCreated(); //Assumed date is returned in format: "yyyy-MM-dd"
            tModel.addRow(rowData);
        }
        return tModel;
    }    
}
