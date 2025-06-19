package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.EstDisponible;
import model.persistence.Journee;
import model.persistence.Secouriste;

/**
 * DAO pour la gestion de la disponibilité des secouristes dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer les disponibilités des secouristes.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOEstDisponible extends DAO<EstDisponible> {
    /**
     * Connection à la base de données.
     */
    private final Connection connection;
    /**
     * DAO pour la gestion des secouristes.
     */
    private final DAOSecouriste daoSecouriste;
    /**
     * DAO pour la gestion des journées.
     */
    private final DAOJournee daoJournee;

    /**
     * Constructeur de la classe DAOEstDisponible.
     */
    public DAOEstDisponible() {
        try {
            this.connection = createConnection();
            this.daoSecouriste = new DAOSecouriste();
            this.daoJournee = new DAOJournee();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée une nouvelle disponibilité pour un secouriste dans la base de données.
     * @param dispo L'objet EstDisponible à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
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

    /**
     * Met à jour la disponibilité d'un secouriste dans la base de données.
     * @param dispo L'objet EstDisponible à mettre à jour dans la base de données.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public EstDisponible read(long idSecouriste, int jour, int mois, int annee) throws SQLException {
        String sql = "SELECT * FROM EstDisponible WHERE idSecouriste = ? AND jour = ? AND mois = ? AND annee = ?";
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

    /**
     * Lit toutes les disponibilités des secouristes dans la base de données.
     * @return Une liste de tous les objets EstDisponible présents dans la base de données.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<EstDisponible> readAll() throws SQLException {
        List<EstDisponible> dispos = new ArrayList<>();
        String sql = "SELECT * FROM EstDisponible";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                long idSecouriste = rs.getLong("idSecouriste");
                int jour = rs.getInt("jour");
                int mois = rs.getInt("mois");
                int annee = rs.getInt("annee");
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

    /**
     * Met à jour la disponibilité d'un secouriste dans la base de données.
     * @param dispo L'objet EstDisponible contenant les nouvelles informations à mettre à jour.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public void delete(long idSecouriste, int jour, int mois, int annee) throws SQLException {
        String sql = "DELETE FROM EstDisponible WHERE idSecouriste = ? AND jour = ? AND mois = ? AND annee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            stmt.setInt(2, jour);
            stmt.setInt(3, mois);
            stmt.setInt(4, annee);
            stmt.executeUpdate();
        }
    }
}