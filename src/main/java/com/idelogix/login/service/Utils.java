package com.idelogix.login.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Object[] options = {Props.getInstance().getTxtProps("option._Yes"), Props.getInstance().getTxtProps("option._No")};
        int opc = JOptionPane.showOptionDialog(frame,
                msg,
                Props.getInstance().getTxtProps("msg._Confirm_Selected_Action"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return opc;
    }

    public static void tryCloseDB(Connection connection, PreparedStatement ptmt, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ptmt != null) {
                ptmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Utils.log.error("Error on DAO / Utils", e);
        } catch (Exception e) {
            Utils.log.error(e);
        }
    }

    public static String[] getUniqueColumnValuesFrom2DArray(String[][] arr, int column) {
        String[] newArray = new String[arr.length];
        for (int row = 0; row < arr.length; row++) {
            newArray[row] = arr[row][column];
        }
        String[] unique = Arrays.stream(newArray).distinct().toArray(String[]::new);
        return unique;
    }

    public static String[] getStringsNotInList(String[] allStrings, String[] subSet) {
        List<String> allArrayList = new ArrayList(Arrays.asList(allStrings));
        List<String> notInList = new ArrayList<>();
        for (int i = 0; i < allArrayList.size(); i++) {
            int index = -1;
            for (int j = 0; j < subSet.length; j++) {
                if (subSet[j].equals(allArrayList.get(i))) {
                    index = j;
                }
            }
            if (index == -1) {
                notInList.add(allArrayList.get(i));
            }
        }
        String arr[] = stringListToArray(notInList);
        return arr;
    }

    public static String[] stringListToArray(List<String> sList) {
        return Arrays.stream(sList.toArray()).toArray(String[]::new);
    }

    public static List<String> stringArrayToList(String[] sArray) {
        return Arrays.asList(sArray);
    }

    public static void print(String s) {
        System.out.println(s);
    }

}
