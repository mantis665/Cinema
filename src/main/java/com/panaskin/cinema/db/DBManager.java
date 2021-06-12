package com.panaskin.cinema.db;

import java.sql.Connection;
import java.sql.SQLException;
import com.panaskin.cinema.exception.DBException;
import com.panaskin.cinema.exception.message.ErrMessage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {
    private static DataSource ds;

    private static DBManager instance;

    public synchronized static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            return instance;
        }
        return instance;
    }
    
    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/Cinema");
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot init DBManager");
        }
    }

    public Connection getConnection() throws DBException {
        Connection connect = null;
        try {
            connect = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(ErrMessage.ERR_CONNECTION_FROM_DB_MANAGER, e);
        }
        return connect;
    }
}