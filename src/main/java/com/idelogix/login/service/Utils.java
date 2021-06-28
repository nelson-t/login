package com.idelogix.login.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Nelson Terrazas
 */
public class Utils {

    public static Logger log = LogManager.getLogger(Utils.class);

    public static boolean isPwdValid(String str) {
        //Here add more contraints
        int ml = Integer.parseInt(Props.getInstance().getAppProps("app.pwdMin"));
        return (ml <= str.length());
    }

    public static boolean isUNameValid(String str) {
        //Here add more contraints
        int ml = Integer.parseInt(Props.getInstance().getAppProps("app.uNameMin"));
        return (ml <= str.length());
    }

    public static String crypt(String str) {
        MessageDigest digester = null;
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            Utils.log.error(e);
            return null;
        }
        try {
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("String to encript cannot be null or zero length");
            }
        } catch (IllegalArgumentException e) {
            Utils.log.error(e);
            return null;
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

    public static int confirmDialog(JInternalFrame frame, String msg) {
        Object[] options = {Props.getInstance().getTxtProps("option.yes"), Props.getInstance().getTxtProps("option.no")};

        int opc = JOptionPane.showOptionDialog(frame,
                msg,
                Props.getInstance().getTxtProps("title.confirmAction"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        return opc;

    }
}
