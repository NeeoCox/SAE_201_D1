package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.DAOSecouriste;
import model.data.Secouriste;

/**
 * Classe de gestion des secouristes avec persistance en base.
 * Utilise DAOSecouriste pour toutes les opérations.
 */
public class MngtSecouriste {

    private final DAOSecouriste daoSecouriste;

    public MngtSecouriste(DAOSecouriste daoSecouriste) {
        this.daoSecouriste = daoSecouriste;
    }

    /**
     * Créer un secouriste et l'ajouter en base.
     */
    public void creerSecouriste(long id, String nom, String prenom, String dateNaissance, String email, String tel, String adresse) throws SQLException {
        Secouriste nouveauSecouriste = new Secouriste();
        nouveauSecouriste.setId(id);
        nouveauSecouriste.setNom(nom);
        nouveauSecouriste.setPrenom(prenom);
        nouveauSecouriste.setDateNaissance(dateNaissance);
        nouveauSecouriste.setEmail(email);
        nouveauSecouriste.setTel(tel);
        nouveauSecouriste.setAdresse(adresse);
        daoSecouriste.create(nouveauSecouriste);
    }

    /**
     * Modifier un secouriste existant en base.
     */
    public void modifierSecouriste(long id, String nom, String prenom, String dateNaissance, String email, String tel, String adresse) throws SQLException {
        Secouriste s = new Secouriste();
        s.setId(id);
        s.setNom(nom);
        s.setPrenom(prenom);
        s.setDateNaissance(dateNaissance);
        s.setEmail(email);
        s.setTel(tel);
        s.setAdresse(adresse);
        daoSecouriste.update(s);
    }

    /**
     * Supprimer un secouriste de la base.
     */
    public void supprimerSecouriste(long id) throws SQLException {
        daoSecouriste.delete(id);
    }

    /**
     * Lister tous les secouristes (affiche les détails).
     */
    public void listerSecouristes() throws SQLException {
        List<Secouriste> secouristes = daoSecouriste.readAll();
        for (Secouriste s : secouristes) {
            System.out.println("ID: " + s.getId() + ", Nom: " + s.getNom() + ", Prénom: " + s.getPrenom() +
                               ", Date de Naissance: " + s.getDateNaissance() + ", Email: " + s.getEmail() +
                               ", Téléphone: " + s.getTel() + ", Adresse: " + s.getAdresse());
        }
    }

    /**
     * Lire un secouriste par son id.
     */
    public Secouriste lireSecouriste(long id) throws SQLException {
        return daoSecouriste.read(id);
    }
}