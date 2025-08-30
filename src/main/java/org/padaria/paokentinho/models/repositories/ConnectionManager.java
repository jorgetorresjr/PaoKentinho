package org.padaria.paokentinho.models.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/paokentinho?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private static Connection conn = null;

    public static Connection getCurrentConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Driver MySQL não encontrado!", e);
            }
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexão estabelecida com o banco!");
        }
        return conn;
    }

    public static Connection getNewConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver MySQL não encontrado!", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
