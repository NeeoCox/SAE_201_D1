package model.service;


public class MngtSession {
    private static Object utilisateurConnecte;

    public static void setUtilisateurConnecte(Object utilisateur) {
        utilisateurConnecte = utilisateur;
    }

    public static Object getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
}
