package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Besoin;
/**
 * DAO pour la gestion des besoins dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer des besoins.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOBesoin extends DAO<Besoin>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOBesoin.
     */
    public DAOBesoin() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée un nouveau besoin dans la base de données.
     * @param besoin L'objet Besoin à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Besoin besoin) throws SQLException {
        String sql = "INSERT INTO Besoin (nombre, laCompetence, leDPS) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, besoin.getNombre());
            stmt.setString(2, besoin.getIntituleCompetence());
            stmt.setLong(3, besoin.getIdDPS());
            stmt.executeUpdate();
        }
    }

    /**
     *  Lit un besoin spécifique à partir de la base de données en fonction de l'ID du DPS et de l'intitulé de la compétence.
     * @param dpsId ID du DPS pour lequel le besoin est recherché.
     * @param competenceIntitule Intitulé de la compétence associée au besoin.
     * @return Un objet Besoin représentant le besoin trouvé, ou null si aucun besoin n'est trouvé.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public Besoin read(long dpsId, String competenceIntitule) throws SQLException {
        String sql = "SELECT * FROM Besoin WHERE leDPS = ? AND laCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            stmt.setString(2, competenceIntitule);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Besoin(
                        rs.getInt("nombre"),
                        rs.getString("intituleCompetence"),
                        rs.getLong("idDPS") 
                    );
                }
            }
        }
        return null;
    }

    /**
     * Lit tous les besoins de la base de données.
     * @return Une liste de tous les besoins présents dans la base de données.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<Besoin> readAll() throws SQLException {
        List<Besoin> besoins = new ArrayList<>();
        String sql = "SELECT * FROM Besoin";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                besoins.add(new Besoin(
                    rs.getInt("nombre"),
                    rs.getString("laCompetence"),
                    rs.getLong("leDPS")                    
                ));
            }
        }
        return besoins;
    }

    /**
     * Met à jour un besoin existant dans la base de données.
     * @param besoin L'objet Besoin à mettre à jour dans la base de données.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public void update(Besoin besoin) throws SQLException {
        String sql = "UPDATE Besoin SET nombre = ? WHERE leDPS = ? AND laCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, besoin.getNombre());
            stmt.setLong(2, besoin.getIdDPS());
            stmt.setString(3, besoin.getIntituleCompetence());
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime un besoin spécifique de la base de données en fonction de l'ID du DPS et de l'intitulé de la compétence.
     * @param dpsId ID du DPS pour lequel le besoin doit être supprimé.
     * @param competenceIntitule Intitulé de la compétence associée au besoin à supprimer.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void delete(long dpsId, String competenceIntitule) throws SQLException {
        String sql = "DELETE FROM Besoin WHERE leDPS = ? AND laCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            stmt.setString(2, competenceIntitule);
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime tous les besoins associés à un DPS spécifique de la base de données.
     * @param dpsId ID du DPS pour lequel tous les besoins doivent être supprimés.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void deleteByDpsId(long dpsId) throws SQLException {
        String sql = "DELETE FROM Besoin WHERE leDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            stmt.executeUpdate();
        }
    }

    public List<Besoin> readByDpsId(long dpsId) throws SQLException {
        List<Besoin> besoins = new ArrayList<>();
        String sql = "SELECT * FROM Besoin WHERE leDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dpsId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    besoins.add(new Besoin(
                        rs.getInt("nombre"),
                        rs.getString("laCompetence"),
                        rs.getLong("leDPS")                    
                    ));
                }
            }
        }
        return besoins;
    }


}
