package org.padaria.paokentinho.models.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/paokentinho";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static Connection conn = null;

    static Connection getCurrentConnection() throws SQLException {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
