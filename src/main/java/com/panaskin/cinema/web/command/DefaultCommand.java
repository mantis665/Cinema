package com.panaskin.cinema.web.command;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.panaskin.cinema.Path;
import com.panaskin.cinema.dao.impl.SessionDAO;
import com.panaskin.cinema.entity.Session;
import com.panaskin.cinema.entity.User;
import com.panaskin.cinema.entity.UserRole;
import com.panaskin.cinema.exception.DAOException;

public class DefaultCommand extends Command {
    private static final long serialVersionUID = 54729356749152145L;
    
    private static SessionDAO session;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        session = SessionDAO.getInstance();
        Map<Date, List<Session>> sessions = null;
        try {
            sessions = session.weekSessionsByDate();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("sessions", sessions);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getRoleId() == UserRole.CLIENT.getRoleId()) {
            return Path.DEFAULT_PAGE;
        }
        return Path.ADMIN_DEFAULT_PAGE;
    }
}