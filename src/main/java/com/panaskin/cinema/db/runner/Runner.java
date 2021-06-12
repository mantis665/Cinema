package com.panaskin.cinema.db.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.panaskin.cinema.dao.impl.sql.SQLQuery;
import com.panaskin.cinema.entity.User;

public class Runner {
    private static final String LOGIN = "root";
    private static final String PASSWORD = "user";
    private static final String DB_PATH = "jdbc:mysql://127.0.0.1:3306/cinema";

    public static void main(String[] args) throws SQLException {
        String query = "SELECT * FROM user";
        String query2 = "SELECT * FROM user WHERE login = ?";
        String param = "gena";
        List<User> users = null;
        User user = new User();
        user.setLogin("Mantis66231");
        user.setPassword("rerere");
        user.setFirstName("Gennadiy");
        user.setLastName("Karpan");
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            users = new ArrayList<>();
            connection = DriverManager.getConnection(DB_PATH, LOGIN, PASSWORD);
            psmt = connection.prepareStatement(SQLQuery.SAVE_USER);
            int counter = 1;
            psmt.setString(counter++, user.getLogin());
            psmt.setString(counter++, user.getPassword());
            psmt.setString(counter++, user.getFirstName());
            psmt.setString(counter++, user.getLastName());
            psmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        for (User myUser : users) {
            System.out.println(myUser);
        }
    }
}
