package model.service;

import java.util.ArrayList;
import java.util.List;
import model.persistence.*;

/**
 * Classe de gestion des secouristes.
 * Cette classe permet de créer, modifier, supprimer et lister les secouristes.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class SecouristeMngt {

    // Pour stocker les secouristes
    private final List<Secouriste> listeSecouristes = new ArrayList<>();


    /**
     * Créer un secouriste
     */
    public void creerSecouriste(long id, String nom, String prenom, String dateNaissance, String email, String tel, String adresse) {

        Secouriste nouveauSecouriste = new Secouriste();

        nouveauSecouriste.setId(id);
        nouveauSecouriste.setNom(nom);
        nouveauSecouriste.setPrenom(prenom);
        nouveauSecouriste.setDateNaissance(dateNaissance);
        nouveauSecouriste.setEmail(email);
        nouveauSecouriste.setTel(tel);
        nouveauSecouriste.setAdresse(adresse);

        listeSecouristes.add(nouveauSecouriste);
    }


    /**
     * Modifier un secouriste
     */
    public void modifierSecouriste(long id, String nom, String prenom, String dateNaissance, String email, String tel, String adresse) {
        for (Secouriste s : listeSecouristes) {
            if (s.getId() == id) {
                s.setNom(nom);
                s.setPrenom(prenom);
                s.setDateNaissance(dateNaissance);
                s.setEmail(email);
                s.setTel(tel);
                s.setAdresse(adresse);
                break;
            }
        }
    }


    /**
     * Supprimer un secouriste
     */
    public void supprimerSecouriste(long id) {
        for (Secouriste s : listeSecouristes) {
            if (s.getId() == id) {
                listeSecouristes.remove(s);
                break;
            }
        }
    }


    /**
     * Lister les secouristes
     */
    public void listerSecouristes() {
        for (Secouriste s : listeSecouristes) {
            System.out.println("ID: " + s.getId() + ", Nom: " + s.getNom() + ", Prénom: " + s.getPrenom() +
                               ", Date de Naissance: " + s.getDateNaissance() + ", Email: " + s.getEmail() +
                               ", Téléphone: " + s.getTel() + ", Adresse: " + s.getAdresse());
        }
    }

}