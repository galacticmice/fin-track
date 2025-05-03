package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecurrenceDAO {

    /**
     * Get all recurrences for a user
     * @param user_id the ID of the user
     * @return a list of Recurrence objects
     */
    public static List<Recurrence> getRecurrencesForUser(int user_id) {
        List<Recurrence> recurrences = new ArrayList<>();

        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT r.*, a.description, a.amount, a.activity_type, c.description as \"category\" " +
                           "FROM \"Recurrence\" r " +
                           "JOIN \"Activity\" a ON r.activity_id = a.activity_id " +
                           "JOIN \"Category\" c ON a.category_id = c.category_id " +
                           "WHERE r.user_id = ? " +
                           "ORDER BY r.last_change DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recurrence recurrence = new Recurrence(
                    rs.getInt("recurrence_id"),
                    rs.getInt("user_id"),
                    rs.getInt("activity_id"),
                    rs.getInt("interval_days"),
                    rs.getDate("last_change")
                );
                recurrences.add(recurrence);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error getting recurrences: " + e.getMessage());
        }

        return recurrences;
    }

    /**
     * Get a recurrence by ID
     * @param recurrence_id the ID of the recurrence to retrieve
     * @return the Recurrence object if found, null otherwise
     */
    public static Recurrence getRecurrenceById(int recurrence_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT * FROM \"Recurrence\" WHERE recurrence_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, recurrence_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Recurrence recurrence = new Recurrence(
                    rs.getInt("recurrence_id"),
                    rs.getInt("user_id"),
                    rs.getInt("activity_id"),
                    rs.getInt("interval_days"),
                    rs.getDate("last_change")
                );
                rs.close();
                ps.close();
                return recurrence;
            } else {
                rs.close();
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting recurrence by ID: " + e.getMessage());
            return null;
        }
    }
    public static boolean addRecurrence(Recurrence recurrence) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO \"Recurrence\" (user_id, activity_id, interval_days, last_change) VALUES (?, ?, ?, ?)";
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

    public static boolean deleteRecurrence(int recurrence_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DELETE FROM \"Recurrence\" WHERE recurrence_id = ?";
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

    public static boolean updateRecurrence(int recurrence_id, Recurrence recurrence) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "UPDATE \"Recurrence\" SET interval_days=?, last_change=? WHERE recurrence_id=?";
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
