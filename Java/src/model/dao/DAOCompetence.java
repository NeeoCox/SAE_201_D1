package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Competence;

public class DAOCompetence {
    private final Connection connection;

    public DAOCompetence(Connection connection) {
        this.connection = connection;
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
}
