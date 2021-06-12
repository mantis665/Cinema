package com.panaskin.cinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.panaskin.cinema.dao.DAO;
import com.panaskin.cinema.dao.impl.sql.SQLQuery;
import com.panaskin.cinema.db.DBManager;
import com.panaskin.cinema.db.DBUtils;
import com.panaskin.cinema.entity.User;
import com.panaskin.cinema.exception.DAOException;
import com.panaskin.cinema.exception.message.ErrMessage;

public class UserDAO implements DAO<User> {

    private DBManager dbManager;
    private static UserDAO instance;

    private UserDAO() {
        dbManager = DBManager.getInstance();
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public boolean save(User user) throws DAOException {
        boolean userIs = false;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = dbManager.getConnection();
            psmt = con.prepareStatement(SQLQuery.SAVE_USER);
            int counter = 1;
            psmt.setString(counter++, user.getLogin());
            psmt.setString(counter++, user.getPassword());
            psmt.setString(counter++, user.getFirstName());
            psmt.setString(counter++, user.getLastName());
            userIs = psmt.execute();
            con.commit();
        } catch (SQLException ex) {
            DBUtils.connectionRollback(con);
            throw new DAOException(ErrMessage.ERR_USER_CREATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(con);
        }
        return userIs;
    }

    @Override
    public void update(User user) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public int delete(long id) throws DAOException {
        Connection connect = null;
        PreparedStatement psmt = null;
        int rowCount = 0;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.DELETE_USER_BY_ID);
            psmt.setInt(1, (int) id);
            rowCount = psmt.executeUpdate();
            connect.commit();
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_USER_DELETE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);  
        }
        return rowCount;
    }

    @Override
    public User find(long id) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = null;
        Connection connect = null;
        PreparedStatement psmt = null;
        ResultSet res = null;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.GET_ALL_USERS);
            res = psmt.executeQuery();
            connect.commit();
            users = new ArrayList<>();
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setLogin(res.getString("login"));
                user.setPassword(res.getString("password"));
                user.setFirstName(res.getString("first_name"));
                user.setLastName(res.getString("last_name"));
                users.add(user);
            }
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_RECEIVE_ALL_USERS, ex);
        } finally {
            DBUtils.resSetClose(res);
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
        return users;
    }

    public User findUserByLogin(String login) throws DAOException {
        User user = null;
        Connection connect = null;
        PreparedStatement psmt = null;
        ResultSet res = null;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.GET_USER_BY_LOGIN);
            psmt.setString(1, login);
            res = psmt.executeQuery();
            connect.commit();
            while (res.next()) {
                user = new User();
                user.setId(res.getLong("id"));
                user.setLogin(res.getString("login"));
                user.setPassword(res.getString("password"));
                user.setFirstName(res.getString("first_name"));
                user.setLastName(res.getString("last_name"));
                user.setRoleId(res.getInt("role_id"));
                user.setStatusId(res.getInt("status_id"));
            }
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_RECEIVE_ALL_USERS, ex);
        } finally {
            DBUtils.resSetClose(res);
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
        return user;
    }
}