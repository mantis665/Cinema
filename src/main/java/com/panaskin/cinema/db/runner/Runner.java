package com.panaskin.cinema.db.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.panaskin.cinema.dao.impl.sql.SQLQuery;
import com.panaskin.cinema.entity.User;

public class Runner {
    private static final String LOGIN = "root";
    private static final String PASSWORD = "user";
    private static final String DB_PATH = "jdbc:mysql://127.0.0.1:3306/cinema";

    public static void main(String[] args) throws SQLException {
        List<User> users = null;
        User user = createUser();
        long createdUserId = -1;
        
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            users = new ArrayList<>();
            connection = DriverManager.getConnection(DB_PATH, LOGIN, PASSWORD);
            psmt = connection.prepareStatement(SQLQuery.SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            psmt.setString(counter++, user.getLogin());
            psmt.setString(counter++, user.getPassword());
            psmt.setString(counter++, user.getFirstName());
            psmt.setString(counter++, user.getLastName());
            psmt.executeUpdate();
            ResultSet keys = psmt.getGeneratedKeys();
            if (keys.next()) {
                createdUserId = keys.getLong(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(createdUserId);
    }
    
    public static User createUser() {
        User user = new User();
        user.setLogin("Mantis22123");
        user.setPassword("rerere");
        user.setFirstName("Gennadiy");
        user.setLastName("Karpan");
        return user;
    }
}
