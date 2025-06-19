package model.dao;

import java.sql.*;

import model.persistence.User;

public class DAOUser extends DAO<User>{
    private final Connection connection;

    public DAOUser() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public User readByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }
}

