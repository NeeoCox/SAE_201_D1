package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.EstDisponible;
import model.persistence.Journee;
import model.persistence.Secouriste;

public class DAOEstDisponible extends DAO<EstDisponible> {
    private final Connection connection;
    private final DAOSecouriste daoSecouriste;
    private final DAOJournee daoJournee;

    public DAOEstDisponible() {
        try {
            this.connection = createConnection();
            this.daoSecouriste = new DAOSecouriste();
            this.daoJournee = new DAOJournee();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(EstDisponible dispo) throws SQLException {
        String sql = "INSERT INTO EstDisponible (leSecouriste, jourJournee, moisJournee, anneeJournee) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dispo.getIdSecouriste());
            stmt.setInt(2, dispo.getJourJournee());
            stmt.setInt(3, dispo.getMoisJournee());
            stmt.setInt(4, dispo.getAnneeJournee());
            stmt.executeUpdate();
        }
    }

    public EstDisponible read(long idSecouriste, int jour, int mois, int annee) throws SQLException {
        String sql = "SELECT * FROM EstDisponible WHERE leSecouriste = ? AND jourJournee = ? AND moisJournee = ? AND anneeJournee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            stmt.setInt(2, jour);
            stmt.setInt(3, mois);
            stmt.setInt(4, annee);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Secouriste secouriste = daoSecouriste.read(idSecouriste);
                    Journee journee = daoJournee.read(jour, mois, annee);
                    EstDisponible dispo = new EstDisponible();
                    dispo.setSecouriste(secouriste);
                    dispo.setIdSecouriste(idSecouriste);
                    dispo.setLaJournee(journee);
                    dispo.setJourJournee(jour);
                    dispo.setMoisJournee(mois);
                    dispo.setAnneeJournee(annee);
                    return dispo;
                }
            }
        }
        return null;
    }

    public List<EstDisponible> readAll() throws SQLException {
        List<EstDisponible> dispos = new ArrayList<>();
        String sql = "SELECT * FROM EstDisponible";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                long idSecouriste = rs.getLong("leSecouriste");
                int jour = rs.getInt("jourJournee");
                int mois = rs.getInt("moisJournee");
                int annee = rs.getInt("anneeJournee");
                Secouriste secouriste = daoSecouriste.read(idSecouriste);
                Journee journee = daoJournee.read(jour, mois, annee);
                EstDisponible dispo = new EstDisponible();
                dispo.setSecouriste(secouriste);
                dispo.setIdSecouriste(idSecouriste);
                dispo.setLaJournee(journee);
                dispo.setJourJournee(jour);
                dispo.setMoisJournee(mois);
                dispo.setAnneeJournee(annee);
                dispos.add(dispo);
            }
        }
        return dispos;
    }

    public void delete(long idSecouriste, int jour, int mois, int annee) throws SQLException {
        String sql = "DELETE FROM EstDisponible WHERE leSecouriste = ? AND jourJournee = ? AND moisJournee = ? AND anneeJournee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            stmt.setInt(2, jour);
            stmt.setInt(3, mois);
            stmt.setInt(4, annee);
            stmt.executeUpdate();
        }
    }
}