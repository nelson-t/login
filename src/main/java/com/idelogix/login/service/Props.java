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
