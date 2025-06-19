package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Competence;
import model.persistence.DPS;
import model.persistence.EstAffecteA;
import model.persistence.Journee;

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
        System.out.println("DAOEstAffecteA.create : " + affectation);
        String sql = "INSERT INTO EstAffecteA (leSecouriste, leDPS, laCompetence) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, affectation.getIdSecouriste());
            stmt.setLong(2, affectation.getIdDPS());
            stmt.setString(3, affectation.getIntituleCompetence());
            stmt.executeUpdate();
            System.out.println("Insertion SQL réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'insertion : " + e.getMessage());
            throw e;
        }
    }

    /**
     * Lit une affectation spécifique à partir de la base de données en fonction de l'ID du secouriste et de l'ID du DPS.
     * @param affectation L'objet EstAffecteA contenant l'ID du secouriste et l'ID du DPS à lire.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public void update(EstAffecteA affectation) throws SQLException {
        String sql = "UPDATE EstAffecteA SET laCompetence = ? WHERE leSecouriste = ? AND leDPS = ?";
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
        String sql = "DELETE FROM EstAffecteA WHERE leSecouriste = ? AND leDPS = ?";
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
        List<EstAffecteA> affectations = new ArrayList<>();
        String sql = "SELECT leSecouriste, leDPS, laCompetence FROM EstAffecteA";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                long idSecouriste = rs.getLong("leSecouriste");
                long idDPS = rs.getLong("leDPS");
                String intituleCompetence = rs.getString("laCompetence");

                EstAffecteA aff = new EstAffecteA();
                aff.setIdSecouriste(idSecouriste);
                aff.setIdDPS(idDPS);
                aff.setIntituleCompetence(intituleCompetence);

                // Charger le DPS associé
                DAODPS daoDPS = new DAODPS();
                DPS dps = daoDPS.read(idDPS);
                aff.setLeDPS(dps);

                // Charger la compétence associée
                DAOCompetence daoCompetence = new DAOCompetence();
                Competence comp = daoCompetence.read(intituleCompetence);
                aff.setLaCompetence(comp);

                affectations.add(aff);
            }
        }
        return affectations;
    }


    public List<EstAffecteA> readBySecouristeId(long idSecouriste) throws SQLException {
        List<EstAffecteA> affectations = new ArrayList<>();
        String sql = "SELECT leSecouriste, leDPS, laCompetence FROM EstAffecteA WHERE leSecouriste = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long idDPS = rs.getLong("leDPS");
                    String intituleCompetence = rs.getString("laCompetence");

                    EstAffecteA aff = new EstAffecteA();
                    aff.setIdSecouriste(idSecouriste);
                    aff.setIdDPS(idDPS);
                    aff.setIntituleCompetence(intituleCompetence);

                    // Charger le DPS associé
                    DAODPS daoDPS = new DAODPS();
                    DPS dps = daoDPS.read(idDPS);
                    aff.setLeDPS(dps);

                    // Charger la compétence associée (optionnel)
                    DAOCompetence daoCompetence = new DAOCompetence();
                    Competence comp = daoCompetence.read(intituleCompetence);
                    aff.setLaCompetence(comp);

                    affectations.add(aff);
                }
            }
        }
        return affectations;
    }

    public int deleteByJournee(Journee journee) throws SQLException {
        String sqlDps = "SELECT id FROM DPS WHERE estProgrammeJour = ? AND estProgrammeMois = ? AND estProgrammeAnnee = ?";
        List<Long> dpsIds = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sqlDps)) {
            ps.setInt(1, journee.getJour());
            ps.setInt(2, journee.getMois());
            ps.setInt(3, journee.getAnnee());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dpsIds.add(rs.getLong("id"));
                }
            }
        }
        if (dpsIds.isEmpty()) return 0;

        StringBuilder sqlDelete = new StringBuilder("DELETE FROM EstAffecteA WHERE leDPS IN (");
        for (int i = 0; i < dpsIds.size(); i++) {
            sqlDelete.append("?");
            if (i < dpsIds.size() - 1) sqlDelete.append(",");
        }
        sqlDelete.append(")");
        try (PreparedStatement ps = connection.prepareStatement(sqlDelete.toString())) {
            for (int i = 0; i < dpsIds.size(); i++) {
                ps.setLong(i + 1, dpsIds.get(i));
            }
            return ps.executeUpdate();
        }
    }
}