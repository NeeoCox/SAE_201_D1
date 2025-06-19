package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Necessite;

public class DAONecessite extends DAO<Necessite>{
    private final Connection connection;

    public DAONecessite() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(Necessite necessite) throws SQLException {
        String sql = "INSERT INTO Necessite (intituleCompetence, intituleCompetenceNecessaire) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, necessite.getIntituleCompetence());
            stmt.setString(2, necessite.getIntituleCompetenceNecessaire());
            stmt.executeUpdate();
        }
    }

    public void delete(String intituleCompetence, String intituleCompetenceNecessaire) throws SQLException {
        String sql = "DELETE FROM Necessite WHERE intituleCompetence = ? AND intituleCompetenceNecessaire = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, intituleCompetence);
            stmt.setString(2, intituleCompetenceNecessaire);
            stmt.executeUpdate();
        }
    }

    public List<Necessite> readAll() throws SQLException {
        List<Necessite> necessites = new ArrayList<>();
        String sql = "SELECT * FROM Necessite";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Necessite n = new Necessite();
                n.setIntituleCompetence(rs.getString("intituleCompetence"));
                n.setIntituleCompetenceNecessaire(rs.getString("intituleCompetenceNecessaire"));
                necessites.add(n);
            }
        }
        return necessites;
    }
}