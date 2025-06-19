package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Sport;

public class DAOSport extends DAO<Sport>{
    private final Connection connection;

    public DAOSport() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(Sport sport) throws SQLException {
        String sql = "INSERT INTO Sport (code, nom) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sport.getCode());
            stmt.setString(2, sport.getNom());
            stmt.executeUpdate();
        }
    }

    public Sport read(String code) throws SQLException {
        String sql = "SELECT * FROM Sport WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Sport(
                        rs.getString("code"),
                        rs.getString("nom")
                    );
                }
            }
        }
        return null;
    }

    public List<Sport> readAll() throws SQLException {
        List<Sport> sports = new ArrayList<>();
        String sql = "SELECT * FROM Sport";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sports.add(new Sport(
                    rs.getString("code"),
                    rs.getString("nom")
                ));
            }
        }
        return sports;
    }

    public void update(Sport sport, String ancienCode) throws SQLException {
        String sql = "UPDATE Sport SET code = ?, nom = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sport.getCode());
            stmt.setString(2, sport.getNom());
            stmt.setString(3, ancienCode);
            stmt.executeUpdate();
        }
    }

    public void delete(String code) throws SQLException {
        String sql = "DELETE FROM Sport WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        }
    }
}
