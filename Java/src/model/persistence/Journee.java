package model.persistence;

/**
 * Représente une journée avec une date précise.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Journee {
    /**
     * Le jour de la date.
     */
    private int jour;
    /**
     * Le mois de la date.
     */
    private int mois;
    /**
     * L'année de la date.
     */
    private int annee;

    
    /**
     * Constructeur de la classe Journee.
     * @param unJour Le jour de la date.
     * @param unMois Le mois de la date.
     * @param uneAnnee L'année de la date.
     */
    public Journee(int unJour, int unMois, int uneAnnee) {
        this.jour = unJour;
        this.mois = unMois;
        this.annee = uneAnnee;
    }

    /**
     * Constructeur par défaut de la classe Journee.
     * Initialise la date au 1er janvier 1970.
     */
    public Journee() {
        this.jour = 1;
        this.mois = 1;
        this.annee = 1970;
    }

    /**
     * Accesseur de l'attribut jour.
     * @return Le jour de la date.
     */
    public int getJour() {
        return this.jour;
    }

    /**
     * Mutateur de l'attribut jour.
     * @param unJour Le nouveau jour de la date.
     */
    public void setJour(int unJour) {
        this.jour = unJour;
    }


    /**
     * Accesseur de l'attribut mois.
     * @return Le mois de la date.
     */
    public int getMois() {
        return this.mois;
    }

    /**
     * Mutateur de l'attribut mois.
     * @param unMois Le nouveau mois de la date.
     */
    public void setMois(int unMois) {
        this.mois = unMois;
    }


    /**
     * Accesseur de l'attribut annee.
     * @return L'année de la date.
     */
    public int getAnnee() {
        return this.annee;
    }

    /**
     * Mutateur de l'attribut annee.
     * @param uneAnnee La nouvelle année de la date.
     */
    public void setAnnee(int uneAnnee) {
        this.annee = uneAnnee;
    }


    /**
     * Méthode toString pour afficher les informations de la journée.
     */
    @Override
    public String toString() {
        return "Journee{" +
                "jour=" + jour +
                ", mois=" + mois +
                ", annee=" + annee +
                '}';
    }


    /**
     * Vérifie si deux journées sont identiques.
     * @param autre L'autre journée à comparer.
     * @return true si les dates sont identiques, false sinon.
     */
    public boolean estEgale(Journee autre) {
        return autre != null && this.jour == autre.jour && this.mois == autre.mois && this.annee == autre.annee;
    }
}