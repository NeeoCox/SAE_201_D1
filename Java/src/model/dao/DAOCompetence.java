package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Competence;

public class DAOCompetence extends DAO<Competence>{
    private final Connection connection;

    public DAOCompetence(){
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(Competence competence) throws SQLException {
        String sql = "INSERT INTO Competence (intitule) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, competence.getIntitule());
            stmt.executeUpdate();
        }
    }

    public Competence read(String intitule) throws SQLException {
        String sql = "SELECT * FROM Competence WHERE intitule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, intitule);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Competence competence = new Competence();
                    competence.setIntitule(rs.getString("intitule"));
                    return competence;
                }
            }
        }
        return null;
    }

    public List<Competence> readAll() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT * FROM Competence";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Competence competence = new Competence();
                competence.setIntitule(rs.getString("intitule"));
                competences.add(competence);
            }
        }
        return competences;
    }

    public void update(Competence competence, String ancienIntitule) throws SQLException {
        String sql = "UPDATE Competence SET intitule = ? WHERE intitule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, competence.getIntitule());
            stmt.setString(2, ancienIntitule);
            stmt.executeUpdate();
        }
    }

    public void delete(String intitule) throws SQLException {
        String sql = "DELETE FROM Competence WHERE intitule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, intitule);
            stmt.executeUpdate();
        }
    }

    public boolean exists(String intitule) throws SQLException {
        String query = "SELECT COUNT(*) FROM competence WHERE intitule = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, intitule);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public Competence findByIntitule(String intitule) {
        try {
            for (Competence c : readAll()) {
                if (c.getIntitule().equals(intitule)) {
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
