package com.panaskin.cinema.dao.impl.sql;

public class SQLQuery {
    //User queries
    public static final String SAVE_USER = "INSERT INTO user(login, password, first_name, last_name) VALUES (?, ?, ?, ?)";
    public static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE id= ?";
    public static final String UPDATE_USER = "UPDATE user SET login = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    
    //Session queries
    public static final String SAVE_SESSION = "INSERT INTO session(date, start, film_id) VALUES (?, ?, ?)";
    public static final String FIND_SESSION_BY_ID = "SELECT * FROM session WHERE id= ?";
    public static final String UPDATE_SESSION = "UPDATE sesssion set date = ?, start = ?, film_id = ? WHERE id = ?";
    public static final String DELETE_SESSION_BY_ID = "DELETE FROM session WHERE id = ?";
    public static final String GET_ALL_SESSIONS = "SELECT * FROM session";
    
    //Ticket queries
    public static final String SAVE_TICKET = "INSERT INTO ticket(seat_number, session_id) VALUES (?, ?)";
    public static final String FIND_TICKET_BY_ID = "SELECT * FROM ticket WHERE id= ?";
    public static final String UPDATE_TICKET = "UPDATE ticket SET seat_number = ?, session_id = ? WHERE id = ?";
    public static final String DELETE_TICKET_BY_ID = "DELETE FROM ticket WHERE id = ?";
    public static final String GET_ALL_TICKETS = "SELECT * FROM ticket";
    
    //Film queries
    public static final String SAVE_FILM = "INSERT INTO film(name) VALUES (?)";
    public static final String FIND_FILM_BY_ID = "SEELECT * FROM film WHERE id = ?";
    public static final String UPDATE_FILM = "UPDATE film SET name = ? WHERE id = ?";
    public static final String DELETE_FILM_BY_ID = "DELETE FROM film WHERE id = ?";
    public static final String GET_ALL_FILMS = "SELECT * FROM film";
    public static final String GET_SESSIONS_JOIN_FILMS_BY_ID_ORDER_BY_DATE_BY_TIME = "SELECT * FROM session LEFT JOIN film ON session.film_id = film.id WHERE date >= CURDATE() AND date <= CURDATE() + INTERVAL 6 DAY;";
}
