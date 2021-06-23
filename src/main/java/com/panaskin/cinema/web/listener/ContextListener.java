package com.panaskin.cinema.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContextListener implements ServletContextListener {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.trace("Servlet context initialiser was started");
        ServletContext ctx = sce.getServletContext();
        initDBManager();
        initCommandContainer();
        log.trace("Servlet context initialiser was finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        log.trace("Servlet context was destroyed");
    }

    private void initDBManager() {
        try {
        Class.forName("com.panaskin.cinema.db.DBManager");
        } catch (ClassNotFoundException ex) {
            log.error("Class DBManager can't be initialized");
            throw new IllegalStateException("Can't initialized DBmanager");
        }
    }

    private void initCommandContainer() {
        try {
            Class.forName("com.panaskin.cinema.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            log.error("Class CommandContainder can't be initialized");
            throw new IllegalStateException("Can't initialized CommandContainder");
        }
    }
}
