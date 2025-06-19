package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.persistence.DPS;
import model.persistence.Journee;
import model.persistence.Site;
import model.persistence.Sport;

public class DAODPS extends DAO<DPS>{
    private final Connection connection;

    public DAODPS() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public void create(DPS dps) throws SQLException {
        String sql = "INSERT INTO DPS (id, horaire_depart, horaire_fin, estProgrammeJour, estProgrammeMois, estProgrammeAnnee, aLieuDansSite, concerneSport) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, dps.getId());
            stmt.setInt(2, dps.getHoraireDepart());
            stmt.setInt(3, dps.getHoraireFin());
            stmt.setInt(4, dps.getEstProgramme().getJour());
            stmt.setInt(5, dps.getEstProgramme().getMois());
            stmt.setInt(6, dps.getEstProgramme().getAnnee());
            stmt.setString(7, dps.getALieuDans().getCode());
            stmt.setString(8, dps.getConcerne().getCode());
            stmt.executeUpdate();
        }
    }

    public DPS read(long id) throws SQLException {
        String sql = "SELECT d.*, s.nom AS nom_site, s.longitude, s.latitude, sp.nom AS nom_sport " +
                    "FROM DPS d " +
                    "JOIN Site s ON d.code_site = s.code " +
                    "JOIN Sport sp ON d.code_sport = sp.code " +
                    "WHERE d.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Journee journee = new Journee(
                        rs.getInt("jour"),
                        rs.getInt("mois"),
                        rs.getInt("annee")
                    );
                    Site site = new Site(
                        rs.getString("code_site"),
                        rs.getString("nom_site"),
                        rs.getFloat("longitude"),
                        rs.getFloat("latitude")
                    );
                    Sport sport = new Sport(
                        rs.getString("code_sport"),
                        rs.getString("nom_sport")
                    );
                    return new DPS(
                        rs.getLong("id"),
                        rs.getInt("horaire_depart"),
                        rs.getInt("horaire_fin"),
                        journee,
                        site,
                        sport
                    );
                }
            }
        }
        return null;
    }

    public List<DPS> readAll() throws SQLException {
        List<DPS> dpsList = new ArrayList<>();
        String sql = "SELECT d.*, s.nom AS nom_site, s.longitude, s.latitude, sp.nom AS nom_sport " +
                    "FROM DPS d " +
                    "JOIN Site s ON d.aLieuDansSite = s.code " +
                    "JOIN Sport sp ON d.concerneSport = sp.code";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Journee journee = new Journee(
                    rs.getInt("estProgrammeJour"),
                    rs.getInt("estProgrammeMois"),
                    rs.getInt("estProgrammeAnnee")
                );
                Site site = new Site(
                    rs.getString("aLieuDansSite"),
                    rs.getString("nom_site"),
                    rs.getFloat("longitude"),
                    rs.getFloat("latitude")
                );
                Sport sport = new Sport(
                    rs.getString("concerneSport"),
                    rs.getString("nom_sport")
                );
                dpsList.add(new DPS(
                    rs.getLong("id"),
                    rs.getInt("horaire_depart"),
                    rs.getInt("horaire_fin"),
                    journee,
                    site,
                    sport
                ));
            }
        }
        return dpsList;
    }

    public void update(DPS dps) throws SQLException {
        String sql = "UPDATE DPS SET horaire_depart = ?, horaire_fin = ?, estProgrammeJour = ?, estProgrammeMois = ?, estProgrammeAnnee = ?, aLieuDansSite = ?, concerneSport = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, dps.getHoraireDepart());
            stmt.setInt(2, dps.getHoraireFin());
            stmt.setInt(3, dps.getEstProgramme().getJour());
            stmt.setInt(4, dps.getEstProgramme().getMois());
            stmt.setInt(5, dps.getEstProgramme().getAnnee());
            stmt.setString(6, dps.getALieuDans().getCode());
            stmt.setString(7, dps.getConcerne().getCode());
            stmt.setLong(8, dps.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM DPS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
