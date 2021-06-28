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
public class GenericEntity implements IGenericEntity {

    protected Integer id;
    protected String name;
    protected String comments;
    protected Boolean enabled;
    protected Date dateCreated;

    public GenericEntity(Integer id, String name, String comments, Boolean enabled, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.comments = comments;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + comments + ", " + enabled + ", " + dateCreated;
    }
}
