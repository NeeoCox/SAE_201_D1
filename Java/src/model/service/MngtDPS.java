package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAODPS;
import model.persistence.*;

/**
 * Classe de gestion des dispositifs de secours avec persistance en base.
 * Utilise DAODPS pour toutes les opérations.
 */
public class MngtDPS {

    private final DAODPS daoDPS;

    public MngtDPS(DAODPS daoDPS) {
        this.daoDPS = daoDPS;
    }

    /**
     * Créer un DPS et l'ajouter en base.
     */
    public void creerDPS(long id, int horaireDepart, int horaireFin, Journee estProgramme, Site aLieuDans, Sport concerne) throws SQLException {
        DPS nouveauDPS = new DPS(id, horaireDepart, horaireFin, estProgramme, aLieuDans, concerne);
        daoDPS.create(nouveauDPS);
    }

    /**
     * Modifier un DPS existant en base.
     */
    public void modifierDPS(long id, int horaireDepart, int horaireFin, Journee estProgramme, Site aLieuDans, Sport concerne) throws SQLException {
        DPS dps = new DPS(id, horaireDepart, horaireFin, estProgramme, aLieuDans, concerne);
        daoDPS.update(dps);
    }

    /**
     * Supprimer un DPS de la base.
     */
    public void supprimerDPS(long id) throws SQLException {
        daoDPS.delete(id);
    }

    /**
     * Lister tous les DPS (affiche les détails).
     */
    public void listerDPS() throws SQLException {
        List<DPS> dpsList = daoDPS.readAll();
        for (DPS d : dpsList) {
            System.out.println("ID: " + d.getId() + ", Horaire Départ: " + d.getHoraireDepart() + ", Horaire Fin: " + d.getHoraireFin() +
                               ", Est Programmé: " + d.getEstProgramme() + ", A Lieu Dans: " + d.getALieuDans().getNom() +
                               ", Concerne: " + d.getConcerne().getNom());
        }
    }

    /**
     * Lire un DPS par son id.
     */
    public DPS lireDPS(long id) throws SQLException {
        return daoDPS.read(id);
    }
}