package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Besoin;

public class DAOBesoin extends DAO<Besoin>{
    private final Connection connection;

    public DAOBesoin() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(Besoin besoin) throws SQLException {
        String sql = "INSERT INTO Besoin (nombre, laCompetence, leDPS) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, besoin.getNombre());
            stmt.setString(2, besoin.getIntituleCompetence());
            stmt.setLong(3, besoin.getIdDPS());
            stmt.executeUpdate();
        }
    }

    public Besoin read(long dpsId, String competenceIntitule) throws SQLException {
        String sql = "SELECT * FROM Besoin WHERE leDPS = ? AND laCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            stmt.setString(2, competenceIntitule);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Besoin(
                        rs.getInt("nombre"),
                        rs.getString("intituleCompetence"),
                        rs.getLong("idDPS") 
                    );
                }
            }
        }
        return null;
    }

    public List<Besoin> readAll() throws SQLException {
        List<Besoin> besoins = new ArrayList<>();
        String sql = "SELECT * FROM Besoin";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                besoins.add(new Besoin(
                    rs.getInt("nombre"),
                    rs.getString("laCompetence"),
                    rs.getLong("leDPS")                    
                ));
            }
        }
        return besoins;
    }

    public void update(Besoin besoin) throws SQLException {
        String sql = "UPDATE Besoin SET nombre = ? WHERE leDPS = ? AND laCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, besoin.getNombre());
            stmt.setLong(2, besoin.getIdDPS());
            stmt.setString(3, besoin.getIntituleCompetence());
            stmt.executeUpdate();
        }
    }

    public void delete(long dpsId, String competenceIntitule) throws SQLException {
        String sql = "DELETE FROM Besoin WHERE leDPS = ? AND laCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            stmt.setString(2, competenceIntitule);
            stmt.executeUpdate();
        }
    }

    public void deleteByDpsId(long dpsId) throws SQLException {
        String sql = "DELETE FROM Besoin WHERE leDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            stmt.executeUpdate();
        }
    }


}
