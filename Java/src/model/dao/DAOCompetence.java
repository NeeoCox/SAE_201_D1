package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Competence;

/**
 * DAO pour la gestion des compétences dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer des compétences.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOCompetence extends DAO<Competence>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOCompetence.
     */
    public DAOCompetence(){
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée une nouvelle compétence dans la base de données.
     * @param competence L'objet Competence à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Competence competence) throws SQLException {
        String sql = "INSERT INTO Competence (intitule) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, competence.getIntitule());
            stmt.executeUpdate();
        }
    }

    /**
     * Lit une compétence spécifique à partir de la base de données en fonction de son intitulé.
     * @param intitule L'intitulé de la compétence à lire.
     * @return Un objet Competence représentant la compétence trouvée, ou null si aucune compétence n'est trouvée.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
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

    /**
     * Lit toutes les compétences de la base de données.
     * @return Une liste de tous les objets Competence présents dans la base de données.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
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

    /**
     * Met à jour une compétence dans la base de données.
     * @param competence L'objet Competence contenant les nouvelles informations.
     * @param ancienIntitule L'ancien intitulé de la compétence à mettre à jour.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public void update(Competence competence, String ancienIntitule) throws SQLException {
        String sql = "UPDATE Competence SET intitule = ? WHERE intitule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, competence.getIntitule());
            stmt.setString(2, ancienIntitule);
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime une compétence de la base de données en fonction de son intitulé. 
     * @param intitule L'intitulé de la compétence à supprimer.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void delete(String intitule) throws SQLException {
        String sql = "DELETE FROM Competence WHERE intitule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, intitule);
            stmt.executeUpdate();
        }
    }

    /**
     * Vérifie si une compétence existe dans la base de données en fonction de son intitulé.
     * @param intitule L'intitulé de la compétence à vérifier.
     * @return  true si la compétence existe, false sinon.
     * @throws SQLException si une erreur se produit lors de la vérification dans la base de données.
     */
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

    /**
     * Recherche une compétence par son intitulé dans la base de données.
     * @param intitule L'intitulé de la compétence à rechercher.
     * @return Un objet Competence si trouvé, sinon null.
     */
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
