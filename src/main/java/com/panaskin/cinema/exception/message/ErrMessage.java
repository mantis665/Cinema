package com.panaskin.cinema.exception.message;

public class ErrMessage {

    private ErrMessage() {
    }
//DBUtils messages
    public static final String ERR_CONNECTION_CLOSE = "Connection can't be closed";
    public static final String ERR_RESULT_SET_CLOSE = "ResultSet can't be closed";
    public static final String ERR_PREPARED_STATEMENT_CLOSE = "PreparedStatement can't be closed";
    public static final String ERR_CONNECTION_ROLLBACK = "Rollback on connection can't be execute";
    public static final String ERR_USER_CREATE = "User can't be created";
    public static final String ERR_USER_UPDATE = "User didn't be update";
    public static final String ERR_USER_DELETE = "User can't be deleted";
    
//FilmDAO messages
    public static final String ERR_FILM_CREATE = "Film has not been created";
    public static final String ERR_FILM_UPDATE = "Film has not been updated"; 
    public static final String ERR_FILM_DELETE = "Film has not been deleted"; 
    
//SessionDAO messages
    public static final String ERR_SESSION_DELETE = "Session with current id isn't exist";
    public static final String ERR_SESSION_CREATE = "Session didn't created";

//UserDAO messages
    public static final String ERR_RECEIVE_ALL_USERS = "Users have not been created";
    public static final String ERR_FIND_USER_BY_LOGIN = "User by login didn't receive";
    
    public static final String LOG_COMMAND_START = "Command starts";
    
    public static final String ERR_CONNECTION_FROM_DB_MANAGER = "Can't get connection from DBManager";

    public static final String ERR_INCORRECT_FIELDS_OF_USER = "Please, fill all necessary field.";
    

    
    public static final String ERR_INVALID_USER_DATA = "User's login or password can't be empty";
    public static final String ERR_INVALID_USER_LOGIN = "User with this login doesn't exist";
    
    
    
    //TicketDAO messages
    public static final String ERR_TICKET_CREATE = "User has not been created";
    public static final String ERR_TICKET_UPDATE = "User has not been update";
    public static final String ERR_TICKET_DELETE = "User has not been deleted";
}
