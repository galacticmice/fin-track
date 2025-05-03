package com.ft.fin_track.database;

import java.sql.*;

public class HistoryDAO {
    /**
     * attempt to insert budget for current month. if entry for current month already exists, update it.
     * @param history object containing user_id, month_year, budget
     * @return true if successful, false otherwise
     */
    public static boolean insertOrUpdateHistory(History history) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO \"History\" (user_id, month_year, budget) " +
                    "VALUES (?, ?, ?) " +
                    "ON CONFLICT (user_id, month_year) DO UPDATE SET budget = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, history.getUser_id());
            ps.setDate(2, history.getMonth_year());
            ps.setDouble(3, history.getBudget());
            ps.setDouble(4, history.getBudget());

            ps.executeUpdate();
            ps.close();
            conn.close();

            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting/updating history: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the latest budget for a user up to a specified reference date.
     *
     * @param user_id the ID of the user for whom the budget is being retrieved
     * @param referenceDate the date up to which the latest budget should be retrieved
     * @return the latest budget as a Double if it exists, or null if no budget is found
     */
    public static Double getLatestBudget(int user_id, Date referenceDate) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT budget FROM \"History\" " +
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
