package com.panaskin.cinema.dao.impl;

import java.util.List;

import com.panaskin.cinema.dao.DAO;
import com.panaskin.cinema.entity.Ticket;
import com.panaskin.cinema.exception.DAOException;

public class TicketDAO implements DAO<Ticket> {

    @Override
    public boolean save(Ticket t) throws DAOException {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Ticket find(long id) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Ticket t) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public int delete(long id) throws DAOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Ticket> findAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

}
