package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Possede;

public class DAOPossede extends DAO<Possede>{
    private final Connection connection;

    public DAOPossede() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(Possede possede) throws SQLException {
        String sql = "INSERT INTO Possede (leSecouriste, laCompetence) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, possede.getIdSecouriste());
            stmt.setString(2, possede.getIntituleCompetence());
            stmt.executeUpdate();
        }
    }

    public void delete(long idSecouriste, String intituleCompetence) throws SQLException {
        String sql = "DELETE FROM Possede WHERE idSecouriste = ? AND intituleCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            stmt.setString(2, intituleCompetence);
            stmt.executeUpdate();
        }
    }

    public List<Possede> readAll() throws SQLException {
        List<Possede> possedes = new ArrayList<>();
        String sql = "SELECT * FROM Possede";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Possede p = new Possede();
                p.setIdSecouriste(rs.getLong("leSecouriste"));
                p.setIntituleCompetence(rs.getString("laCompetence"));
                possedes.add(p);
            }
        }
        return possedes;
    }

    public List<Possede> read(long idSecouriste) throws SQLException {
        List<Possede> possedes = new ArrayList<>();
        String sql = "SELECT * FROM Possede WHERE leSecouriste = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Possede p = new Possede();
                p.setIdSecouriste(rs.getLong("leSecouriste"));
                p.setIntituleCompetence(rs.getString("laCompetence"));
                possedes.add(p);
            }
        }
        return possedes;
    }

}