/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idelogix.login.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Nelson Terrazas
 */
public class Props {

    Properties appProps = new Properties();
    Properties txtProps = new Properties();

    String fileName = "/app.properties";
    String fileName2 = "/localtext.properties";

    private Props() {
        try {
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            appProps.load(inputStream);
            InputStream inputStream2 = getClass().getResourceAsStream(fileName2);
            txtProps.load(inputStream2);
        } catch (IOException e) {
            Utils.log.error(e);
        }
    }

    public static Props getInstance() {
        return PropsHolder.INSTANCE;
    }

    private static class PropsHolder {

        private static final Props INSTANCE = new Props();
    }

    public String getAppProps(String name) {
        return appProps.getProperty(name);
    }

    public String getTxtProps(String name) {
        if (txtProps.getProperty(name) == null) {
            return name+"*";
        } else {
            return txtProps.getProperty(name);
        }
    }
}
