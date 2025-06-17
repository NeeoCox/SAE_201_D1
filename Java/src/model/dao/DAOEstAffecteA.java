package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.data.EstAffecteA;

public class DAOEstAffecteA {
    private final Connection connection;

    public DAOEstAffecteA(Connection connection) {
        this.connection = connection;
    }

    public void create(EstAffecteA affectation) throws SQLException {
        String sql = "INSERT INTO EstAffecteA (idSecouriste, intituleCompetence, idDPS) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, affectation.getIdSecouriste());
            stmt.setString(2, affectation.getIntituleCompetence());
            stmt.setLong(3, affectation.getIdDPS());
            stmt.executeUpdate();
        }
    }

    public void update(EstAffecteA affectation) throws SQLException {
        String sql = "UPDATE EstAffecteA SET intituleCompetence = ? WHERE idSecouriste = ? AND idDPS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, affectation.getIntituleCompetence());
            stmt.setLong(2, affectation.getIdSecouriste());
            stmt.setLong(3, affectation.getIdDPS());
            stmt.executeUpdate();
        }
    }

    public void delete(int idSecouriste, long idDPS) throws SQLException {
        String sql = "DELETE FROM EstAffecteA WHERE idSecouriste = ? AND idDPS = ?";
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
                a.setIdSecouriste(rs.getInt("idSecouriste"));
                a.setIntituleCompetence(rs.getString("intituleCompetence"));
                a.setIdDPS(rs.getLong("idDPS"));
                list.add(a);
            }
        }
        return list;
    }
}