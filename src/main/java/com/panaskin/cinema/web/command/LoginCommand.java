package com.panaskin.cinema.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.panaskin.cinema.Path;
import com.panaskin.cinema.dao.impl.UserDAO;
import com.panaskin.cinema.entity.User;
import com.panaskin.cinema.exception.AppException;
import com.panaskin.cinema.exception.message.ErrMessage;

public class LoginCommand extends Command {
    UserDAO userDAO;
    private static final long serialVersionUID = -7759075347625345914L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        return authorisation(request, response);
    }

    public String authorisation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        String login = null;
        String password = null;
        login = request.getParameter("login");
        password = request.getParameter("password");
        if (userDataValidation(login, password)) {
            throw new AppException(ErrMessage.ERR_INVALID_USER_DATA);
        }
        userDAO = UserDAO.getInstance();
        User user = userDAO.findUserByLogin(login);
        if (user == null || !(user.getPassword().equals(password))) {
            throw new AppException(ErrMessage.ERR_INVALID_USER_DATA);
        }
        request.getSession().setAttribute("user", user);
        return Path.INDEX_PAGE;
    }

    private boolean userDataValidation(String login, String password) {
        if (login == null || login.isEmpty()) {
            return true;
        }
        if (password == null || password.isEmpty()) {
            return true;
        }
        return false;
    }

}
