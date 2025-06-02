package metier.service;
import metier.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de gestion des disponibilités.
 * Cette classe permet de créer, modifier, supprimer et lister les disponibilités.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DispoMngt {

    // Pour stocker les disponibilités
    private List<EstDisponible> listeDispos = new ArrayList<>();

    /**
     * Créer une disponibilité
     * @param idSecouriste L'identifiant du secouriste
     * @param journee La journée pour laquelle le secouriste est disponible
     * @param jour Le jour de la date de disponibilité
     * @param mois Le mois de la date de disponibilité
     * @param annee L'année de la date de disponibilité
     */
    public void creerDispo(long idSecouriste, Journee journee, int jour, int mois, int annee) {
        EstDisponible dispo = new EstDisponible();

        dispo.setIdSecouriste((int)idSecouriste); // setter attend un int
        dispo.setLaJournee(journee);
        dispo.setJourJournee(jour);
        dispo.setMoisJournee(mois);
        dispo.setAnneeJournee(annee);
    
        listeDispos.add(dispo);
    }


    /**
     * Modifier une disponibilité
     * @param idSecouriste L'identifiant du secouriste
     * @param journee La journée pour laquelle le secouriste est disponible
     * @param jour Le jour de la date de disponibilité
     * @param mois Le mois de la date de disponibilité
     * @param annee L'année de la date de disponibilité
     */
    public void modifierDispo(long idSecouriste, Journee journee, int jour, int mois, int annee) {
        for (EstDisponible dispo : listeDispos) {
            if (dispo.getIdSecouriste() == idSecouriste) {
                dispo.setLaJournee(journee);
                dispo.setJourJournee(jour);
                dispo.setMoisJournee(mois);
                dispo.setAnneeJournee(annee);
                break;
            }
        }
    }


    /**
     * Supprimer une disponibilité
     * @param idSecouriste L'identifiant du secouriste dont on veut supprimer la disponibilité
     */
    public void supprimerDispo(long idSecouriste) {
        for (EstDisponible dispo : listeDispos) {
            if (dispo.getIdSecouriste() == idSecouriste) {
                listeDispos.remove(dispo);
                break;
            }
        }
    }


    /**
     * Lister les disponibilités
     * Cette méthode affiche les détails de chaque disponibilité dans la liste.
     */
    public void listerDispos() {
        for (EstDisponible dispo : listeDispos) {
            System.out.println("ID Secouriste: " + dispo.getIdSecouriste() + 
                               ", Jour: " + dispo.getJourJournee() + 
                               ", Mois: " + dispo.getMoisJournee() + 
                               ", Année: " + dispo.getAnneeJournee() + 
                               ", Journee: " + dispo.getLaJournee());
        }
    }


}