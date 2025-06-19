package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Necessite;
/**
 * DAO pour la gestion des nécessités (dépendances) entre les compétences dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire et supprimer des nécessités.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAONecessite extends DAO<Necessite>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAONecessite.
     */
    public DAONecessite() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée une nouvelle nécessité dans la base de données.
     * @param necessite L'objet Necessite à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Necessite necessite) throws SQLException {
        String sql = "INSERT INTO Necessite (intituleCompetence, intituleCompetenceNecessaire) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, necessite.getIntituleCompetence());
            stmt.setString(2, necessite.getIntituleCompetenceNecessaire());
            stmt.executeUpdate();
        }
    }

    /**
     * Lit une nécessité spécifique à partir de la base de données en fonction des intitulés des compétences.
     * @param intituleCompetence L'intitulé de la compétence principale.
     * @param intituleCompetenceNecessaire L'intitulé de la compétence nécessaire.
     * @return Un objet Necessite représentant la nécessité trouvée, ou null si aucune nécessité n'est trouvée.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public void delete(String intituleCompetence, String intituleCompetenceNecessaire) throws SQLException {
        String sql = "DELETE FROM Necessite WHERE intituleCompetence = ? AND intituleCompetenceNecessaire = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, intituleCompetence);
            stmt.setString(2, intituleCompetenceNecessaire);
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime toutes les nécessités associées à une compétence spécifique.
     * @param intitule L'intitulé de la compétence pour laquelle toutes les nécessités doivent être supprimées.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void deleteByCompetence(String intitule) throws SQLException {
        String sql = "DELETE FROM Necessite WHERE intituleCompetence = ? OR intituleCompetenceNecessaire = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, intitule);
            stmt.setString(2, intitule);
            stmt.executeUpdate();
        }
    }


    /**
     * Lit une nécessité spécifique à partir de la base de données en fonction des intitulés des compétences.
     * @return Un objet Necessite représentant la nécessité trouvée, ou null si aucune nécessité n'est trouvée.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
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