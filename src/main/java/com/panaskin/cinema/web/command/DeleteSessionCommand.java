package com.panaskin.cinema.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.panaskin.cinema.Path;
import com.panaskin.cinema.dao.impl.SessionDAO;
import com.panaskin.cinema.exception.AppException;
import com.panaskin.cinema.exception.message.ErrMessage;

public class DeleteSessionCommand extends Command {
    private static final long serialVersionUID = -6307740866537839348L;
    public static final Logger log = LogManager.getLogger(DeleteSessionCommand.class);
    private static SessionDAO sessionDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        return removeSession(request, response);
    }

    public String removeSession(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        log.trace("I'm in =>> " + this.getClass().getName());
        sessionDAO = SessionDAO.getInstance();
        String parameter = request.getParameter("sessionId");
        if (parameter == null || parameter.isEmpty()) {
            log.error(ErrMessage.ERR_SESSION_DELETE);
            throw new AppException(ErrMessage.ERR_SESSION_DELETE);
        }
        int id = Integer.parseInt(parameter);
        sessionDAO.delete(id);
        
        return Path.INDEX_PAGE;
    }

}
