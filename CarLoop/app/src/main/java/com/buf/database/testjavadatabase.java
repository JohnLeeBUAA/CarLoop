package com.buf.database;

/**
 * Created by xin on 2016/2/20.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testjavadatabase {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "jdbc:mysql://localhost:3306/javabase";
        String username = "test";
        String password = "test1234";

        System.out.println("Connecting database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
