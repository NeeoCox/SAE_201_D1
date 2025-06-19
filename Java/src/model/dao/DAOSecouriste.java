package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Possede;
import model.persistence.Secouriste;
/**
 * DAO pour la gestion des secouristes dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire,
 * mettre à jour et supprimer des secouristes.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAOSecouriste extends DAO<Secouriste> {
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAOSecouriste.
     * Initialise la connexion à la base de données.
     */
    public DAOSecouriste() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée un nouveau secouriste dans la base de données.
     * @param secouriste L'objet Secouriste à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
    public void create(Secouriste secouriste) throws SQLException {
        String sql = "INSERT INTO Secouriste (id, nom, prenom, dateNaissance, email, telephone, adresse) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, secouriste.getId());
            stmt.setString(2, secouriste.getNom());
            stmt.setString(3, secouriste.getPrenom());
            stmt.setString(4, secouriste.getDateNaissance());
            stmt.setString(5, secouriste.getEmail());
            stmt.setString(6, secouriste.getTel());
            stmt.setString(7, secouriste.getAdresse());
            stmt.executeUpdate();
        }
    }

    /**
     * Lit un secouriste spécifique à partir de la base de données en fonction de son ID.
     * @param id L'ID du secouriste à lire.
     * @return Un objet Secouriste représentant le secouriste trouvé, ou null si aucun secouriste n'est trouvé.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public Secouriste read(long id) throws SQLException {
        String sql = "SELECT * FROM Secouriste WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Secouriste s = new Secouriste();
                    s.setId(rs.getLong("id"));
                    s.setNom(rs.getString("nom"));
                    s.setPrenom(rs.getString("prenom"));
                    s.setDateNaissance(rs.getString("dateNaissance"));
                    s.setEmail(rs.getString("email"));
                    s.setTel(rs.getString("telephone"));
                    s.setAdresse(rs.getString("adresse"));

                    DAOPossede daoPossede = new DAOPossede();
                    List<Possede> possessions = daoPossede.read(s.getId());
                    s.setPossessions(possessions);

                    return s;
                }
            }
        }
        return null;
    }

    /**
     * Lit tous les secouristes de la base de données.
     * @return Une liste de tous les secouristes.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public List<Secouriste> readAll() throws SQLException {
        List<Secouriste> secouristes = new ArrayList<>();
        String sql = "SELECT * FROM Secouriste";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Secouriste s = new Secouriste();
                s.setId(rs.getLong("id"));
                s.setNom(rs.getString("nom"));
                s.setPrenom(rs.getString("prenom"));
                s.setDateNaissance(rs.getString("dateNaissance"));
                s.setEmail(rs.getString("email"));
                s.setTel(rs.getString("telephone"));
                s.setAdresse(rs.getString("adresse"));
                DAOPossede daoPossede = new DAOPossede();
                List<Possede> possessions = daoPossede.read(s.getId());
                s.setPossessions(possessions);
                secouristes.add(s);
            }
        }
        return secouristes;
    }

    /**
     * Lit un secouriste spécifique à partir de la base de données en fonction de son nom.
     * @param nom Le nom du secouriste à lire.
     * @return Un objet Secouriste représentant le secouriste trouvé, ou null si aucun secouriste n'est trouvé.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public Secouriste readByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM Secouriste WHERE nom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Secouriste s = new Secouriste();
                    s.setId(rs.getLong("id"));
                    s.setNom(rs.getString("nom"));
                    s.setPrenom(rs.getString("prenom"));
                    s.setDateNaissance(rs.getString("dateNaissance"));
                    s.setEmail(rs.getString("email"));
                    s.setTel(rs.getString("telephone"));
                    s.setAdresse(rs.getString("adresse"));
                    return s;
                }
            }
        }
        return null;
    }


    /**
     * Met à jour les informations d'un secouriste dans la base de données.
     * @param secouriste L'objet Secouriste contenant les nouvelles informations.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
    public void update(Secouriste secouriste) throws SQLException {
        String sql = "UPDATE Secouriste SET nom = ?, prenom = ?, dateNaissance = ?, email = ?, telephone = ?, adresse = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, secouriste.getNom());
            stmt.setString(2, secouriste.getPrenom());
            stmt.setString(3, secouriste.getDateNaissance());
            stmt.setString(4, secouriste.getEmail());
            stmt.setString(5, secouriste.getTel());
            stmt.setString(6, secouriste.getAdresse());
            stmt.setLong(7, secouriste.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Supprime un secouriste de la base de données en fonction de son ID.
     * @param id L'ID du secouriste à supprimer.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM Secouriste WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
