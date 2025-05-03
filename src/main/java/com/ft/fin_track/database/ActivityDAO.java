package com.ft.fin_track.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ActivityDAO {

    /**
     * Get an activity by ID
     * @param activity_id the ID of the activity to retrieve
     * @return the Activity object if found, null otherwise
     */
    public static Activity getActivityById(int activity_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT * FROM \"Activity\" WHERE activity_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, activity_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Activity activity = new Activity(
                    rs.getInt("activity_id"),
                    rs.getTimestamp("entry_time"),
                    rs.getInt("user_id"),
                    rs.getInt("category_id"),
                    rs.getBoolean("activity_type"),
                    rs.getDouble("amount"),
                    rs.getString("description")
                );
                rs.close();
                ps.close();
                return activity;
            } else {
                rs.close();
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting activity by ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get all activities for a user in the current month
     * @param user_id the ID of the user
     * @return a list of Activity objects
     */
    public static List<Activity> getActivitiesForCurrentMonth(int user_id) {
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);
        Date sqlFirstDay = Date.valueOf(firstDayOfMonth);
        LocalDate firstDayOfNextMonth = now.plusMonths(1).withDayOfMonth(1);
        Date sqlNextMonth = Date.valueOf(firstDayOfNextMonth);

        return getActivitiesForDateRange(user_id, sqlFirstDay, sqlNextMonth);
    }

    /**
     * Get all activities for a user in a specific month and year
     * @param user_id the ID of the user
     * @param year the year
     * @param month the month (1-12)
     * @return a list of Activity objects
     */
    public static List<Activity> getActivitiesForMonth(int user_id, int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        Date sqlFirstDay = Date.valueOf(firstDayOfMonth);
        LocalDate firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);
        Date sqlNextMonth = Date.valueOf(firstDayOfNextMonth);

        return getActivitiesForDateRange(user_id, sqlFirstDay, sqlNextMonth);
    }

    /**
     * Get all activities for a user in a date range
     * @param user_id the ID of the user
     * @param startDate the start date (inclusive)
     * @param endDate the end date (exclusive)
     * @return a list of Activity objects
     */
    private static List<Activity> getActivitiesForDateRange(int user_id, Date startDate, Date endDate) {
        List<Activity> activities = new ArrayList<>();

        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT * FROM \"Activity\" WHERE user_id = ? AND entry_time >= ? AND entry_time < ? ORDER BY entry_time DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Activity activity = new Activity(
                    rs.getInt("activity_id"),
                    rs.getTimestamp("entry_time"),
                    rs.getInt("user_id"),
                    rs.getInt("category_id"),
                    rs.getBoolean("activity_type"),
                    rs.getDouble("amount"),
                    rs.getString("description")
                );
                activities.add(activity);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error getting activities: " + e.getMessage());
        }

        return activities;
    }

    /**
     * Get monthly summary statistics for a user
     * @param user_id the ID of the user
     * @param year the year
     * @param month the month (1-12)
     * @return an array of [totalIncome, totalExpense]
     */
    public static double[] getMonthlySummary(int user_id, int year, int month) {
        double totalIncome = 0;
        double totalExpense = 0;

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        Date sqlFirstDay = Date.valueOf(firstDayOfMonth);
        LocalDate firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);
        Date sqlNextMonth = Date.valueOf(firstDayOfNextMonth);

        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT activity_type, SUM(amount) as \"total\" FROM \"Activity\" " +
                           "WHERE user_id = ? AND entry_time >= ? AND entry_time < ? " +
                           "GROUP BY activity_type";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.setDate(2, sqlFirstDay);
            ps.setDate(3, sqlNextMonth);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                boolean isIncome = rs.getBoolean("activity_type");
                double amount = rs.getDouble("total");

                if (isIncome) {
                    totalIncome = amount;
                } else {
                    totalExpense = amount;
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error getting monthly summary: " + e.getMessage());
        }

        return new double[] { totalIncome, totalExpense };
    }
    public static Integer addActivity(Activity activity) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO \"Activity\" (entry_time, user_id, category_id, activity_type, amount, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?)" +
                    "RETURNING activity_id";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setTimestamp(1, activity.getEntryTime());
            ps.setInt(2, activity.getUser_id());
            ps.setInt(3, activity.getCategory_id());
            ps.setBoolean(4, activity.getActivity_type());
            ps.setDouble(5, activity.getAmount());
            ps.setString(6, activity.getDescription());
            ResultSet rs = ps.executeQuery();
            Integer activity_id = null;
            if (rs.next()) {
                activity_id = rs.getInt("activity_id");
            }

            ps.close();
            conn.close();
            return activity_id;
        } catch (SQLException e) {
            System.out.println("Error adding activity: " + e.getMessage());
            return null;
        }
    }

    public static boolean deleteActivity(int activity_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DELETE FROM \"Activity\" WHERE activity_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, activity_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting activity: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateActivity(int activity_id, Activity updated_activity) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "UPDATE \"Activity\" SET entry_time=?, category_id=?, activity_type=?, amount=?, description=? WHERE activity_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setTimestamp(1, updated_activity.getEntryTime());
            ps.setInt(2, updated_activity.getCategory_id());
            ps.setBoolean(3, updated_activity.getActivity_type());
            ps.setDouble(4, updated_activity.getAmount());
            ps.setString(5, updated_activity.getDescription());
            ps.setInt(6, activity_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating activity: " + e.getMessage());
            return false;
        }
    }
}
