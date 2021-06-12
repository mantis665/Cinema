package com.panaskin.cinema.dao.impl;
import java.util.List;

import com.panaskin.cinema.dao.DAO;
import com.panaskin.cinema.entity.Film;
import com.panaskin.cinema.exception.DAOException;

public class FilmDAO implements DAO<Film>{

    @Override
    public boolean save(Film film) throws DAOException {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Film find(long id) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Film film) throws DAOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int delete(long id) throws DAOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Film> findAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }
}
