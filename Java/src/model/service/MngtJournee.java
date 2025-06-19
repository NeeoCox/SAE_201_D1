package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAOJournee;
import model.persistence.Journee;

/**
 * Classe de gestion des journées (Journee) avec persistance en base.
 * Utilise DAOJournee pour toutes les opérations.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class MngtJournee {

    /**
     * DAO pour les opérations sur les journées.
     */
    private final DAOJournee daoJournee;

    /**
     * Constructeur de la classe MngtJournee.
     * @param daoJournee DAO pour les opérations sur les journées
     */
    public MngtJournee(DAOJournee daoJournee) {
        this.daoJournee = daoJournee;
    }

    /**
     * Créer une journée et l'ajouter en base.
     */
    public void creerJournee(int jour, int mois, int annee) throws SQLException {
        Journee journee = new Journee(jour, mois, annee);
        daoJournee.create(journee);
    }

    /**
     * Modifier une journée existante en base.
     */
    public void modifierJournee(int ancienJour, int ancienMois, int ancienneAnnee, int nouveauJour, int nouveauMois, int nouvelleAnnee) throws SQLException {
        Journee nouvelleJournee = new Journee(nouveauJour, nouveauMois, nouvelleAnnee);
        daoJournee.update(nouvelleJournee, ancienJour, ancienMois, ancienneAnnee);
    }

    /**
     * Supprimer une journée de la base.
     */
    public void supprimerJournee(int jour, int mois, int annee) throws SQLException {
        daoJournee.delete(jour, mois, annee);
    }

    /**
     * Lister toutes les journées (affiche les détails).
     */
    public void listerJournees() throws SQLException {
        List<Journee> journees = daoJournee.readAll();
        for (Journee j : journees) {
            System.out.println("Jour : " + j.getJour() + ", Mois : " + j.getMois() + ", Année : " + j.getAnnee());
        }
    }

    /**
     * Lire une journée par sa date.
     */
    public Journee lireJournee(int jour, int mois, int annee) throws SQLException {
        return daoJournee.read(jour, mois, annee);
    }
}