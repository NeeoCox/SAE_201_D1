package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
        String sql = "INSERT INTO EstAffecteA (leSecouriste, intituleCompetence, idDPS) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, affectation.getIdSecouriste());
            stmt.setString(2, affectation.getIntituleCompetence());
            stmt.setLong(3, affectation.getIdDPS());
            stmt.executeUpdate();
        }
    }

    public void update(EstAffecteA affectation) throws SQLException {
        String sql = "UPDATE EstAffecteA SET intituleCompetence = ? WHERE leSecouriste = ? AND idDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, affectation.getIntituleCompetence());
            stmt.setLong(2, affectation.getIdSecouriste());
            stmt.setLong(3, affectation.getIdDPS());
            stmt.executeUpdate();
        }
    }

    public void delete(int idSecouriste, long idDPS) throws SQLException {
        String sql = "DELETE FROM EstAffecteA WHERE leSecouriste = ? AND idDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSecouriste);
            stmt.setLong(2, idDPS);
            stmt.executeUpdate();
        }
    }

    public List<EstAffecteA> readAll() throws SQLException {
        List<EstAffecteA> list = new ArrayList<>();
        String sql = "SELECT * FROM EstAffecteA";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EstAffecteA a = new EstAffecteA();
                a.setIdSecouriste(rs.getInt("leSecouriste"));
                a.setIntituleCompetence(rs.getString("intituleCompetence"));
                a.setIdDPS(rs.getLong("idDPS"));
                list.add(a);
            }
        }
        return list;
    }


    public List<EstAffecteA> readBySecouristeId(long idSecouriste) throws SQLException {
        List<EstAffecteA> list = new ArrayList<>();
        String sql = "SELECT * FROM EstAffecteA WHERE leSecouriste = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idSecouriste);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EstAffecteA a = new EstAffecteA();
                    a.setIdSecouriste(rs.getInt("leSecouriste"));
                    a.setIntituleCompetence(rs.getString("intituleCompetence"));
                    a.setIdDPS(rs.getLong("idDPS"));
                    list.add(a);
                }
            }
        }
        return list;
    }
}