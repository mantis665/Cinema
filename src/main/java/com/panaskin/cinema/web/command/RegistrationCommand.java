package com.panaskin.cinema.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panaskin.cinema.Path;
import com.panaskin.cinema.dao.impl.UserDAO;
import com.panaskin.cinema.entity.User;
import com.panaskin.cinema.exception.AppException;
import com.panaskin.cinema.exception.message.ErrMessage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RegistrationCommand extends Command {

    private static final long serialVersionUID = -2841103532405689975L;
    private static UserDAO userDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        userDAO = UserDAO.getInstance();
        return registerNewUser(request, response);
    }

    public static String registerNewUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        userDAO = UserDAO.getInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        if (fieldVerificator(Arrays.asList(login, password, firstName, lastName))) {
            throw new AppException(ErrMessage.ERR_INCORRECT_FIELDS_OF_USER);
        }
        User user = userDAO.findUserByLogin(login);
        if (user != null) {
            throw new AppException("User with current login exists");
        }
        user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        String forward = Path.DEFAULT_PAGE;
        System.out.println(user);
        userDAO.save(user);
        return forward;
    }

    public static boolean fieldVerificator(List<String> fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}