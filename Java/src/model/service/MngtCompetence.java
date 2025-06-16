package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.DAOCompetence;
import model.persistence.Competence;

/**
 * Classe de gestion des compétences (Competence) avec persistance en base.
 * Utilise DAOCompetence pour toutes les opérations.
 */
public class MngtCompetence {

    private final DAOCompetence daoCompetence;

    public MngtCompetence(DAOCompetence daoCompetence) {
        this.daoCompetence = daoCompetence;
    }

    /**
     * Créer une compétence et l'ajouter en base.
     */
    public void creerCompetence(String intitule) throws SQLException {
        Competence competence = new Competence();
        competence.setIntitule(intitule);
        daoCompetence.create(competence);
    }

    /**
     * Modifier une compétence existante en base.
     */
    public void modifierCompetence(String nouvelIntitule, String ancienIntitule) throws SQLException {
        Competence competence = new Competence();
        competence.setIntitule(nouvelIntitule);
        daoCompetence.update(competence, ancienIntitule);
    }

    /**
     * Supprimer une compétence de la base.
     */
    public void supprimerCompetence(String intitule) throws SQLException {
        daoCompetence.delete(intitule);
    }

    /**
     * Lister toutes les compétences (affiche les détails).
     */
    public void listerCompetences() throws SQLException {
        List<Competence> competences = daoCompetence.readAll();
        for (Competence c : competences) {
            System.out.println("Intitulé : " + c.getIntitule());
        }
    }

    /**
     * Lire une compétence par son intitulé.
     */
    public Competence lireCompetence(String intitule) throws SQLException {
        return daoCompetence.read(intitule);
    }
}