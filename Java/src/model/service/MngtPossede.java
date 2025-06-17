package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAOPossede;
import model.data.Possede;

/**
 * Classe de gestion des relations Possede (compétences des secouristes).
 * Utilise DAOPossede pour toutes les opérations.
 */
public class MngtPossede {

    private final DAOPossede daoPossede;

    public MngtPossede(DAOPossede daoPossede) {
        this.daoPossede = daoPossede;
    }

    /**
     * Créer une relation Possede et l'ajouter en base.
     */
    public void creerPossede(long idSecouriste, String intituleCompetence) throws SQLException {
        Possede possede = new Possede();
        possede.setIdSecouriste(idSecouriste);
        possede.setIntituleCompetence(intituleCompetence);
        daoPossede.create(possede);
    }

    /**
     * Supprimer une relation Possede de la base.
     */
    public void supprimerPossede(long idSecouriste, String intituleCompetence) throws SQLException {
        daoPossede.delete(idSecouriste, intituleCompetence);
    }

    /**
     * Lister toutes les relations Possede (affiche les détails).
     */
    public void listerPossedes() throws SQLException {
        List<Possede> possedes = daoPossede.readAll();
        for (Possede p : possedes) {
            System.out.println("Secouriste ID : " + p.getIdSecouriste() +
                               ", Compétence : " + p.getIntituleCompetence());
        }
    }
}