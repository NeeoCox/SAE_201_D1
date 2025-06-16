package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Besoin;

public class DAOBesoin {
    private final Connection connection;

    public DAOBesoin(Connection connection) {
        this.connection = connection;
    }

    public void create(Besoin besoin) throws SQLException {
        String sql = "INSERT INTO Besoin (idDPS, intituleCompetence, nombre) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, besoin.getIdDPS());
            stmt.setString(2, besoin.getIntituleCompetence());
            stmt.setInt(3, besoin.getNombre());
            stmt.executeUpdate();
        }
    }

    public Besoin read(long dpsId, String competenceIntitule) throws SQLException {
        String sql = "SELECT * FROM Besoin WHERE idDPS = ? AND intituleCompetence = ?";
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
                    rs.getString("intituleCompetence"),
                    rs.getLong("idDPS")                    
                ));
            }
        }
        return besoins;
    }

    public void update(Besoin besoin) throws SQLException {
        String sql = "UPDATE Besoin SET nombre = ? WHERE idDPS = ? AND intituleCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, besoin.getNombre());
            stmt.setLong(2, besoin.getIdDPS());
            stmt.setString(3, besoin.getIntituleCompetence());
            stmt.executeUpdate();
        }
    }

    public void delete(long dpsId, String competenceIntitule) throws SQLException {
        String sql = "DELETE FROM Besoin WHERE idDPS = ? AND intituleCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            stmt.setString(2, competenceIntitule);
            stmt.executeUpdate();
        }
    }
}
