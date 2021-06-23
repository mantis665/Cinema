package com.panaskin.cinema.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.panaskin.cinema.exception.message.ErrMessage;

public class DBUtils {
    private static final Logger log = LogManager.getLogger(DBUtils.class);

    private DBUtils() {
    }

    public static void connectionClose(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                log.error(ErrMessage.ERR_CONNECTION_CLOSE, ex);
            }
        }
    }

    public static void resSetClose(ResultSet resSet) {
        if (resSet != null) {
            try {
                resSet.close();
            } catch (SQLException ex) {
                log.error(ErrMessage.ERR_RESULT_SET_CLOSE, ex);
            }
        }
    }

    public static void preparedStmtClose(PreparedStatement psmt) {
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException ex) {
                log.error(ErrMessage.ERR_PREPARED_STATEMENT_CLOSE, ex);
            }
        }
    }

    public static void connectionRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            log.error(ErrMessage.ERR_CONNECTION_ROLLBACK, ex);
        }
    }
}
