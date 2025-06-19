package model.service;

/**
 * Classe de gestion de la session utilisateur.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class MngtSession {
    private static Object utilisateurConnecte;

    public static void setUtilisateurConnecte(Object utilisateur) {
        utilisateurConnecte = utilisateur;
    }

    public static Object getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
}
