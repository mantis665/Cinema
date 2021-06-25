package com.panaskin.cinema.web.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String, Command> commands;

    static {
        commands = new TreeMap<String, Command>();
        commands.put("default", new DefaultCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("authorisation", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("session-remove", new DeleteSessionCommand());
        commands.put("session-booking", new BookingCommand());
        commands.put("session-page", new SessionPreBookingCommand());
    }

    public static Command getCommand(String name) {
        return commands.get(name);
    }
}