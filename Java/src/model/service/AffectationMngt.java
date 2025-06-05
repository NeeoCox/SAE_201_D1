package model.service;
import java.util.ArrayList;
import java.util.List;
import model.persistence.*;


/**
 * Classe de gestion des affectations.
 * Cette classe permet de créer, modifier, supprimer et lister les affectations.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class AffectationMngt {

    // Pour stocker les affectations
    private final List<EstAffecteA> listeAffectations = new ArrayList<>();


    /**
     * Créer une affectation.
     * @param idSecouriste L'identifiant du secouriste
     * @param competence La compétence du secouriste
     * @param intituleCompetence L'intitulé de la compétence
     * @param idDPS L'identifiant du DPS auquel le secouriste est affecté
     */
    public void creerAffectation(long idSecouriste, Competence competence, String intituleCompetence, long idDPS) {
        EstAffecteA affectation = new EstAffecteA();

        affectation.setIdSecouriste((int)idSecouriste); // setter attend un int
        affectation.setLaCompetence(competence);
        affectation.setIntituleCompetence(intituleCompetence);
        affectation.setIdDPS(idDPS);

        listeAffectations.add(affectation);
    }


    /**
     * Modifier une affectation.
     */
    public void modifierAffectation(long idSecouriste, Competence competence, String intituleCompetence, long idDPS) {
        for (EstAffecteA a : listeAffectations) {
            if (a.getIdSecouriste() == idSecouriste && a.getIdDPS() == idDPS) {
                a.setLaCompetence(competence);
                a.setIntituleCompetence(intituleCompetence);
                break;
            }
        }
    }


    /**
     * Supprimer une affectation.
     * @param idSecouriste L'identifiant du secouriste
     * @param idDPS L'identifiant du DPS
     */
    public void supprimerAffectation(long idSecouriste, long idDPS) {
        for (EstAffecteA a : listeAffectations) {
            if (a.getIdSecouriste() == idSecouriste && a.getIdDPS() == idDPS) {
                listeAffectations.remove(a);
                break;
            }
        }
    }

    /**
     * Lister les affectations.
     * Cette méthode affiche les détails de chaque affectation dans la liste.
     */
    public void listerAffectations() {
        for (EstAffecteA a : listeAffectations) {
            System.out.println("Affectation: Secouriste ID: " + a.getIdSecouriste() + 
                               ", Compétence: " + a.getIntituleCompetence() + 
                               ", DPS ID: " + a.getIdDPS());
        }
    }

}