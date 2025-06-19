package model.service;

import model.persistence.Secouriste;

public class MngtSession {
    private static Secouriste utilisateurConnecte;

    public static void setUtilisateurConnecte(Secouriste user) {
        utilisateurConnecte = user;
    }

    public static Secouriste getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public static long getIdUtilisateurConnecte() {
        long ret;
        if (utilisateurConnecte == null){
            ret = -1; 
        }
        else{
            ret = utilisateurConnecte.getId();
        }
        return ret;

    }
}
