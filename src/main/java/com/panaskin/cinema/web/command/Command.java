package com.panaskin.cinema.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panaskin.cinema.exception.AppException;

public abstract class Command implements Serializable {

    private static final long serialVersionUID = -8363542858772208670L;

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
