package com.panaskin.cinema.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.panaskin.cinema.Path;
import com.panaskin.cinema.dao.impl.SessionDAO;
import com.panaskin.cinema.dao.impl.TicketDAO;
import com.panaskin.cinema.entity.Ticket;
import com.panaskin.cinema.entity.User;
import com.panaskin.cinema.exception.AppException;
import com.panaskin.cinema.exception.DAOException;
import com.panaskin.cinema.exception.message.ErrMessage;

public class BookingCommand extends Command {

    private static final long serialVersionUID = 5325807615429950717L;
    private static final Logger log = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        return bookingSession(request, response);
    }

    private String bookingSession(HttpServletRequest request, HttpServletResponse response) throws AppException {
        log.trace("I'm in =>> " + this.getClass().getName());
        User user = (User) request.getSession().getAttribute("user");
        long sessionId = Long.parseLong(request.getParameter("sessionId"));
        TicketDAO ticketDAO = TicketDAO.getInstance();
        Ticket ticket = new Ticket();
        log.trace(user.getId());
        ticket.setSessionId(sessionId);
        ticket.setUserId(user.getId());
        try {
            ticketDAO.save(ticket);
        } catch (DAOException ex) {
            throw new AppException(ErrMessage.ERR_SESSION_BOOKING);
        }
        return Path.SESSION_SUCCESSFUL_PAGE;
    }
}
