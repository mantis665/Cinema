package com.panaskin.cinema.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.panaskin.cinema.dao.DAO;
import com.panaskin.cinema.dao.impl.sql.SQLQuery;
import com.panaskin.cinema.db.DBManager;
import com.panaskin.cinema.db.DBUtils;
import com.panaskin.cinema.entity.Ticket;
import com.panaskin.cinema.exception.DAOException;
import com.panaskin.cinema.exception.message.ErrMessage;

public class TicketDAO implements DAO<Ticket> {
    
    private DBManager dbManager;
    private static TicketDAO instance;
    
    private TicketDAO() {
        dbManager = DBManager.getInstance();
    }
    
    public static synchronized TicketDAO getInstance() {
        if (instance == null) {
            instance = new TicketDAO();
        }
        return instance;
    }

    @Override
    public long save(Ticket ticket) throws DAOException {
        long createdTicketID = -1;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = dbManager.getConnection();
            psmt = con.prepareStatement(SQLQuery.SAVE_TICKET, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            psmt.setLong(counter++, ticket.getUserId());
            psmt.setLong(counter++, ticket.getSessionId());
            psmt.executeUpdate();
            con.commit();
            ResultSet keys = psmt.getGeneratedKeys();
            if (keys.next()) {
                createdTicketID = keys.getLong(1);
            }
        } catch (SQLException ex) {
            DBUtils.connectionRollback(con);
            throw new DAOException(ErrMessage.ERR_TICKET_CREATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(con);
        }
        return createdTicketID;
    }

    @Override
    public Ticket find(long id) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ticket update(Ticket ticket) throws DAOException {
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = dbManager.getConnection();
            psmt = con.prepareStatement(SQLQuery.UPDATE_TICKET);
            int counter = 1;
            psmt.setLong(counter++, ticket.getId());
            psmt.setLong(counter++, ticket.getSessionId());
            psmt.setLong(counter++, ticket.getId());
            psmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            DBUtils.connectionRollback(con);
            throw new DAOException(ErrMessage.ERR_TICKET_UPDATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(con);
        }
        return ticket;
    }

    @Override
    public void delete(long id) throws DAOException {
        Connection connect = null;
        PreparedStatement psmt = null;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.DELETE_TICKET_BY_ID);
            psmt.setInt(1, (int) id);
            psmt.executeUpdate();
            connect.commit();
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_TICKET_UPDATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
    }

    @Override
    public List<Ticket> findAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

}
