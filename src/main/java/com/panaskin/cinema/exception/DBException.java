package com.panaskin.cinema.exception;

public class DBException extends DAOException {

    private static final long serialVersionUID = 705069958127659363L;

    public DBException() {
        super();
    }

    public DBException(String message) {
        super(message);
    }
    
    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
