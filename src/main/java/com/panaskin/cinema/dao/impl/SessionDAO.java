package com.panaskin.cinema.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.panaskin.cinema.dao.DAO;
import com.panaskin.cinema.dao.impl.sql.SQLQuery;
import com.panaskin.cinema.db.DBManager;
import com.panaskin.cinema.entity.Film;
import com.panaskin.cinema.entity.Session;
import com.panaskin.cinema.exception.DAOException;
import com.panaskin.cinema.exception.DBException;

public class SessionDAO implements DAO<Session> {
    private DBManager dbManager;
    private static SessionDAO instance;

    private SessionDAO() {
        dbManager = DBManager.getInstance();
    }

    public static synchronized SessionDAO getInstance() {
        if (instance == null) {
            instance = new SessionDAO();
        }
        return instance;
    }

    @Override
    public boolean save(Session t) throws DAOException {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Session find(long id) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Session t) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public int delete(long id) throws DAOException {
        Connection connect = null;
        PreparedStatement psmt = null;
        int rowCount = 0;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.DELETE_SESSION_BY_ID);
            psmt.setInt(1, (int) id);
            rowCount = psmt.executeUpdate();
            connect.commit();
            return rowCount;
        } catch (DBException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new DAOException("PreparedStatement can't be closed", e);
            }
            try {
                connect.close();
            } catch (SQLException e) {
                throw new DAOException("Connection can't be closed", e);
            }
        }
        return rowCount;
    }

    @Override
    public List<Session> findAll() throws DAOException {
        List<Session> sessions = null;
        Connection con = null;
        ResultSet res = null;
        try {
            con = dbManager.getConnection();
            res = con.prepareStatement(SQLQuery.GET_ALL_SESSIONS).executeQuery();
            sessions = new ArrayList<>();
            while (res.next()) {
                Session session = new Session();
                Film film = new Film();
                session.setId(res.getLong("id"));
                session.setDate(res.getDate("date"));
                session.setStart(res.getTime("start"));
                film.setId(res.getLong("film_id"));
                film.setName(res.getString("name"));
                session.setFilm(film);
                sessions.add(session);
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sessions;
    }

    public Map<Date, List<Session>> weekSessionsByDate() throws DAOException {
        Map<Date, List<Session>> sessions = null;
        List<Session> sessionOfDay = null;
        Connection con = null;
        ResultSet res = null;
        Date date = null;
        try {
            con = dbManager.getConnection();
            res = con.prepareStatement(SQLQuery.GET_SESSIONS_JOIN_FILMS_BY_ID_ORDER_BY_DATE_BY_TIME).executeQuery();
            sessions = new TreeMap<>();
            while (res.next()) {
                Session session = new Session();
                Film film = new Film();
                session.setId(res.getLong("id"));
                date = res.getDate("date");
                session.setDate(date);
                session.setStart(res.getTime("start"));
                film.setId(res.getLong("film_id"));
                film.setName(res.getString("name"));
                session.setFilm(film);
                if (!sessions.containsKey(date)) {
                    sessionOfDay = new ArrayList<>();
                    sessionOfDay.add(session);
                    sessions.put(date, sessionOfDay);
                } else {
                    sessions.get(date).add(session);
                }
            }
        } catch (DBException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sessions;
    }
}