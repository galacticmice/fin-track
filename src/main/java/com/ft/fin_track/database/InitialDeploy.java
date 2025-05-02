package com.ft.fin_track.database;

import java.sql.*;

/// used only for initial deployment of server
/// DO NOT USE IN PRODUCTION
public class InitialDeploy {

    public static void startFresh() {
        deleteUserTable();
        deleteActivityTable();
        deleteHistoryTable();
        createUserTable();
        createActivityTable();
        createHistoryTable();
    }

    /**
     * create user table
     * @return true if success
     */
    public static boolean createUserTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "CREATE TABLE Users (" +
                    "user_id SERIAL PRIMARY KEY, " +
                    "username VARCHAR(20) NOT NULL, " +
                    "password VARCHAR(128) NOT NULL, " +
                    "creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";
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

    /**
     * create activity table
     * @return true if success
     */
    public static boolean createActivityTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "CREATE TABLE Activity (" +
                    "activity_id SERIAL PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "activity_type CHAR(1) NOT NULL, " +
                    "amount DECIMAL(10, 2) NOT NULL, " +
                    "creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (user_id) REFERENCES Users(user_id)" +
                    ")";
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

    /**
     * create history table
     * @return true if success
     */
    public static boolean createHistoryTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "";
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

    /**
     * delete user table from database if exists
     * @return true if success
     */
    public static boolean deleteUserTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS Users";
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

    /**
     * delete activity table from database if exists
     * @return true if success
     */
    public static boolean deleteActivityTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS Activity";
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

    /**
     * delete history table from database if exists
     * @return true if success
     */
    public static boolean deleteHistoryTable() {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DROP TABLE IF EXISTS History";
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
