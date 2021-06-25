package com.panaskin.cinema.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.panaskin.cinema.Path;
import com.panaskin.cinema.dao.impl.FilmDAO;
import com.panaskin.cinema.dao.impl.SessionDAO;
import com.panaskin.cinema.entity.Film;
import com.panaskin.cinema.entity.Session;
import com.panaskin.cinema.entity.User;
import com.panaskin.cinema.exception.AppException;
import com.panaskin.cinema.exception.DAOException;
import com.panaskin.cinema.web.command.util.AttributeValidator;

public class SessionPreBookingCommand extends Command {

    private static final long serialVersionUID = -2213508188583421258L;
    private static final Logger log = LogManager.getLogger(SessionPreBookingCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        // TODO Auto-generated method stub
        return getSessionDetailInformation(request, response);
    }

    private String getSessionDetailInformation(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        String forward = Path.ERROR_PAGE;
        User user = null;
        user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("errMessage", "");
            throw new AppException("You must log in to continue");
        }
        log.trace("I'm in =>> " + this.getClass().getName());
        String parametrId = request.getParameter("sessionId");
        Session session = null;
        if (!AttributeValidator.idValidate(parametrId)) {
            throw new AppException("Session id has wrong format");
        }
        long sessionId = Integer.parseInt(parametrId);
        SessionDAO sessionDAO = SessionDAO.getInstance();
        session = sessionDAO.find(sessionId);
        if (session == null) {
            throw new AppException("Can't find session with current id");
        }
        FilmDAO filmDAO = FilmDAO.getInstance();
        Film film = filmDAO.find(session.getFilm().getId());
        request.setAttribute("session", session);
        request.setAttribute("film", film);
        request.getSession().setAttribute("command", "default");
        forward = Path.SESSION_BOOKING_PAGE;
        return forward;
    }
}
