package com.panaskin.cinema.dao.impl.sql;

public class SQLQuery {
    //User query
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    public static final String SAVE_USER = "INSERT INTO user(login, password, first_name, last_name) VALUES (?, ?, ?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    
    //Session query
    public static final String GET_ALL_SESSIONS = "SELECT * FROM session";
    public static final String DELETE_SESSION_BY_ID = "DELETE FROM session WHERE id = ?";
    
    public static final String GET_SESSIONS_JOIN_FILMS_BY_ID_ORDER_BY_DATE_BY_TIME = "SELECT * FROM session LEFT JOIN film ON session.film_id = film.id WHERE date >= CURDATE() AND date <= CURDATE() + INTERVAL 6 DAY;";
}
