package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.DAOBesoin;
import model.persistence.Besoin;

/**
 * Classe de gestion des besoins (Besoin) avec persistance en base.
 * Utilise DAOBesoin pour toutes les opérations.
 */
public class MngtBesoin {

    private final DAOBesoin daoBesoin;

    public MngtBesoin(DAOBesoin daoBesoin) {
        this.daoBesoin = daoBesoin;
    }

    /**
     * Créer un besoin et l'ajouter en base.
     */
    public void creerBesoin(int nombre, String intituleCompetence, long idDPS) throws SQLException {
        Besoin besoin = new Besoin(nombre, intituleCompetence, idDPS);
        daoBesoin.create(besoin);
    }

    /**
     * Modifier un besoin existant en base.
     */
    public void modifierBesoin(int nombre, String intituleCompetence, long idDPS) throws SQLException {
        Besoin besoin = new Besoin(nombre, intituleCompetence, idDPS);
        daoBesoin.update(besoin);
    }

    /**
     * Supprimer un besoin de la base.
     */
    public void supprimerBesoin(long idDPS, String intituleCompetence) throws SQLException {
        daoBesoin.delete(idDPS, intituleCompetence);
    }

    /**
     * Lister tous les besoins (affiche les détails).
     */
    public void listerBesoins() throws SQLException {
        List<Besoin> besoins = daoBesoin.readAll();
        for (Besoin b : besoins) {
            System.out.println("DPS ID: " + b.getIdDPS() +
                               ", Compétence: " + b.getIntituleCompetence() +
                               ", Nombre: " + b.getNombre());
        }
    }

    /**
     * Lire un besoin par idDPS et intituleCompetence.
     */
    public Besoin lireBesoin(long idDPS, String intituleCompetence) throws SQLException {
        return daoBesoin.read(idDPS, intituleCompetence);
    }
}