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
import com.panaskin.cinema.db.DBUtils;
import com.panaskin.cinema.entity.Film;
import com.panaskin.cinema.entity.Session;
import com.panaskin.cinema.exception.DAOException;
import com.panaskin.cinema.exception.DBException;
import com.panaskin.cinema.exception.message.ErrMessage;

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
    public long save(Session session) throws DAOException {
        long createdSessionId = -1;
        Connection con = null;
        PreparedStatement psmt = null;
        try {
            con = dbManager.getConnection();
            psmt = con.prepareStatement(SQLQuery.SAVE_SESSION, PreparedStatement.RETURN_GENERATED_KEYS);
            int counter = 1;
            psmt.setDate(counter++, session.getDate());
            psmt.setTime(counter++, session.getTime());
            psmt.setLong(counter++, session.getFilm().getId());
            psmt.executeUpdate();
            con.commit();
            ResultSet keys = psmt.getGeneratedKeys();
            if (keys.next()) {
                createdSessionId = keys.getLong(1);
            }
        } catch (SQLException ex) {
            DBUtils.connectionRollback(con);
            throw new DAOException(ErrMessage.ERR_SESSION_CREATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(con);
        }
        return createdSessionId;
    }

    @Override
    public Session find(long id) throws DAOException {
        Session session = null;
        Connection connect = null;
        PreparedStatement psmt = null;
        ResultSet res = null;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.FIND_SESSION_BY_ID);
            psmt.setLong(1, id);
            res = psmt.executeQuery();
            connect.commit();
            while (res.next()) {
                session = new Session();
                session.setId(res.getLong("id"));
                session.setDate(res.getDate("date"));
                session.setStart(res.getTime("start"));
                Film film = new Film();
                film.setId(res.getLong("film_id"));
                session.setFilm(film);
            }
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_RECEIVE_ALL_USERS, ex);
        } finally {
            DBUtils.resSetClose(res);
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
        return session;
    }

    @Override
    public Session update(Session session) throws DAOException {
        Connection connect = null;
        PreparedStatement psmt = null;
        try {
            int counter = 1;
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.UPDATE_SESSION);
            psmt.setDate(counter++, session.getDate());
            psmt.setTime(counter++, session.getTime());
            psmt.setLong(counter++, session.getFilm().getId());
            psmt.setLong(counter++, session.getId());
            connect.commit();
        } catch (SQLException ex) {
            DBUtils.connectionRollback(connect);
            throw new DAOException(ErrMessage.ERR_USER_UPDATE, ex);
        } finally {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);
        }
        return session;
    }

    @Override
    public void delete(long id) throws DAOException {
        Connection connect = null;
        PreparedStatement psmt = null;
        try {
            connect = dbManager.getConnection();
            psmt = connect.prepareStatement(SQLQuery.DELETE_SESSION_BY_ID);
            psmt.setInt(1, (int) id);
            psmt.executeUpdate();
            connect.commit();
        } catch (SQLException ex) {
            DBUtils.preparedStmtClose(psmt);
            DBUtils.connectionClose(connect);;
        }
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