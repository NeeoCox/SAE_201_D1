package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.data.Journee;

public class DAOJournee extends DAO<Journee>{
    private final Connection connection;

    public DAOJournee() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(Journee journee) throws SQLException {
        String sql = "INSERT INTO Journee (jour, mois, annee) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, journee.getJour());
            stmt.setInt(2, journee.getMois());
            stmt.setInt(3, journee.getAnnee());
            stmt.executeUpdate();
        }
    }

    public Journee read(int jour, int mois, int annee) throws SQLException {
        String sql = "SELECT * FROM Journee WHERE jour = ? AND mois = ? AND annee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, jour);
            stmt.setInt(2, mois);
            stmt.setInt(3, annee);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Journee j = new Journee();
                    j.setJour(rs.getInt("jour"));
                    j.setMois(rs.getInt("mois"));
                    j.setAnnee(rs.getInt("annee"));
                    return j;
                }
            }
        }
        return null;
    }

    public List<Journee> readAll() throws SQLException {
        List<Journee> journees = new ArrayList<>();
        String sql = "SELECT * FROM Journee";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Journee j = new Journee();
                j.setJour(rs.getInt("jour"));
                j.setMois(rs.getInt("mois"));
                j.setAnnee(rs.getInt("annee"));
                journees.add(j);
            }
        }
        return journees;
    }

    public void update(Journee journee, int ancienJour, int ancienMois, int ancienneAnnee) throws SQLException {
        String sql = "UPDATE Journee SET jour = ?, mois = ?, annee = ? WHERE jour = ? AND mois = ? AND annee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, journee.getJour());
            stmt.setInt(2, journee.getMois());
            stmt.setInt(3, journee.getAnnee());
            stmt.setInt(4, ancienJour);
            stmt.setInt(5, ancienMois);
            stmt.setInt(6, ancienneAnnee);
            stmt.executeUpdate();
        }
    }

    public void delete(int jour, int mois, int annee) throws SQLException {
        String sql = "DELETE FROM Journee WHERE jour = ? AND mois = ? AND annee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, jour);
            stmt.setInt(2, mois);
            stmt.setInt(3, annee);
            stmt.executeUpdate();
        }
    }
}
