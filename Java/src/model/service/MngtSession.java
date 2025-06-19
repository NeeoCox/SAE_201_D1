package model.service;

/**
 * Classe de gestion de la session utilisateur.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class MngtSession {
    private static Object utilisateurConnecte;
    private static Long idSecouristeAModifier = null;
    private static Long idDPSAModifier = null;
    private static String intituleCompetenceAModifier = null;

    public static void setUtilisateurConnecte(Object utilisateur) {
        utilisateurConnecte = utilisateur;
    }

    public static Object getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public static void setIdSecouristeAModifier(Long id) {
        idSecouristeAModifier = id;
    }

    public static Long getIdSecouristeAModifier() {
        return idSecouristeAModifier;
    }

    public static void setIdDPSAModifier(Long id) {
        idDPSAModifier = id;
    }

    public static Long getIdDPSAModifier() {
        return idDPSAModifier;
    }

    public static void setIntituleCompetenceAModifier(String intitule) {
        intituleCompetenceAModifier = intitule;
    }

    public static String getIntituleCompetenceAModifier() {
        return intituleCompetenceAModifier;
    }
}
