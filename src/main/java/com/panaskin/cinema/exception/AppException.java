package com.panaskin.cinema.exception;

public class AppException extends Exception {

    private static final long serialVersionUID = -4976469478401068722L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
