package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.persistence.DPS;
import model.persistence.Journee;
import model.persistence.Site;
import model.persistence.Sport;

/**
 * DAO pour la gestion des Dossiers de Prise en Charge (DPS) dans la base de données.
 * Cette classe étend la classe DAO générique et fournit des méthodes pour créer, lire, mettre à jour et supprimer des DPS.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DAODPS extends DAO<DPS>{
    /**
     * Connection à la base de données.
     */
    private final Connection connection;

    /**
     * Constructeur de la classe DAODPS.
     * Initialise la connexion à la base de données.
     */
    public DAODPS() {
        try {
            this.connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Crée un nouveau DPS dans la base de données.
     * @param dps L'objet DPS à insérer dans la base de données.
     * @throws SQLException si une erreur se produit lors de l'insertion dans la base de données.
     */
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

    /**
     * Lit un DPS spécifique à partir de la base de données en fonction de son ID.
     * @param id L'ID du DPS à lire.
     * @return Un objet DPS représentant le DPS trouvé, ou null si aucun DPS n'est trouvé.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
    public DPS read(long id) throws SQLException {
        String sql = "SELECT d.*, s.nom AS nom_site, s.longitude, s.latitude, sp.nom AS nom_sport " +
                    "FROM DPS d " +
                    "JOIN Site s ON d.aLieuDansSite = s.code " +
                    "JOIN Sport sp ON d.concerneSport = sp.code " +
                    "WHERE d.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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

    /**
     * Lit tous les DPS de la base de données.
     * @return Une liste de tous les objets DPS présents dans la base de données.
     * @throws SQLException si une erreur se produit lors de la lecture dans la base de données.
     */
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

    /**
     * Met à jour un DPS existant dans la base de données.
     * @param dps L'objet DPS contenant les nouvelles informations à mettre à jour.
     * @throws SQLException si une erreur se produit lors de la mise à jour dans la base de données.
     */
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

    /**
     * Supprime un DPS spécifique de la base de données en fonction de son ID.
     * @param id L'ID du DPS à supprimer.
     * @throws SQLException si une erreur se produit lors de la suppression dans la base de données.
     */
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM DPS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
