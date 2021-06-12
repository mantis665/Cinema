package com.panaskin.cinema.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.panaskin.cinema.exception.DBException;
import com.panaskin.cinema.exception.message.ErrMessage;

public class DBUtils {

    private DBUtils() {
    }

    public static void connectionClose(Connection connection) throws DBException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new DBException(ErrMessage.ERR_CONNECTION_CLOSE, ex);
            }
        }
    }

    public static void resSetClose(ResultSet resSet) throws DBException {
        if (resSet != null) {
            try {
                resSet.close();
            } catch (SQLException ex) {
                throw new DBException(ErrMessage.ERR_RESULT_SET_CLOSE, ex);
            }
        }
    }

    public static void preparedStmtClose(PreparedStatement psmt) throws DBException {
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException ex) {
                throw new DBException(ErrMessage.ERR_PREPARED_STATEMENT_CLOSE, ex);
            }
        }
    }

    public static void connectionRollback(Connection connection) throws DBException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new DBException(ErrMessage.ERR_CONNECTION_ROLLBACK, ex);
        }
    }
}
