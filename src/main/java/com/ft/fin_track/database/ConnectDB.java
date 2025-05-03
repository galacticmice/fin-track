package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;

public class ConnectDB {
    @Value("${SUPABASE_URL}")
    static String url;

    @Value("${SUPABASE_USER_IPV4}")
    static String user;

    @Value("${SUPABASE_PASSWORD}")
    static String password;

    public static Connection getConnection() {
        Connection conn = null;

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
