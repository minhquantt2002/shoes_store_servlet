package com.dev4fun.dao;

import com.dev4fun.utils.BCrypt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public Connection getConnection() {
        Connection connection = null;
        try {
//            System.out.println("Connecting database ...");
            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://localhost/shoes", "root", "");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoes", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Connection error");
        }
        return connection;
    }
}
