package com.ft.fin_track.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/// this is a static table used only as reference
/// no need to add, delete or update
/// read operation only
public class CategoryDAO {
    /**
     * Get a category by ID
     * @param category_id the ID of the category to retrieve
     * @return the Category object if found, null otherwise
     */
    public static Category getCategory(int category_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT * FROM \"Category\" WHERE category_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Category category = new Category(
                    rs.getInt("category_id"),
                    rs.getString("description")
                );
                rs.close();
                ps.close();
                return category;
            } else {
                rs.close();
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting category: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get all categories
     * @return a list of all categories
     */
    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT * FROM \"Category\" ORDER BY category_id";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category(
                    rs.getInt("category_id"),
                    rs.getString("description")
                );
                categories.add(category);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error getting all categories: " + e.getMessage());
        }

        return categories;
    }
}
