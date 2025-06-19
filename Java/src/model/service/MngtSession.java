package model.service;

/**
 * Classe de gestion de la session utilisateur.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class MngtSession {
    /**
     * Variable pour stocker l'utilisateur connecté.
     */
    private static Object utilisateurConnecte;
    /**
     * Variable pour stocker l'ID du secouriste à modifier.
     */
    private static Long idSecouristeAModifier = null;
    /**
     * Variable pour stocker l'ID du DPS à modifier.
     */
    private static Long idDPSAModifier = null;
    /**
     * Variable pour stocker l'intitulé de la compétence à modifier.
     */
    private static String intituleCompetenceAModifier = null;

    /**
     * Méthode pour définir l'utilisateur connecté.
     * @param utilisateur L'utilisateur à connecter.
     */
    public static void setUtilisateurConnecte(Object utilisateur) {
        utilisateurConnecte = utilisateur;
    }

    /**
     * Méthode pour obtenir l'utilisateur connecté.
     * @return L'utilisateur connecté.
     */
    public static Object getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    /**
     * Méthode pour définir l'ID du secouriste à modifier.
     * @param id L'ID du secouriste à modifier.
     */
    public static void setIdSecouristeAModifier(Long id) {
        idSecouristeAModifier = id;
    }

    /**
     * Méthode pour obtenir l'ID du secouriste à modifier.
     * @return L'ID du secouriste à modifier.
     */
    public static Long getIdSecouristeAModifier() {
        return idSecouristeAModifier;
    }

    /**
     * Méthode pour définir l'ID du DPS à modifier.
     * @param id L'ID du DPS à modifier.
     */
    public static void setIdDPSAModifier(Long id) {
        idDPSAModifier = id;
    }

    /**
     * Méthode pour obtenir l'ID du DPS à modifier.
     * @return L'ID du DPS à modifier.
     */
    public static Long getIdDPSAModifier() {
        return idDPSAModifier;
    }

    /**
     * Méthode pour définir l'intitulé de la compétence à modifier.
     * @param intitule L'intitulé de la compétence à modifier.
     */
    public static void setIntituleCompetenceAModifier(String intitule) {
        intituleCompetenceAModifier = intitule;
    }

    /**
     * Méthode pour obtenir l'intitulé de la compétence à modifier.
     * @return L'intitulé de la compétence à modifier.
     */
    public static String getIntituleCompetenceAModifier() {
        return intituleCompetenceAModifier;
    }
}
