package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.data.Possede;

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
        String sql = "INSERT INTO Possede (idSecouriste, intituleCompetence) VALUES (?, ?)";
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
                p.setIdSecouriste(rs.getLong("idSecouriste"));
                p.setIntituleCompetence(rs.getString("intituleCompetence"));
                possedes.add(p);
            }
        }
        return possedes;
    }
}