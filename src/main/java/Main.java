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

import com.idelogix.login.dao.ConnectionFactory;
import com.idelogix.login.dao.DBUtils;
import com.idelogix.login.service.Utils;
import com.idelogix.login.ui.InitDbUI;
import com.idelogix.login.ui.LoginUI;

/**
 *
 * @author Nelson Terrazas
 */
public class Main {

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException e) {
            Utils.log.error(e);
        }
        if (!DBUtils.isDatabaseConection()) {
            Utils.print("Error conecting to the database. Check URL and othet database parameter in the app.properties file.");
        } else if (DBUtils.tableExists(ConnectionFactory.getInstance().getConnection(), "user")) {
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> {
                new LoginUI().setVisible(true);
            });
        } else {
            Utils.print("Tables will be created...");
            new InitDbUI(null, true).setVisible(true);
        }
    }
}
