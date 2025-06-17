package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAOSite;
import model.persistence.Site;

/**
 * Classe de gestion des sites avec persistance en base.
 * Utilise DAOSite pour toutes les opérations.
 */
public class MngtSite {

    private final DAOSite daoSite;

    public MngtSite(DAOSite daoSite) {
        this.daoSite = daoSite;
    }

    /**
     * Créer un site et l'ajouter en base.
     */
    public void creerSite(String code, String nom, float longitude, float latitude) throws SQLException {
        Site site = new Site(code, nom, longitude, latitude);
        daoSite.create(site);
    }

    /**
     * Modifier un site existant en base.
     */
    public void modifierSite(String ancienCode, String nouveauCode, String nom, float longitude, float latitude) throws SQLException {
        Site site = new Site(nouveauCode, nom, longitude, latitude);
        daoSite.update(site, ancienCode);
    }

    /**
     * Supprimer un site de la base.
     */
    public void supprimerSite(String code) throws SQLException {
        daoSite.delete(code);
    }

    /**
     * Lister tous les sites (affiche les détails).
     */
    public void listerSites() throws SQLException {
        List<Site> sites = daoSite.readAll();
        for (Site s : sites) {
            System.out.println("Code : " + s.getCode() +
                               ", Nom : " + s.getNom() +
                               ", Longitude : " + s.getLongitude() +
                               ", Latitude : " + s.getLatitude());
        }
    }

    /**
     * Lire un site par son code.
     */
    public Site lireSite(String code) throws SQLException {
        return daoSite.read(code);
    }
}