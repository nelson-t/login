/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.data;

import com.idelogix.login.service.UserService;
import com.idelogix.login.dao.UserDAO;
import com.idelogix.login.service.Props;
import com.idelogix.login.service.Utils;
import com.idelogix.login.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author Administrator
 */
//@TestMethodOrder(OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Test
    @Order(1)
    public void testProps() throws SQLException {
        System.out.println("01-Load props:");
        String prop = Props.getInstance().getAppProps("description");
        assertTrue(prop.equals("Application properties"));
        System.out.println("01-END Load props:");
    }
    /**
     * Test of add method, of class UserDAO.
     */
    @Test
    @Order(10)
    public void testAdd() {
        //First delete if exist
        User user = UserService.getInstance().getUser("nelsonF");
        
        if(user!=null)
            UserDAO.getInstance().delete(user.getId());
        //Add
        System.out.println("10-add:");
        user = new User(0,"nelsonF","Prueba", false, null, "123" );
        assertEquals(UserDAO.getInstance().add(user),true);
        System.out.println("10:"+user.toString());
    }

    /**
     * Test of update method, of class UserDAO.
     */
    @Test
    @Order(20)
    public void testUpdate() {
        System.out.println("20-update:");
        User user = UserService.getInstance().getUser("nelsonF");
        System.out.println("INICIAL:"+user.toString());

        user.setPassword("abc");
        user.setEnabled(true);
        assertEquals(UserDAO.getInstance().update(user), true);
        
        User user2 = UserService.getInstance().getUser(user.getId());
        assertEquals(user.getPassword(),user2.getPassword());
        System.out.println("FINAL  :"+user.toString());
        
    }
  
    @Test
    @Order(25)
    public void testPasswords() {
        System.out.println("25-passwords:");
        User user = (User)UserDAO.getInstance().getByName("nelsonF");
        //Set encripted password
        assertTrue(UserService.getInstance().setUserPassword(user.getId(), null, "123"));
        user = UserService.getInstance().getUser("nelsonF");
        System.out.println(user);
        assertTrue(user.getPassword().equals(Utils.crypt("123")));
        //Change password
        assertTrue(UserService.getInstance().setUserPassword(user.getId(), "123", "abc"));
        user = UserService.getInstance().getUser("nelsonF");
        System.out.println(user);
    
    }
    
    

    /**
     * Test of get method, of class UserDAO.
     */
    @Test
    @Order(30)
    public void testGets() {
        System.out.println("30a-get by name:");
        String name = "nelsonF";
        User user = UserService.getInstance().getUser(name);
        assertEquals(user.getName(),"nelsonF");
        System.out.println(name+" -> "+user.toString());

        System.out.println("30b-get by id:");
        User user2 = UserService.getInstance().getUser(user.getId());
        assertEquals(user2.getName(),"nelsonF");
        System.out.println(user.getId()+" -> "+user2.toString());
    }
  
    /**
     * Test of getAll method, of class UserDAO.
     */
    @Test
    @Order(40)
    public void testGetAll_0args() {
        System.out.println("40-getAll:");
        ArrayList<User> result = UserService.getInstance().getAll();
        assertTrue (result.size()>0);
        System.out.println(result.toString());
    }

    /**
     * Test of getAll method, of class UserDAO.
     */
    @Test
    @Order(50)
    public void testGetAll_int_int() {
        System.out.println("50-getAll offset:");
        int limit = 2;
        int offset = 0;
        ArrayList<User> result = UserService.getInstance().getAll(limit, offset, null);
        assertTrue (result.size()>0);
        System.out.println(result.toString());

    }

    @Test
    @Order(55)
    public void testGetTableAll() {
    System.out.println("55-getTableAll:");
        DefaultTableModel table = UserService.getInstance().getTableModel(null);
        assertTrue (table.getRowCount()>0);
        System.out.println(table.toString());
        System.out.println(table.getValueAt(1, 1));
    }    

    /**
     * Test of delete method, of class UserDAO.
     */
    @Test
    @Order(60)
    public void testDelete() {
        System.out.println("60-delete:");
        User user = UserService.getInstance().getUser("nelsonF");
        Boolean result = UserService.getInstance().deleteUser(user.getId());
        assertTrue(result);
        System.out.println("deleted -> "+result);
        ArrayList<User> aResult = UserService.getInstance().getAll();
        System.out.println("After delete: "+aResult.toString());
    }    
}
