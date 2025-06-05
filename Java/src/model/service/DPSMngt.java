<<<<<<< HEAD:Java/src/model/service/DPSMngt.java
package model.service;
=======
package metier.service;
import metier.persistence.*;
import java.util.ArrayList;
import java.util.List;
>>>>>>> 2ed2943cdf851ffc3cfbaad7f0687a5319cf2818:Java/src/metier/service/DPSMngt.java

/**
 * Classe de gestion des dispositifs de secours.
 * Cette classe permet de créer, modifier, supprimer et lister les DPS.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DPSMngt {

    // Pour stocker les DPS
    private List<DPS> listeDPS = new ArrayList<>();


    /**
     * Créer un DPS
     * @param id L'identifiant du DPS
     * @param horaireDepart L'heure de départ du DPS
     * @param horaireFin L'heure de fin du DPS
     * @param estProgramme La journée à laquelle le DPS est programmé
     * @param aLieuDans Le site où le DPS a lieu
     * @param concerne Le sport concerné par le DPS
     */
    public void creerDPS(long id, int horaireDepart, int horaireFin, Journee estProgramme, Site aLieuDans, Sport concerne) {

        DPS nouveauDPS = new DPS();

        nouveauDPS.setId(id);
        nouveauDPS.setHoraireDepart(horaireDepart);
        nouveauDPS.setHoraireFin(horaireFin);
        nouveauDPS.setEstProgramme(estProgramme);
        nouveauDPS.setALieuDans(aLieuDans);
        nouveauDPS.setConcerne(concerne);

        listeDPS.add(nouveauDPS);
    }


    /**
     * Modifier un DPS
     * @param id L'identifiant du DPS à modifier
     * @param horaireDepart La nouvelle heure de départ du DPS
     * @param horaireFin La nouvelle heure de fin du DPS
     * @param estProgramme La nouvelle journée à laquelle le DPS est programmé
     * @param aLieuDans Le nouveau site où le DPS a lieu
     * @param concerne Le nouveau sport concerné par le DPS
     */
    public void modifierDPS(long id, int horaireDepart, int horaireFin, Journee estProgramme, Site aLieuDans, Sport concerne) {
        for (DPS d : listeDPS) {
            if (d.getId() == id) {
                d.setHoraireDepart(horaireDepart);
                d.setHoraireFin(horaireFin);
                d.setEstProgramme(estProgramme);
                d.setALieuDans(aLieuDans);
                d.setConcerne(concerne);
                break;
            }
        }
    }


    /**
     * Supprimer un DPS
     * @param id L'identifiant du DPS à supprimer
     */
    public void supprimerDPS(long id) {
        for (DPS d : listeDPS) {
            if (d.getId() == id) {
                listeDPS.remove(d);
                break;
            
            }
        }
    }

    /**
     * Lister les DPS
     * Cette méthode affiche les détails de chaque DPS dans la liste.
     */
    public void listerDPS() {
        for (DPS d : listeDPS) {
            System.out.println("ID: " + d.getId() + ", Horaire Départ: " + d.getHoraireDepart() + ", Horaire Fin: " + d.getHoraireFin() +
                               ", Est Programmé: " + d.getEstProgramme() + ", A Lieu Dans: " + d.getALieuDans().getNom() +
                               ", Concerne: " + d.getConcerne().getNom());
        }
    }



}