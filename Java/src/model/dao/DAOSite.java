package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.Site;
/**
 * DAO pour la gestion des sites dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer des sites.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOSite extends DAO<Site>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOSite.
     * Initialise la connexion à la base de données.
     */
    public DAOSite() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée un nouveau site dans la base de données.
     * @param site L'objet Site à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Site site) throws SQLException {
        String sql = "INSERT INTO Site (code, nom, longitude, latitude) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, site.getCode());
            stmt.setString(2, site.getNom());
            stmt.setFloat(3, site.getLongitude());
            stmt.setFloat(4, site.getLatitude());
            stmt.executeUpdate();
        }
    }

    /**
     * Lit un site spécifique à partir de la base de données en fonction de son code.
     * @param code Le code du site à lire.
     * @return Un objet Site représentant le site trouvé, ou null si aucun site n'est trouvé.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public Site read(String code) throws SQLException {
        String sql = "SELECT * FROM Site WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Site(
                        rs.getString("code"),
                        rs.getString("nom"),
                        rs.getFloat("longitude"),
                        rs.getFloat("latitude")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Lit tous les sites de la base de données.
     * @return Une liste d'objets Site représentant tous les sites trouvés.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<Site> readAll() throws SQLException {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT * FROM Site";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sites.add(new Site(
                    rs.getString("code"),
                    rs.getString("nom"),
                    rs.getFloat("longitude"),
                    rs.getFloat("latitude")
                ));
            }
        }
        return sites;
    }

    /**
     * Met à jour un site existant dans la base de données.
     * @param site L'objet Site contenant les nouvelles informations à mettre à jour.
     * @param ancienCode Le code du site à mettre à jour.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public void update(Site site, String ancienCode) throws SQLException {
        String sql = "UPDATE Site SET code = ?, nom = ?, longitude = ?, latitude = ? WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, site.getCode());
            stmt.setString(2, site.getNom());
            stmt.setFloat(3, site.getLongitude());
            stmt.setFloat(4, site.getLatitude());
            stmt.setString(5, ancienCode);
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime un site de la base de données en fonction de son code.
     * @param code Le code du site à supprimer.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void delete(String code) throws SQLException {
        String sql = "DELETE FROM Site WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        }
    }
}
