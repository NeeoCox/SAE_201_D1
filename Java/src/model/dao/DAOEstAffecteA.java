package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.EstAffecteA;

/**
 * DAO pour la gestion des affectations de secouristes à des DPS dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer des affectations.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOEstAffecteA extends DAO<EstAffecteA>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOEstAffecteA.
     * Initialise la connexion à la base de données.
     */
    public DAOEstAffecteA() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée une nouvelle affectation de secouriste à un DPS dans la base de données.
     * @param affectation L'objet EstAffecteA à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(EstAffecteA affectation) throws SQLException {
        String sql = "INSERT INTO EstAffecteA (idSecouriste, intituleCompetence, idDPS) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, affectation.getIdSecouriste());
            stmt.setString(2, affectation.getIntituleCompetence());
            stmt.setLong(3, affectation.getIdDPS());
            stmt.executeUpdate();
        }
    }

    /**
     * Lit une affectation spécifique à partir de la base de données en fonction de l'ID du secouriste et de l'ID du DPS.
     * @param affectation L'objet EstAffecteA contenant l'ID du secouriste et l'ID du DPS à lire.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public void update(EstAffecteA affectation) throws SQLException {
        String sql = "UPDATE EstAffecteA SET intituleCompetence = ? WHERE idSecouriste = ? AND idDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, affectation.getIntituleCompetence());
            stmt.setLong(2, affectation.getIdSecouriste());
            stmt.setLong(3, affectation.getIdDPS());
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime une affectation de secouriste à un DPS dans la base de données.
     * @param idSecouriste ID du secouriste à supprimer.
     * @param idDPS ID du DPS à supprimer. 
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void delete(int idSecouriste, long idDPS) throws SQLException {
        String sql = "DELETE FROM EstAffecteA WHERE idSecouriste = ? AND idDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSecouriste);
            stmt.setLong(2, idDPS);
            stmt.executeUpdate();
        }
    }

    /**
     * Lit une affectation spécifique à partir de la base de données en fonction de l'ID du secouriste et de l'intitulé de la compétence.
     * @return Un objet EstAffecteA représentant l'affectation trouvée, ou null si aucune affectation n'est trouvée.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<EstAffecteA> readAll() throws SQLException {
        List<EstAffecteA> list = new ArrayList<>();
        String sql = "SELECT * FROM EstAffecteA";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EstAffecteA a = new EstAffecteA();
                a.setIdSecouriste(rs.getInt("idSecouriste"));
                a.setIntituleCompetence(rs.getString("intituleCompetence"));
                a.setIdDPS(rs.getLong("idDPS"));
                list.add(a);
            }
        }
        return list;
    }
}