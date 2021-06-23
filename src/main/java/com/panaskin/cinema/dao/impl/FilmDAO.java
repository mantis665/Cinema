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
import com.panaskin.cinema.entity.Film;
import com.panaskin.cinema.exception.DAOException;
import com.panaskin.cinema.exception.DBException;
import com.panaskin.cinema.exception.message.ErrMessage;

public class FilmDAO implements DAO<Film> {
    private DBManager dbManager;
    private static FilmDAO instance;

    private FilmDAO() {
        dbManager = DBManager.getInstance();
    }

    public static synchronized FilmDAO getInstance() {
        if (instance == null) {
            instance = new FilmDAO();
        }
        return instance;
    }

    @Override
    public long save(Film film) throws DAOException {
        long createdFilmId;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = dbManager.getConnection();
            psmt = con.prepareStatement(SQLQuery.SAVE_FILM, PreparedStatement.RETURN_GENERATED_KEYS);
            int counter = 1;
            psmt.setString(counter++, film.getName());
            createdFilmId = psmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            DBUtils.connectionRollback(con);
            throw new DAOException(ErrMessage.ERR_USER_CREATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(con);
        }
        return createdFilmId;
    }

    @Override
    public Film find(long id) throws DAOException {
        Film film = null;
        Connection connect = null;
        PreparedStatement psmt = null;
        ResultSet res = null;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.FIND_FILM_BY_ID);
            psmt.setLong(1, id);
            res = psmt.executeQuery();
            connect.commit();
            while (res.next()) {
                film = new Film();
                film.setId(res.getLong("id"));
                film.setName(res.getString("name"));
            }
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_RECEIVE_ALL_USERS, ex);
        } finally {
            DBUtils.resSetClose(res);
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
        return film;
    }

    @Override
    public Film update(Film film) throws DAOException {
        Connection connect = null;
        PreparedStatement psmt = null;
        try {
            int counter = 1;
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.UPDATE_FILM);
            psmt.setString(counter++, film.getName());
            psmt.setLong(counter++, film.getId());
            connect.commit();
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_FILM_UPDATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
        return film;
    }

    @Override
    public void delete(long id) throws DAOException {
        Connection connect = null;
        PreparedStatement psmt = null;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.DELETE_FILM_BY_ID);
            psmt.setInt(1, (int) id);
            psmt.executeUpdate();
            connect.commit();
        } catch (SQLException ex) {
            throw new DBException(ErrMessage.ERR_FILM_DELETE);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
    }

    @Override
    public List<Film> findAll() throws DAOException {
        List<Film> films = null;
        Connection con = null;
        ResultSet res = null;
        try {
            con = dbManager.getConnection();
            res = con.prepareStatement(SQLQuery.GET_ALL_SESSIONS).executeQuery();
            films = new ArrayList<>();
            while (res.next()) {
                Film film = new Film();
                film.setId(res.getLong("id"));
                film.setName(res.getString("name"));
                films.add(film);
            }
        } catch (SQLException ex) {
            throw new DBException(ErrMessage.ERR_CONNECTION_CLOSE);
        }
        return films;
    }
}