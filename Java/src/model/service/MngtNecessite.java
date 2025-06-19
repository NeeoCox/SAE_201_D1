package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAONecessite;
import model.persistence.Necessite;

/**
 * Classe de gestion des relations de nécessité entre compétences.
 * Utilise DAONecessite pour toutes les opérations.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class MngtNecessite {

    /**
     * DAO pour les opérations sur les relations de nécessité.
     */
    private final DAONecessite daoNecessite;

    /**
     * Constructeur de la classe MngtNecessite.
     * @param daoNecessite DAO pour les opérations sur les relations de nécessité
     */
    public MngtNecessite(DAONecessite daoNecessite) {
        this.daoNecessite = daoNecessite;
    }

    /**
     * Créer une relation de nécessité et l'ajouter en base.
     */
    public void creerNecessite(String intituleCompetence, String intituleCompetenceNecessaire) throws SQLException {
        Necessite necessite = new Necessite();
        necessite.setIntituleCompetence(intituleCompetence);
        necessite.setIntituleCompetenceNecessaire(intituleCompetenceNecessaire);
        daoNecessite.create(necessite);
    }

    /**
     * Supprimer une relation de nécessité de la base.
     */
    public void supprimerNecessite(String intituleCompetence, String intituleCompetenceNecessaire) throws SQLException {
        daoNecessite.delete(intituleCompetence, intituleCompetenceNecessaire);
    }

    /**
     * Lister toutes les relations de nécessité (affiche les détails).
     */
    public void listerNecessites() throws SQLException {
        List<Necessite> necessites = daoNecessite.readAll();
        for (Necessite n : necessites) {
            System.out.println("Compétence : " + n.getIntituleCompetence() +
                               " nécessite : " + n.getIntituleCompetenceNecessaire());
        }
    }
}