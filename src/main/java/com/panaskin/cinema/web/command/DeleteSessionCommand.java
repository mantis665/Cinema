package com.panaskin.cinema.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panaskin.cinema.Path;
import com.panaskin.cinema.dao.impl.SessionDAO;
import com.panaskin.cinema.exception.AppException;

public class DeleteSessionCommand extends Command {

    private static final long serialVersionUID = -6307740866537839348L;
    private static SessionDAO sessionDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        return removeSession(request, response);
    }

    public String removeSession(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        sessionDAO = SessionDAO.getInstance();
        String parameter = request.getParameter("sessionId");
        if (parameter == null || parameter.isEmpty()) {
            throw new AppException("Can't find session with current id");
        }
        int id = Integer.parseInt(parameter);
        if (sessionDAO.delete(id) == 0 ) {
            throw new AppException("Can't find session with current id");
        }
        return Path.INDEX_PAGE;
    }

}
