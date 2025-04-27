package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection getConnection() {
        Connection conn = null;
        String url = System.getenv("POSTGRES_URL");
        String user = System.getenv("POSTGRES_USER");
        String password = System.getenv("POSTGRES_PASSWORD");

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Connection failed: " + ex.getMessage());
            throw new RuntimeException(ex);
        }

        System.out.println("Connected to the database");
        return conn;
    }
}
