package com.panaskin.cinema.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.panaskin.cinema.Path;
import com.panaskin.cinema.exception.AppException;

public class LogoutCommand extends Command {
    private static final long serialVersionUID = 2880925886392947627L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        return removeUserFromSession(request, response);
    }
    
    public String removeUserFromSession (HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        return Path.INDEX_PAGE;
    }

}
