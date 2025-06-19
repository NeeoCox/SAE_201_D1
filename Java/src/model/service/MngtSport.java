package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAOSport;
import model.persistence.Sport;

/**
 * Classe de gestion des sports avec persistance en base.
 * Utilise DAOSport pour toutes les opérations.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class MngtSport {

    /**
     * DAO pour la gestion des sports.
     */
    private final DAOSport daoSport;

    /**
     * Constructeur de la classe MngtSport.
     * @param daoSport DAO pour la gestion des sports.
     */
    public MngtSport(DAOSport daoSport) {
        this.daoSport = daoSport;
    }

    /**
     * Créer un sport et l'ajouter en base.
     */
    public void creerSport(String code, String nom) throws SQLException {
        Sport sport = new Sport(code, nom);
        daoSport.create(sport);
    }

    /**
     * Modifier un sport existant en base.
     */
    public void modifierSport(String ancienCode, String nouveauCode, String nom) throws SQLException {
        Sport sport = new Sport(nouveauCode, nom);
        daoSport.update(sport, ancienCode);
    }

    /**
     * Supprimer un sport de la base.
     */
    public void supprimerSport(String code) throws SQLException {
        daoSport.delete(code);
    }

    /**
     * Lister tous les sports (affiche les détails).
     */
    public void listerSports() throws SQLException {
        List<Sport> sports = daoSport.readAll();
        for (Sport s : sports) {
            System.out.println("Code : " + s.getCode() + ", Nom : " + s.getNom());
        }
    }

    /**
     * Lire un sport par son code.
     */
    public Sport lireSport(String code) throws SQLException {
        return daoSport.read(code);
    }
}