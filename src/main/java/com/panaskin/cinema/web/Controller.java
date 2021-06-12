package com.panaskin.cinema.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panaskin.cinema.Path;
import com.panaskin.cinema.exception.AppException;
import com.panaskin.cinema.web.command.Command;
import com.panaskin.cinema.web.command.CommandContainer;

public class Controller extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = -9114376076104812954L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            commandName = "default";
        }
        Command command = CommandContainer.getCommand(commandName);
        String forward = Path.ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (AppException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }
}