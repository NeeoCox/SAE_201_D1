package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAOEstAffecteA;
import model.data.*;

/**
 * Classe de gestion des affectations (EstAffecteA) avec persistance en base.
 * Utilise DAOEstAffecteA pour toutes les opérations.
 */
public class MngtEstAffecteA {

    private final DAOEstAffecteA daoAffectation;

    public MngtEstAffecteA(DAOEstAffecteA daoAffectation) {
        this.daoAffectation = daoAffectation;
    }

    /**
     * Créer une affectation et l'ajouter en base.
     */
    public void creerAffectation(long idSecouriste, String intituleCompetence, long idDPS) throws SQLException {
        EstAffecteA affectation = new EstAffecteA();
        affectation.setIdSecouriste((int)idSecouriste);
        affectation.setIntituleCompetence(intituleCompetence);
        affectation.setIdDPS(idDPS);
        daoAffectation.create(affectation);
    }

    /**
     * Modifier une affectation existante en base.
     */
    public void modifierAffectation(long idSecouriste, String intituleCompetence, long idDPS) throws SQLException {
        EstAffecteA affectation = new EstAffecteA();
        affectation.setIdSecouriste((int)idSecouriste);
        affectation.setIntituleCompetence(intituleCompetence);
        affectation.setIdDPS(idDPS);
        daoAffectation.update(affectation);
    }

    /**
     * Supprimer une affectation de la base.
     */
    public void supprimerAffectation(long idSecouriste, long idDPS) throws SQLException {
        daoAffectation.delete((int)idSecouriste, idDPS);
    }

    /**
     * Lister toutes les affectations (affiche les détails).
     */
    public void listerAffectations() throws SQLException {
        List<EstAffecteA> listeAffectations = daoAffectation.readAll();
        for (EstAffecteA a : listeAffectations) {
            System.out.println("Affectation: Secouriste ID: " + a.getIdSecouriste() +
                               ", Compétence: " + a.getIntituleCompetence() +
                               ", DPS ID: " + a.getIdDPS());
        }
    }
}