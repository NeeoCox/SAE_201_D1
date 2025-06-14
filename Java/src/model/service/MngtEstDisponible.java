package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.DAOEstDisponible;
import model.persistence.*;

/**
 * Classe de gestion des disponibilités (EstDisponible) avec persistance en base.
 * Utilise DAOEstDisponible pour toutes les opérations.
 */
public class MngtEstDisponible {

    private final DAOEstDisponible daoEstDisponible;

    public MngtEstDisponible(DAOEstDisponible daoEstDisponible) {
        this.daoEstDisponible = daoEstDisponible;
    }

    /**
     * Créer une disponibilité et l'ajouter en base.
     */
    public void creerDispo(long idSecouriste, Journee journee, int jour, int mois, int annee) throws SQLException {
        EstDisponible dispo = new EstDisponible();
        dispo.setIdSecouriste(idSecouriste);
        dispo.setLaJournee(journee);
        dispo.setJourJournee(jour);
        dispo.setMoisJournee(mois);
        dispo.setAnneeJournee(annee);
        daoEstDisponible.create(dispo);
    }

    /**
     * Modifier une disponibilité existante en base.
     */
    public void modifierDispo(long idSecouriste, Journee journee, int jour, int mois, int annee) throws SQLException {
        EstDisponible dispo = new EstDisponible();
        dispo.setIdSecouriste(idSecouriste);
        dispo.setLaJournee(journee);
        dispo.setJourJournee(jour);
        dispo.setMoisJournee(mois);
        dispo.setAnneeJournee(annee);
        // Ici, on supprime puis on recrée la disponibilité (pas d'update direct dans DAO)
        daoEstDisponible.delete(idSecouriste, jour, mois, annee);
        daoEstDisponible.create(dispo);
    }

    /**
     * Supprimer une disponibilité de la base.
     */
    public void supprimerDispo(long idSecouriste, int jour, int mois, int annee) throws SQLException {
        daoEstDisponible.delete(idSecouriste, jour, mois, annee);
    }

    /**
     * Lister toutes les disponibilités (affiche les détails).
     */
    public void listerDispos() throws SQLException {
        List<EstDisponible> listeDispos = daoEstDisponible.readAll();
        for (EstDisponible dispo : listeDispos) {
            System.out.println("ID Secouriste: " + dispo.getIdSecouriste() +
                               ", Jour: " + dispo.getJourJournee() +
                               ", Mois: " + dispo.getMoisJournee() +
                               ", Année: " + dispo.getAnneeJournee() +
                               ", Journee: " + dispo.getLaJournee());
        }
    }
}