package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Journee;

/**
 * DAO pour la gestion des journées dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer des journées.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOJournee extends DAO<Journee>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOJournee.
     * Initialise la connexion à la base de données.
     */
    public DAOJournee() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée une nouvelle journée dans la base de données.
     * @param journee L'objet Journee à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Journee journee) throws SQLException {
        String sql = "INSERT INTO Journee (jour, mois, annee) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, journee.getJour());
            stmt.setInt(2, journee.getMois());
            stmt.setInt(3, journee.getAnnee());
            stmt.executeUpdate();
        }
    }

    /**
     * Lit une journée spécifique à partir de la base de données en fonction de sa date.
     * @param jour Le jour de la journée à lire.
     * @param mois Le mois de la journée à lire.
     * @param annee L'année de la journée à lire.
     * @return Un objet Journee représentant la journée trouvée, ou null si aucune journée n'est trouvée.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
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

    /**
     * Lit toutes les journées de la base de données.
     * @return Une liste de tous les objets Journee présents dans la base de données.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
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

    /**
     * Met à jour une journée existante dans la base de données.
     * @param journee L'objet Journee contenant les nouvelles informations.
     * @param ancienJour Le jour de la journée à mettre à jour.
     * @param ancienMois Le mois de la journée à mettre à jour.
     * @param ancienneAnnee L'année de la journée à mettre à jour.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
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

    /**
     * Supprime une journée de la base de données en fonction de sa date.
     * @param jour Le jour de la journée à supprimer.
     * @param mois Le mois de la journée à supprimer.
     * @param annee L'année de la journée à supprimer.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
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
