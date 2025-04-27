package com.ft.fin_track.database;

import java.sql.*;

public class UserDAO {

    public static boolean addUser(User user) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO Users (username, password, date_created) VALUES (?, ? ,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setTimestamp(3, user.getTimestamp());
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public static User getUserById(int id) {
        // Logic to retrieve user from the database by ID
        return null;
    }
}
