package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.data.Secouriste;

public class DAOSecouriste extends DAO<Secouriste> {
    private final Connection connection;

    public DAOSecouriste() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

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
                    s.setTel(rs.getString("tel"));
                    s.setAdresse(rs.getString("adresse"));
                    return s;
                }
            }
        }
        return null;
    }

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
                secouristes.add(s);
            }
        }
        return secouristes;
    }

    public void update(Secouriste secouriste) throws SQLException {
        String sql = "UPDATE Secouriste SET nom = ?, prenom = ?, dateNaissance = ?, email = ?, tel = ?, adresse = ? WHERE id = ?";
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

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM Secouriste WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
