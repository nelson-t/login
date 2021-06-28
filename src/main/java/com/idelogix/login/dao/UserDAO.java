package com.idelogix.login.dao;

import com.idelogix.login.model.GenericEntity;
import com.idelogix.login.model.Role;
import com.idelogix.login.model.User;
import com.idelogix.login.service.RoleService;
import com.idelogix.login.service.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson Terrazas
 */
public class UserDAO extends GenericEntityDAO {

    //Singleton implementation
    private static final UserDAO userJdbcDAOInstance = new UserDAO();
    private final String queryAdd = "INSERT INTO user(name,comments,enabled) VALUES(?,?,?)";
    private final String queryUpdate = "UPDATE user SET name=?, comments=?, enabled=? WHERE id=?";
    private final String queryDelete = "DELETE FROM user WHERE id = ?";
    private final String queryGetById = "SELECT * FROM user where id = ?";
    private final String queryGetByName = "SELECT * FROM user where name = ?";
    private final String queryGetAll = "SELECT * FROM user ";
    private final String queryUpdatePassword = "UPDATE user SET password=? WHERE id=?";
    private final String queryGetUserRoles = "SELECT role.* \n"
            + "	  FROM user, role, user_role \n"
            + "    WHERE user.id=user_role.user_id AND role.id=user_role.role_id AND user.id= ? ";
    private final String queryDeleteUserRoles = "DELETE FROM user_role WHERE user_id = ? ";
    private final String queryAddUserRole = "INSERT INTO user_role() VALUES (?,?)";

    public static UserDAO getInstance() {
        return userJdbcDAOInstance;
    }

    private UserDAO() {
        super();
        super.setQueryStrings(queryAdd, queryUpdate, queryDelete, queryGetById, queryGetByName, queryGetAll);
    }

    @Override
    public User getById(Integer id) {
        User u = (User) super.getById(id);
        if (u != null) {
            u = getUserRoles(u);
        }
        return u;
    }

    @Override
    public User getByName(String name) {
        User u = (User) super.getByName(name);
        if (u != null) {
            u = getUserRoles(u);
        }
        return u;
    }

    public boolean deleteUserRoles(Integer id) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryDeleteUserRoles);
            ptmt.setInt(1, id);
            success = (ptmt.executeUpdate() > 0);
            System.out.println("Deleted user's roles");
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.log.error("Error on UserDAO", e);
            } catch (Exception e) {
                Utils.log.error(e);
            }
        }
        return success;
    }

    public boolean updateUserRoles(Integer user_id, ArrayList<String> roleList) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            int updateCount = 0;
            for (String r : roleList) {
                System.out.println(r);
                Role role = RoleService.getInstance().getRole(r);
                if (!(role == null)) {
                    ptmt = connection.prepareStatement(queryAddUserRole);
                    ptmt.setInt(1, user_id);
                    ptmt.setInt(2, role.getId());
                    updateCount = updateCount + (ptmt.executeUpdate());
                }
            }
            System.out.println("Created user's roles:" + updateCount);
            success = (updateCount > 0);
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.log.error("Error on UserDAO", e);
            } catch (Exception e) {
                Utils.log.error(e);
            }
        }
        return success;
    }

    public User getUserRoles(User u) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        ArrayList<Role> roleList;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryGetUserRoles);
            //For "Where" clause
            ptmt.setInt(1, u.getId());
            resultSet = ptmt.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();
            roleList = new ArrayList<>(columnCount);

            while (resultSet.next()) {
                //Tricky
                Role r = (Role) RoleDAO.getInstance().getEntityFromRS(resultSet);
                roleList.add(r);
            }
            u.setRoleArrayList(roleList);
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
        } finally {
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
                Utils.log.error("Error on UserDAO", e);
            } catch (Exception e) {
                Utils.log.error(e);
            }
        }
        return u;
    }

    public boolean add(User user) {
        super.add(user);
        String password = user.getPassword();
        user = (User) super.getByName(user.getName());
        user.setPassword(password);
        return (updatePassword(user));
    }

    public boolean update(User user) {
        return (super.update(user) && updatePassword(user));
    }

    @Override
    public GenericEntity getEntityFromRS(ResultSet resultSet) {
        GenericEntity theNewUser = null;
        try {
            theNewUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getDate(5), resultSet.getString(6));
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
        }
        return theNewUser;
    }

    public boolean updatePassword(User u) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement ptmt = null;
        try {
            connection = super.getConnection();
            ptmt = connection.prepareStatement(queryUpdatePassword);
            ptmt.setString(1, u.getPassword());
            //For "Where" clause
            ptmt.setInt(2, u.getId());
            success = (ptmt.executeUpdate() > 0);
            System.out.println("Updated password");
        } catch (SQLException e) {
            Utils.log.error("Error on UserDAO", e);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Utils.log.error("Error on UserDAO", e);
            } catch (Exception e) {
                Utils.log.error(e);
            }
        }
        return success;
    }
}
