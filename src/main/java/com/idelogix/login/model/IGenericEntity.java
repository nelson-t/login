/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.model;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public interface IGenericEntity {

    public Integer getId();

    public void setId(Integer id);

    public String getName();

    public void setName(String name);

    public String getComments();

    public void setComments(String comments);

    public Date getDateCreated();

    public void setDateCreated(Date dateCreated);

    public Boolean getEnabled();

    public void setEnabled(Boolean enabled);

    @Override
    public String toString();

}
