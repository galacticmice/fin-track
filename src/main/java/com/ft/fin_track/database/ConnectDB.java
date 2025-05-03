package com.ft.fin_track.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources").load();

    public static Connection getConnection() {
        Connection conn = null;

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

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
