package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/// this is a static table used only as reference
/// no need to add, delete or update
/// read operation only
public class CategoryDAO {
    public static boolean getCategory(int category_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT description FROM Category WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, category_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error getting category: " + e.getMessage());
            return false;
        }
    }
}
