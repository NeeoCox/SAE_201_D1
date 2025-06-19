package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Possede;
/**
 * DAO pour la gestion des compétences possédées par les secouristes dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire et supprimer les compétences possédées par les secouristes.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOPossede extends DAO<Possede>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOPossede.
     * Initialise la connexion à la base de données.
     */
    public DAOPossede() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée une nouvelle compétence possédée par un secouriste dans la base de données.
     * @param possede L'objet Possede à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Possede possede) throws SQLException {
        String sql = "INSERT INTO Possede (leSecouriste, laCompetence) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, possede.getIdSecouriste());
            stmt.setString(2, possede.getIntituleCompetence());
            stmt.executeUpdate();
        }
    }

    /**
     * Met à jour la compétence possédée par un secouriste dans la base de données.
     * @param possede L'objet Possede à mettre à jour dans la base de données.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public void delete(long idSecouriste, String intituleCompetence) throws SQLException {
        String sql = "DELETE FROM Possede WHERE leSecouriste = ? AND laCompetence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            stmt.setString(2, intituleCompetence);
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime toutes les compétences possédées par un secouriste dans la base de données.
     * @param idSecouriste L'ID du secouriste dont les compétences doivent être supprimées.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void deleteBySecouristeId(long idSecouriste) throws SQLException {
        String sql = "DELETE FROM Possede WHERE leSecouriste = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            stmt.executeUpdate();
        }
    }


    /**
     * Lit toutes les compétences possédées par les secouristes dans la base de données.
     * @return Une liste d'objets Possede représentant les compétences possédées par les secouristes.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<Possede> readAll() throws SQLException {
        List<Possede> possedes = new ArrayList<>();
        String sql = "SELECT * FROM Possede";
        DAOCompetence daoCompetence = new DAOCompetence(); // Ajouté
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Possede p = new Possede();
                p.setIdSecouriste(rs.getLong("leSecouriste"));
                p.setIntituleCompetence(rs.getString("laCompetence"));
                // Ajoute cette ligne :
                p.setLaCompetence(daoCompetence.read(p.getIntituleCompetence()));
                possedes.add(p);
            }
        }
        return possedes;
    }

    /**
     * Lit les compétences possédées par un secouriste spécifique dans la base de données.
     * @param idSecouriste L'ID du secouriste dont les compétences doivent être lues.
     * @return Une liste d'objets Possede représentant les compétences possédées par le secouriste.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<Possede> read(long idSecouriste) throws SQLException {
        List<Possede> possedes = new ArrayList<>();
        String sql = "SELECT * FROM Possede WHERE leSecouriste = ?";
        DAOCompetence daoCompetence = new DAOCompetence(); // Ajouté
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Possede p = new Possede();
                p.setIdSecouriste(rs.getLong("leSecouriste"));
                p.setIntituleCompetence(rs.getString("laCompetence"));
                // Ajoute cette ligne :
                p.setLaCompetence(daoCompetence.read(p.getIntituleCompetence()));
                possedes.add(p);
            }
        }
        return possedes;
    }
}