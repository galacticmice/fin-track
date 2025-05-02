package com.ft.fin_track.database;

import java.sql.*;

public class HistoryDAO {
    public static boolean insertOrUpdateHistory(History history) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO History (user_id, month_year, budget) " +
                    "VALUES (?, ?, ?) " +
                    "ON CONFLICT (user_id, month_year) DO UPDATE SET budget = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, history.getUser_id());
            ps.setDate(2, history.getMonth_year());
            ps.setDouble(3, history.getBudget());
            ps.setDouble(4, history.getBudget());

            int rowsAffected = ps.executeUpdate();
            ps.close();
            conn.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting/updating history: " + e.getMessage());
            return false;
        }
    }

    public static Double getLatestBudget(int user_id, Date referenceDate) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT budget FROM History " +
                    "WHERE user_id = ? AND month_year <= ? " +
                    "ORDER BY month_year DESC " +
                    "LIMIT 1";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setDate(2, referenceDate);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double budget = rs.getDouble("budget");
                rs.close();
                ps.close();
                conn.close();
                return budget;
            } else {
                rs.close();
                ps.close();
                conn.close();
                return null; // No budget found in history, shouldn't get to this point
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving latest budget: " + e.getMessage());
            return null;
        }
    }
}
