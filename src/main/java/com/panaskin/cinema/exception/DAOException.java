package com.panaskin.cinema.exception;

public class DAOException extends AppException {

    private static final long serialVersionUID = 705069958127659363L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }
    
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
