package com.ft.fin_track.database;

import java.sql.*;

public class UserDAO {

    /**
     * Authenticate a user by username and password
     * @param username the username to check
     * @param password the password to check
     * @return the User object if authentication is successful, null otherwise
     */
    public static User authenticateUser(String username, String password) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT * FROM \"Users\" WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getTimestamp("creation_time")
                );
                rs.close();
                ps.close();
                return user;
            } else {
                rs.close();
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get a user by ID
     * @param userId the ID of the user to retrieve
     * @return the User object if found, null otherwise
     */
    public static User getUserById(int userId) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "SELECT * FROM \"Users\" WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getTimestamp("creation_time")
                );
                rs.close();
                ps.close();
                return user;
            } else {
                rs.close();
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting user by ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * add user to database
     * @param user object to add
     * @return true if success
     */
    public static boolean addUser(User user) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "INSERT INTO \"Users\" (username, email, password, creation_time) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setTimestamp(4, user.getCreationTime());
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
     * delete user from database for given id
     * @param user_id to delete
     * @return true if success
     */
    public static boolean deleteUser(int user_id) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "DELETE FROM Users WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    /**
     * update user in database (limit to username and password)
     * @param user_id to update
     * @param updated_user new updated user object
     * @return true if success
     */
    public static boolean updateUser(int user_id, User updated_user) {
        try (Connection conn = ConnectDB.getConnection()) {
            String query = "UPDATE Users SET username=?, password=? WHERE user_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, updated_user.getUsername());
            ps.setString(2, updated_user.getPassword());
            ps.setInt(3, user_id);
            ps.executeUpdate();

            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }
}
