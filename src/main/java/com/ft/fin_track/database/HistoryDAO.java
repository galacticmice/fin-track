package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
