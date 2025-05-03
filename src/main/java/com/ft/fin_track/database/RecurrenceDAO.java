package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecurrenceDAO {
    public static boolean addRecurrence(Recurrence recurrence) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO Recurrence (user_id, activity_id, interval_days, last_change) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recurrence.getUser_id());
            ps.setInt(2, recurrence.getActivity_id());
            ps.setInt(3, recurrence.getInterval_days());
            ps.setDate(4, recurrence.getLast_change());
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding recurring activity: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteActivity(int recurrence_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DELETE FROM Recurrence WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recurrence_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting recurring activity: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateActivity(int recurrence_id, Recurrence recurrence) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "UPDATE Recurrence SET interval_days=?, last_change=? WHERE recurrence_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recurrence.getInterval_days());
            ps.setDate(2, recurrence.getLast_change());
            ps.setInt(3, recurrence_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating recurring activity: " + e.getMessage());
            return false;
        }
    }
}
