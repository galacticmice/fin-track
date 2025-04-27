package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivityDAO {
    public static boolean addActivity(Activity activity) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO Activity (date_time, user_id, category_id, amount, description, recur_days) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }
}
