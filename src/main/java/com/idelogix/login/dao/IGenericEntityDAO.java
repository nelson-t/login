/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.dao;

import java.util.ArrayList;

/**
 *
 * @author Nelson Terrazas
 * @date Jun 2020
 * @param <T>
 */
public interface IGenericEntityDAO<T> {

    T getById(Integer id);

    T getByName(String name);

    ArrayList<T> getAll();

    ArrayList<T> getAll(String queryString);

    ArrayList<T> getAll(int limit, int offset, String queryString);

    boolean add(T t);

    boolean update(T t);

    boolean delete(Integer id);

}
