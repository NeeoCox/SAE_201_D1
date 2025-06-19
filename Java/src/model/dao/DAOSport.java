package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Sport;
/**
 * DAO pour la gestion des sports dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer des sports.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOSport extends DAO<Sport>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOSport.
     * Initialise la connexion à la base de données.
     */
    public DAOSport() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée un nouveau sport dans la base de données.
     * @param sport L'objet Sport à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Sport sport) throws SQLException {
        String sql = "INSERT INTO Sport (code, nom) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sport.getCode());
            stmt.setString(2, sport.getNom());
            stmt.executeUpdate();
        }
    }

    /**
     * Lit un sport spécifique à partir de la base de données en fonction de son code.
     * @param code Le code du sport à lire.
     * @return Un objet Sport représentant le sport trouvé, ou null si aucun sport n'est trouvé.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public Sport read(String code) throws SQLException {
        String sql = "SELECT * FROM Sport WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Sport(
                        rs.getString("code"),
                        rs.getString("nom")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Lit tous les sports de la base de données.
     * @return Une liste d'objets Sport représentant tous les sports trouvés.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<Sport> readAll() throws SQLException {
        List<Sport> sports = new ArrayList<>();
        String sql = "SELECT * FROM Sport";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sports.add(new Sport(
                    rs.getString("code"),
                    rs.getString("nom")
                ));
            }
        }
        return sports;
    }

    /**
     * Met à jour un sport existant dans la base de données.
     * @param sport L'objet Sport contenant les nouvelles valeurs.
     * @param ancienCode Le code du sport à mettre à jour.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public void update(Sport sport, String ancienCode) throws SQLException {
        String sql = "UPDATE Sport SET code = ?, nom = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sport.getCode());
            stmt.setString(2, sport.getNom());
            stmt.setString(3, ancienCode);
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime un sport de la base de données en fonction de son code.
     * @param code Le code du sport à supprimer.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void delete(String code) throws SQLException {
        String sql = "DELETE FROM Sport WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        }
    }
}
