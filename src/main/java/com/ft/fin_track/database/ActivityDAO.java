package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivityDAO {
    public static boolean addActivity(Activity activity) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO Activity (entry_time, user_id, category_id, activity_type, amount, description, recur_days) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setTimestamp(1, activity.getEntryTime());
            ps.setInt(2, activity.getUser_id());
            ps.setInt(3, activity.getCategory_id());
            ps.setString(4, String.valueOf(activity.getActivity_type()));
            ps.setDouble(5, activity.getAmount());
            ps.setString(6, activity.getDescription());
            ps.setInt(7, activity.getRecur_days());
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteActivity(int activity_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DELETE FROM Activity WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, activity_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateActivity(int activity_id, Activity updated_activity) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "UPDATE Activity SET entry_time=?, user_id=?, category_id=?, type=?, amount=?, description=?, recur_days=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setTimestamp(1, updated_activity.getEntryTime());
            ps.setInt(2, updated_activity.getUser_id());
            ps.setInt(3, updated_activity.getCategory_id());
            ps.setString(4, String.valueOf(updated_activity.getActivity_type()));
            ps.setDouble(5, updated_activity.getAmount());
            ps.setString(6, updated_activity.getDescription());
            ps.setInt(7, updated_activity.getRecur_days());
            ps.setInt(8, activity_id);
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
