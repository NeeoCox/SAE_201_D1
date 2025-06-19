package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.Competence;
import model.persistence.DPS;
import model.persistence.EstAffecteA;

public class DAOEstAffecteA extends DAO<EstAffecteA>{
    private final Connection connection;

    public DAOEstAffecteA() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

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

    public void update(EstAffecteA affectation) throws SQLException {
        String sql = "UPDATE EstAffecteA SET laCompetence = ? WHERE leSecouriste = ? AND leDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, affectation.getIntituleCompetence());
            stmt.setLong(2, affectation.getIdSecouriste());
            stmt.setLong(3, affectation.getIdDPS());
            stmt.executeUpdate();
        }
    }

    public void delete(int idSecouriste, long idDPS) throws SQLException {
        String sql = "DELETE FROM EstAffecteA WHERE leSecouriste = ? AND leDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSecouriste);
            stmt.setLong(2, idDPS);
            stmt.executeUpdate();
        }
    }

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
}