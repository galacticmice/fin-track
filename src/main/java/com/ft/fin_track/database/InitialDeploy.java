package com.ft.fin_track.database;

import java.sql.*;
import java.util.Arrays;

/// used only for initial deployment of server
/// DO NOT USE IN PRODUCTION
public class InitialDeploy {

    /**
     * create category table
     * @return true if success
     */
    public static boolean createCategoryTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "CREATE TABLE \"Category\" (" +
                    "category_id SERIAL PRIMARY KEY, " +
                    "description VARCHAR(50) NOT NULL" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            // Initialize with default categories
            String[] defaultCategories = {
                "Housing", "Transportation", "Food", "Utilities", 
                "Insurance", "Medical", "Savings", "Personal", 
                "Entertainment", "Clothing", "Education", "Gifts/Donations",
                "Salary", "Investment", "Other Income", "Other Expense"
            };

            for (String category : defaultCategories) {
                String insertQuery = "INSERT INTO \"Category\" (description) VALUES (?)";
                PreparedStatement insertPs = conn.prepareStatement(insertQuery);
                insertPs.setString(1, category);
                insertPs.executeUpdate();
                insertPs.close();
            }

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating category table: " + e.getMessage());
            return false;
        }
    }

    /**
     * delete category table from database if exists
     * @return true if success
     */
    public static boolean deleteCategoryTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS \"Category\"";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting category table: " + e.getMessage());
            return false;
        }
    }

    /**
     * create user table
     * @return true if success
     */
    public static boolean createUserTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "CREATE TABLE \"Users\" (" +
                    "user_id SERIAL PRIMARY KEY, " +
                    "username VARCHAR(20) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL UNIQUE, " +
                    "password VARCHAR(128) NOT NULL, " +
                    "creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating user table: " + e.getMessage());
            return false;
        }
    }

    /**
     * create activity table
     * @return true if success
     */
    public static boolean createActivityTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "CREATE TABLE \"Activity\" (" +
                    "activity_id SERIAL PRIMARY KEY, " +
                    "entry_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "user_id INT NOT NULL, " +
                    "category_id INT NOT NULL, " +
                    "activity_type BOOLEAN NOT NULL, " +
                    "amount DECIMAL(10, 2) NOT NULL, " +
                    "description VARCHAR(255), " +
                    "FOREIGN KEY (user_id) REFERENCES Users(user_id)," +
                    "FOREIGN KEY (category_id) REFERENCES Category(category_id)" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating activity: " + e.getMessage());
            return false;
        }
    }

    /**
     * create history table
     * @return true if success
     */
    public static boolean createHistoryTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "CREATE TABLE \"History\" (" +
                    "user_id INT NOT NULL, " +
                    "month_year DATE NOT NULL, " +
                    "budget DECIMAL(10, 2) NOT NULL, " +
                    "PRIMARY KEY (user_id, month_year), " +
                    "FOREIGN KEY (user_id) REFERENCES \"Users\"(user_id)" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating history table: " + e.getMessage());
            return false;
        }
    }

    /**
     * create recurrence table
     * @return true if success
     */
    public static boolean createRecurrenceTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "CREATE TABLE \"Recurrence\" (" +
                    "recurrence_id INT NOT NULL, " +
                    "user_id INT NOT NULL, " +
                    "activity_id INT NOT NULL, " +
                    "interval_days INT NOT NULL, " +
                    "last_change DATE NOT NULL, " +
                    "PRIMARY KEY (recurrence_id), " +
                    "FOREIGN KEY (user_id) REFERENCES \"Users\"(user_id), " +
                    "FOREIGN KEY (activity_id) REFERENCES \"Activity\"(activity_id)" +
                    ")";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating recurrence table: " + e.getMessage());
            return false;
        }
    }

    /**
     * delete user table from database if exists
     * @return true if success
     */
    public static boolean deleteUserTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS \"Users\"";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting user table: " + e.getMessage());
            return false;
        }
    }

    /**
     * delete activity table from database if exists
     * @return true if success
     */
    public static boolean deleteActivityTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS \"Activity\"";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting activity table: " + e.getMessage());
            return false;
        }
    }

    /**
     * delete history table from database if exists
     * @return true if success
     */
    public static boolean deleteHistoryTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS \"History\"";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting history table: " + e.getMessage());
            return false;
        }
    }

    /**
     * delete recurrence table from database if exists
     * @return true if success
     */
    public static boolean deleteRecurrenceTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS \"Recurrence\"";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting recurrence table: " + e.getMessage());
            return false;
        }
    }
}
