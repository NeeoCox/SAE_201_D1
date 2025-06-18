package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Site;

public class DAOSite extends DAO<Site>{
    private final Connection connection;

    public DAOSite() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(Site site) throws SQLException {
        String sql = "INSERT INTO Site (code, nom, longitude, latitude) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, site.getCode());
            stmt.setString(2, site.getNom());
            stmt.setFloat(3, site.getLongitude());
            stmt.setFloat(4, site.getLatitude());
            stmt.executeUpdate();
        }
    }

    public Site read(String code) throws SQLException {
        String sql = "SELECT * FROM Site WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Site(
                        rs.getString("code"),
                        rs.getString("nom"),
                        rs.getFloat("longitude"),
                        rs.getFloat("latitude")
                    );
                }
            }
        }
        return null;
    }

    public List<Site> readAll() throws SQLException {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT * FROM Site";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sites.add(new Site(
                    rs.getString("code"),
                    rs.getString("nom"),
                    rs.getFloat("longitude"),
                    rs.getFloat("latitude")
                ));
            }
        }
        return sites;
    }

    public void update(Site site, String ancienCode) throws SQLException {
        String sql = "UPDATE Site SET code = ?, nom = ?, longitude = ?, latitude = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, site.getCode());
            stmt.setString(2, site.getNom());
            stmt.setFloat(3, site.getLongitude());
            stmt.setFloat(4, site.getLatitude());
            stmt.setString(5, ancienCode);
            stmt.executeUpdate();
        }
    }

    public void delete(String code) throws SQLException {
        String sql = "DELETE FROM Site WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        }
    }
}
